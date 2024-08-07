import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Clase que funciona para actualizar el stock de un producto por su ID
public class form14 extends JFrame {
    private JPanel mainPanel; // Panel principal para contener los componentes
    private JTextField id; // Campo de texto para ingresar el ID del producto
    private JTextField stock; // Campo de texto para ingresar el nuevo stock
    private JButton ACTUALIZARButton; // Botón para actualizar el stock
    private JButton REGRESARButton; // Botón para regresar a la pantalla anterior
    private JLabel mensaje1; // Etiqueta para mostrar mensajes de estado
    private JLabel mensaje2; // Etiqueta para mostrar mensajes adicionales

    public form14() {
        setTitle("Actualizar Stock"); // Título de la ventana
        setContentPane(mainPanel); // Configura el panel principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Acción por defecto al cerrar la ventana
        setPreferredSize(new Dimension(700, 400)); // Tamaño preferido de la ventana
        pack(); // Ajusta el tamaño de la ventana al tamaño preferido
        setVisible(true); // Hace visible la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica que el campo ID no esté vacío
                if (id.getText().trim().isEmpty()) {
                    mensaje1.setText("El ID está vacío");
                    mensaje2.setText(""); // Limpia el mensaje adicional
                    return;
                }
                // Conexión a la base de datos
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680";
                String user = "sql10723680";
                String password = "uNjR5yDxj2";
                Connection con = null;
                PreparedStatement ps = null;
                try {
                    // Establece la conexión con la base de datos
                    con = DriverManager.getConnection(url, user, password);
                    String sql = "UPDATE zapatos SET stock = ? WHERE id_producto = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, stock.getText()); // Establece el nuevo stock
                    ps.setString(2, id.getText()); // Establece el ID del producto
                    // Ejecuta la actualización
                    int a = ps.executeUpdate();
                    if (a > 0) {
                        // Mensaje de éxito
                        mensaje1.setText("Se ha modificado: " + a + " stock");
                        mensaje2.setText("Actualización exitosa");
                    } else {
                        // Mensaje de error si no se encuentra el producto
                        mensaje1.setText("Error: No se encontró un producto con el ID");
                        mensaje2.setText("");
                    }
                } catch (SQLException e1) {
                    // Mensaje de error en caso de excepciones SQL
                    mensaje1.setText("Error al actualizar los datos.");
                    mensaje2.setText(e1.getMessage());
                } finally {
                    // Cierra la conexión y el prepared statement
                    try {
                        if (ps != null) ps.close();
                        if (con != null) con.close();
                    } catch (SQLException ex) {
                        // Mensaje de error al cerrar la conexión
                        mensaje1.setText("Error al cerrar la conexión");
                        mensaje2.setText(ex.getMessage());
                    }
                }
            }
        });

        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form5(); // Crea una nueva instancia de la clase form5
                setVisible(false); // Oculta la ventana actual
            }
        });
    }
}
