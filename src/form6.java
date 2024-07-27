import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form6 extends JFrame {
    private JPanel mainPanel;
    private JTextField id;
    private JTextField nombre;
    private JTextField precio;
    private JTextField stock;
    private JButton AGREGARButton;
    private JLabel mensaje;
    private JButton BORRARButton;
    private JButton REGRESARButton;

    public form6() {
        setTitle("Agregar Producto");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        AGREGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.getText().equals("") || nombre.getText().equals("") || precio.getText().equals("") || stock.getText().equals("")) {
                    mensaje.setText("Ingrese todos los campos");
                    return;
                }
                float pr = Float.parseFloat(precio.getText());
                String stockText = stock.getText().replace(",", ".");
                int stock_produc = Integer.parseInt(stockText);
                productos produc = new productos(id.getText(), nombre.getText(), pr, stock_produc);
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10722403";
                String username = "sql10722403";
                String password = "4gdmDFBIMd";
                String query ="insert into zapatos (id_producto, nombre, precio, stock) values(?,?,?,?)";
                try(Connection con = DriverManager.getConnection(url, username, password)) {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, id.getText());
                    ps.setString(2, nombre.getText());
                    ps.setString(3, precio.getText());
                    ps.setString(4, stock.getText());
                    mensaje.setText("Producto Registrado");
                    ps.executeUpdate();
                }catch (NumberFormatException e1) {
                        mensaje.setText("Asegúrate de que todos los campos numéricos contengan valores válidos.");
                        e1.printStackTrace();
                }catch (SQLException e1){
                    e1.printStackTrace();
                    mensaje.setText("Producto no Registrados");
                }
            }
        });
        BORRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                nombre.setText("");
                precio.setText("");
                stock.setText("");
                mensaje.setText("");
            }
        });
        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form5();
                setVisible(false);
            }
        });
    }
}
