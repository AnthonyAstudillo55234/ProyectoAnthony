import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// Clase que representa un formulario de registro de datos para clientes
public class form4 extends JFrame {
    private JPanel mainPanel;
    private JTextField cedula;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField telefono;
    private JTextField correo;
    private JTextField edad;
    private JTextField contrasenia;
    private JButton REGISTRARButton;
    private JLabel mensaje;
    private JButton REGRESARELINICIODEButton;
    private JButton BORRARButton;

    public form4() {
        setTitle("Registrar Datos"); // Título de la ventana
        setContentPane(mainPanel); // Establece el panel principal del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura el cierre de la aplicación al cerrar la ventana
        setPreferredSize(new Dimension(600, 700)); // Establece el tamaño preferido de la ventana
        pack(); // Ajusta el tamaño de la ventana para que se ajuste a los componentes
        setVisible(true); // Hace visible la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si todos los campos están llenos
                if (cedula.getText().equals("") || nombre.getText().equals("") || apellido.getText().equals("") || telefono.getText().equals("") || correo.getText().equals("") || edad.getText().equals("") || contrasenia.getText().equals("")){
                    mensaje.setText("Llena todos los campos");
                    return;
                }
                // Configuración de la conexión a la base de datos
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10724628";
                String username = "sql10724628";
                String password = "7NShjCC9Ev";
                // Uso de try-with-resources para asegurar el cierre automático de la conexión
                try(Connection con = DriverManager.getConnection(url, username, password)) {
                    // Verificar si ya existe un registro con la misma cédula
                    String checkQuery = "SELECT * FROM clientes WHERE cedula = ?";
                    PreparedStatement checkStmt = con.prepareStatement(checkQuery);
                    checkStmt.setString(1, cedula.getText());
                    ResultSet rs = checkStmt.executeQuery();
                    if (rs.next()) {
                        // Si ya existe un registro con la misma cédula, muestra un mensaje de error
                        mensaje.setText("Este registro ya existe.");
                    } else {
                        // Si no existe, procedemos a registrar los datos
                        String insertQuery = "INSERT INTO clientes (cedula, nombre, apellido, telefono, correo, edad, contrasenia) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = con.prepareStatement(insertQuery);
                        ps.setString(1, cedula.getText());
                        ps.setString(2, nombre.getText());
                        ps.setString(3, apellido.getText());
                        ps.setString(4, telefono.getText());
                        ps.setString(5, correo.getText());
                        ps.setString(6, edad.getText());
                        // Encripta la contraseña antes de almacenarla en la base de datos
                        String hashedPassword = encriptado.generateHash(contrasenia.getText());
                        ps.setString(7, hashedPassword);
                        ps.executeUpdate(); // Ejecuta la inserción de datos en la base de datos
                        mensaje.setText("Datos Registrados"); // Muestra un mensaje de éxito
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace(); // Imprime cualquier error de SQL en la consola
                    mensaje.setText("Datos no Registrados"); // Muestra un mensaje de error
                }
            }
        });

        REGRESARELINICIODEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form3(); // Abre el formulario de inicio de sesión para clientes (form3)
                setVisible(false); // Oculta la ventana actual
            }
        });

        BORRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpia todos los campos de texto y el mensaje
                cedula.setText("");
                nombre.setText("");
                apellido.setText("");
                telefono.setText("");
                correo.setText("");
                edad.setText("");
                contrasenia.setText("");
                mensaje.setText("");
            }
        });
    }
    // Método principal para ejecutar el formulario de registro
    public static void main(String[] args) {
        new form4();
    }
}

