import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Clase que representa un formulario para eliminar un producto por su ID
public class form7 extends JFrame {
    private JPanel mainPanel;
    private JTextField id;
    private JButton ELIMINARButton;
    private JButton REGRESARButton;
    private JLabel mensaje;

    public form7() {
        setTitle("Eliminar producto"); // Título de la ventana
        setContentPane(mainPanel); // Establece el panel principal del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura el cierre de la aplicación al cerrar la ventana
        setPreferredSize(new Dimension(600, 400)); // Establece el tamaño preferido de la ventana
        pack(); // Ajusta el tamaño de la ventana para que se ajuste a los componentes
        setVisible(true); // Hace visible la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si el campo ID está vacío
                if(id.getText().equals("")){
                    mensaje.setText("Ingrese un ID");
                    return;
                }
                // Configuración de la conexión a la base de datos
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680";
                String username = "sql10723680";
                String password = "uNjR5yDxj2";
                String query ="delete from zapatos where id_producto = ?"; // Consulta SQL para eliminar un producto por ID
                try (Connection con = DriverManager.getConnection(url, username, password)) {
                    // Preparación de la consulta
                    PreparedStatement ps = con.prepareStatement(query);
                    int id_pro = Integer.parseInt(id.getText()); // Convierte el ID ingresado a un entero
                    ps.setInt(1, id_pro);
                    // Ejecuta la consulta y verifica si algún registro fue afectado
                    int affectedRows = ps.executeUpdate();
                    if (affectedRows > 0) {
                        mensaje.setText("Producto eliminado exitosamente"); // Mensaje si la eliminación fue exitosa
                    } else {
                        mensaje.setText("No se encontró un producto con el ID especificado"); // Mensaje si no se encontró el producto
                    }
                } catch (SQLException ex) {
                    mensaje.setText("Error al eliminar el producto."); // Mensaje de error en caso de falla en la base de datos
                    ex.printStackTrace();
                }
            }
        });

        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form5(); // Navega de regreso al formulario anterior (form5)
                setVisible(false); // Oculta la ventana actual
            }
        });
    }
}