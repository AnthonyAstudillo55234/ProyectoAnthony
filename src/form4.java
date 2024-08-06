import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
        setTitle("Registrar Datos");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 700));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cedula.getText().equals("") || nombre.getText().equals("") || apellido.getText().equals("") || telefono.getText().equals("") || correo.getText().equals("") || edad.getText().equals("") || contrasenia.getText().equals("")){
                    mensaje.setText("Llena todos los campos");
                    return;
                }

                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680";
                String username = "sql10723680";
                String password = "uNjR5yDxj2";

                try(Connection con = DriverManager.getConnection(url, username, password)) {
                    // Verificar si ya existe un registro con la misma cédula
                    String checkQuery = "SELECT * FROM clientes WHERE cedula = ?";
                    PreparedStatement checkStmt = con.prepareStatement(checkQuery);
                    checkStmt.setString(1, cedula.getText());
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next()) {
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
                        String hashedPassword = encriptado.generateHash(contrasenia.getText());
                        ps.setString(7, hashedPassword);
                        ps.executeUpdate();
                        mensaje.setText("Datos Registrados");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    mensaje.setText("Datos no Registrados");
                }
            }
        });

        REGRESARELINICIODEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form3();
                setVisible(false);
            }
        });

        BORRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    public static void main(String[] args) {
        new form4();
    }
}
