import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form7 extends JFrame {
    private JPanel mainPanel;
    private JTextField id;
    private JButton ELIMINARButton;
    private JButton REGRESARButton;
    private JLabel mensaje;

    public form7() {
        setTitle("Eliminar producto");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.getText().equals("")){
                    mensaje.setText("Ingrese un ID");
                }
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10722403";
                String username = "sql10722403";
                String password = "4gdmDFBIMd";
                String query ="delete from zapatos where id_producto = ?";
                try (Connection con = DriverManager.getConnection(url, username, password)) {
                    PreparedStatement ps = con.prepareStatement(query);
                    int id_pro = Integer.parseInt(id.getText());
                    ps.setInt(1, id_pro);
                    int affectedRows = ps.executeUpdate();
                    if (affectedRows > 0) {
                        mensaje.setText("Producto eliminado exitosamente");
                    } else {
                        mensaje.setText("No se encontró un producto con el ID especificado");
                    }
                } catch (SQLException ex) {
                    mensaje.setText("Error al eliminar el producto.");
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
