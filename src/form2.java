import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// Clase que representa un formulario de login para administradores
public class form2 extends JFrame {
    private JPanel mainPanel;
    private JTextField usuario;
    private JTextField contrasenia;
    private JButton INGRESARButton;
    private JLabel mensaje;
    private JButton REGRESARButton;

    public form2() {
        // Configuración inicial del JFrame
        setTitle("Login Administrador"); // Título de la ventana
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
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680";
                String username = "sql10723680";
                String password = "uNjR5yDxj2";
                Connection conn = null;
                PreparedStatement ps = null;
                // Consulta SQL para verificar las credenciales del administrador
                String query = "select * from administradores where nombre = ? and contrasenia_adm = ?";

                try {
                    // Establece la conexión con la base de datos
                    conn = DriverManager.getConnection(url, username, password);
                    // Prepara la consulta SQL
                    ps = conn.prepareStatement(query);
                    // Asigna los valores de los campos de texto a los parámetros de la consulta
                    ps.setString(1, usuario.getText());
                    ps.setString(2, contrasenia.getText());
                    // Ejecuta la consulta y obtiene el resultado
                    ResultSet rs = ps.executeQuery();
                    // Verifica si las credenciales son correctas
                    if (rs.next()) {
                        mensaje.setText("Inicio de Sesion Exitoso");
                        // Si las credenciales son correctas, abre el menú de administrador (form5)
                        form5 menuAdmin = new form5();
                        menuAdmin.setVisible(true);
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
