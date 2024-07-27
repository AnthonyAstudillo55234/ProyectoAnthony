import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form3 extends JFrame {
    private JPanel mainPanel;
    private JTextField usuario;
    private JTextField contrasenia;
    private JButton INGRESARButton;
    private JLabel mensaje;
    private JButton REGISTRARButton;
    private JButton REGRESARButton;

    public form3() {
        setTitle("Login Cliente");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10722403";
                String username = "sql10722403";
                String password = "4gdmDFBIMd";
                Connection conn = null;
                PreparedStatement ps = null;
                String query = "select * from clientes where cedula = ? and contrasenia = ?";
                try {
                    conn = DriverManager.getConnection(url,username,password);
                    ps = conn.prepareStatement(query);
                    ps.setString(1,usuario.getText());
                    ps.setString(2,contrasenia.getText());
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                        mensaje.setText("Inicio de Sesion Exitoso");
                        form10 menuClient = new form10();
                        menuClient.setVisible(true);
                        setVisible(false);
                    }else {
                        mensaje.setText("Datos Incorrectos");
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form4();
                setVisible(false);
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
