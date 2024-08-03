import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form14 extends JFrame {
    private JPanel mainPanel;
    private JTextField id;
    private JTextField stock;
    private JButton ACTUALIZARButton;
    private JButton REGRESARButton;
    private JLabel mensaje1;
    private JLabel mensaje2;

    public form14() {
        setTitle("Actualizar Stock");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica que el ID no esté vacío
                if (id.getText().trim().isEmpty()) {
                    mensaje1.setText("El ID esta vacío");
                    mensaje2.setText("");
                    return;
                }

                // Conexión a la base de datos
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680";
                String user = "sql10723680";
                String password = "uNjR5yDxj2";
                Connection con = null;
                PreparedStatement ps = null;

                try {
                    con = DriverManager.getConnection(url, user, password);
                    String sql = "UPDATE zapatos SET stock = ? WHERE id_producto = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, stock.getText());
                    ps.setString(2, id.getText());

                    int a = ps.executeUpdate();
                    if (a > 0) {
                        mensaje1.setText("Se ha modificado: " + a + " stock");
                        mensaje2.setText("Actualización exitosa");
                    } else {
                        mensaje1.setText("Error: No se encontró un producto con el ID");
                        mensaje2.setText("");
                    }
                } catch (SQLException e1) {
                    mensaje1.setText("Error al actualizar los datos.");
                    mensaje2.setText(e1.getMessage());
                } finally {
                    try {
                        if (ps != null) ps.close();
                        if (con != null) con.close();
                    } catch (SQLException ex) {
                        mensaje1.setText("Error al cerrar la conexión");
                        mensaje2.setText(ex.getMessage());
                    }
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