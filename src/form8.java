import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// Clase que representa un formulario para buscar un producto por su ID
public class form8 extends JFrame {
    private JPanel mainPanel;
    private JTextField id;
    private JButton BUSCARButton;
    private JButton REGRESARButton;
    private JLabel nombre;
    private JLabel precio;
    private JLabel stock;
    private JLabel mensaje;

    // Constructor de la clase
    public form8() {
        setTitle("Buscar Producto"); // Título de la ventana
        setContentPane(mainPanel); // Establece el panel principal del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura el cierre de la aplicación al cerrar la ventana
        setPreferredSize(new Dimension(600, 400)); // Establece el tamaño preferido de la ventana
        pack(); // Ajusta el tamaño de la ventana para que se ajuste a los componentes
        setVisible(true); // Hace visible la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        BUSCARButton.addActionListener(new ActionListener() {
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
                String query = "select * from zapatos where id_producto = ?"; // Consulta SQL para buscar un producto por ID
                try (Connection con = DriverManager.getConnection(url, username, password)) {
                    // Preparación de la consulta
                    PreparedStatement ps = con.prepareStatement(query);
                    int id_pro = Integer.parseInt(id.getText()); // Convierte el ID ingresado a un entero
                    ps.setInt(1, id_pro);
                    // Ejecuta la consulta y procesa los resultados
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        // Si se encuentra un producto, se actualizan los labels con los datos del producto
                        mensaje.setText("El ID es correcto");
                        nombre.setText("Nombre: " + rs.getString("nombre"));
                        precio.setText("Precio: $" + rs.getString("precio"));
                        stock.setText("Stock: " + rs.getString("stock"));
                    } else {
                        // Si no se encuentra un producto, se muestra un mensaje indicando que no existe
                        mensaje.setText("No se encontró un producto con el ID especificado.");
                        nombre.setText("");
                        precio.setText("");
                        stock.setText("");
                    }
                } catch (SQLException ex) {
                    mensaje.setText("Error al buscar el producto."); // Mensaje de error en caso de falla en la base de datos
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
