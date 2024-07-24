import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form8 extends JFrame {
    private JPanel mainPanel;
    private JTextField id;
    private JButton BUSCARButton;
    private JButton REGRESARButton;
    private JLabel nombre;
    private JLabel precio;
    private JLabel stock;
    private JLabel mensaje;

    public form8() {
        setTitle("Buscar Producto");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.getText().equals("")){
                    mensaje.setText("Ingrese un ID");
                }
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10720950";
                String username = "sql10720950";
                String password = "9IN3lSHIrx";
                String query ="select * from zapatos where id_producto = ?";
                try (Connection con = DriverManager.getConnection(url, username, password)) {
                    PreparedStatement ps = con.prepareStatement(query);
                    int id_pro = Integer.parseInt(id.getText());
                    ps.setInt(1, id_pro);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        mensaje.setText("El ID es correcto");
                        nombre.setText("Nombre: " + rs.getString("nombre"));
                        precio.setText("Precio: $" + rs.getString("precio"));
                        stock.setText("Stock: " + rs.getString("stock"));
                    }
                } catch (SQLException ex) {
                    mensaje.setText("Error al buscar el producto.");
                    ex.printStackTrace();
                }
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
