import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// Clase que representa un formulario de login para clientes
public class form3 extends JFrame {
    private JPanel mainPanel;
    private JTextField usuario;
    private JTextField contrasenia;
    private JButton INGRESARButton;
    private JLabel mensaje;
    private JButton REGISTRARButton;
    private JButton REGRESARButton;

    public form3() {
        // Configuración inicial del JFrame
        setTitle("Login Cliente"); // Título de la ventana
        setContentPane(mainPanel); // Establece el panel principal del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura el cierre de la aplicación al cerrar la ventana
        setPreferredSize(new Dimension(600, 400)); // Establece el tamaño preferido de la ventana
        pack(); // Ajusta el tamaño de la ventana para que se ajuste a los componentes
        setVisible(true); // Hace visible la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Configuración de la conexión a la base de datos
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10724628";
                String username = "sql10724628";
                String password = "7NShjCC9Ev";
                Connection conn = null;
                PreparedStatement ps = null;
                // Consulta SQL para verificar las credenciales del cliente
                String query = "select * from clientes where cedula = ? and contrasenia = ?";
                try {
                    // Establece la conexión con la base de datos
                    conn = DriverManager.getConnection(url, username, password);
                    // Prepara la consulta SQL
                    ps = conn.prepareStatement(query);
                    // Asigna el valor del campo de texto de usuario (cedula) al primer parámetro de la consulta
                    ps.setString(1, usuario.getText());
                    // Encripta la contraseña ingresada antes de verificarla
                    String hashedPassword = encriptado.generateHash(contrasenia.getText());
                    ps.setString(2, hashedPassword);
                    // Ejecuta la consulta y obtiene el resultado
                    ResultSet rs = ps.executeQuery();
                    // Verifica si las credenciales son correctas
                    if (rs.next()) {
                        mensaje.setText("Inicio de Sesion Exitoso");
                        // Si las credenciales son correctas, abre el menú de cliente (form10)
                        form10 menuClient = new form10();
                        menuClient.setVisible(true);
                        setVisible(false); // Oculta la ventana actual
                    } else {
                        // Si las credenciales son incorrectas, muestra un mensaje de error
                        mensaje.setText("Datos Incorrectos");
                    }
                } catch (SQLException e1) {
                    // Imprime cualquier error de SQL en la consola
                    e1.printStackTrace();
                }
            }
        });
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre el formulario de registro (form4) para nuevos clientes
                new form4();
                setVisible(false); // Oculta la ventana actual
            }
        });

        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Regresa al formulario anterior (form1)
                new form1();
                setVisible(false); // Oculta la ventana actual
            }
        });
    }
}
