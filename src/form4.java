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
                int ed = Integer.parseInt(edad.getText());
                cliente user = new cliente(cedula.getText(), nombre.getText(), apellido.getText(), telefono.getText(), correo.getText(), ed, contrasenia.getText());
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680";
                String username = "sql10723680";
                String password = "uNjR5yDxj2";
                String query ="insert into clientes (cedula, nombre, apellido, telefono, correo, edad, contrasenia) values(?,?,?,?,?,?,?)";
                try(Connection con = DriverManager.getConnection(url, username, password)){
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, cedula.getText());
                    ps.setString(2, nombre.getText());
                    ps.setString(3, apellido.getText());
                    ps.setString(4, telefono.getText());
                    ps.setString(5, correo.getText());
                    ps.setString(6, edad.getText());
                    String hashedPassword = encriptado.generateHash(contrasenia.getText());
                    ps.setString(7, hashedPassword);
                    mensaje.setText("Datos Registrados");
                    ps.executeUpdate();
                }catch (SQLException e1){
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
}
