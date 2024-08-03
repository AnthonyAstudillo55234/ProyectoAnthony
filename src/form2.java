import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form2 extends JFrame {
    private JPanel mainPanel;
    private JTextField usuario;
    private JTextField contrasenia;
    private JButton INGRESARButton;
    private JLabel mensaje;
    private JButton REGRESARButton;

    public form2() {
        setTitle("Login Administrador");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680";
                String username = "sql10723680";
                String password = "uNjR5yDxj2";
                Connection conn = null;
                PreparedStatement ps = null;
                String query = "select * from administradores where nombre = ? and contrasenia_adm = ?";
                try {
                    conn = DriverManager.getConnection(url,username,password);
                    ps = conn.prepareStatement(query);
                    ps.setString(1,usuario.getText());
                    ps.setString(2,contrasenia.getText());
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                        mensaje.setText("Inicio de Sesion Exitoso");
                        form5 menuAdmin = new form5();
                        menuAdmin.setVisible(true);
                        setVisible(false);
                    }else {
                        mensaje.setText("Datos Incorrectos");
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form1();
                setVisible(false);
            }
        });
    }
}
