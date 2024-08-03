import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
    private JTextField imagePath;
    private JButton AGREGARButton;
    private JLabel mensaje;
    private JButton BORRARButton;
    private JButton REGRESARButton;
    private JButton seleccionarImagenButton;
    private JLabel imagenLabel;

    public form6() {
        setTitle("Agregar Producto");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 700));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        seleccionarImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(form6.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imagePath.setText(selectedFile.getAbsolutePath());
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                    imagenLabel.setIcon(imageIcon);
                }
            }
        });

        AGREGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (id.getText().equals("") || nombre.getText().equals("") || precio.getText().equals("") || stock.getText().equals("") || imagePath.getText().equals("")) {
                    mensaje.setText("Ingrese todos los campos");
                    return;
                }
                float pr = Float.parseFloat(precio.getText());
                String stockText = stock.getText().replace(",", ".");
                int stock_produc = Integer.parseInt(stockText);

                // Save the image to a specific directory
                File imageFile = new File(imagePath.getText());
                File destinationFile = new File("C:\\Users\\User\\IdeaProjects\\ProyectoAnthony\\src\\img\\" + imageFile.getName());
                try {
                    Files.copy(imageFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception ex) {
                    mensaje.setText("Error al guardar la imagen.");
                    ex.printStackTrace();
                    return;
                }

                productos produc = new productos(id.getText(), nombre.getText(), pr, stock_produc);
                String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680";
                String username = "sql10723680";
                String password = "uNjR5yDxj2";
                String query = "insert into zapatos (id_producto, nombre, precio, stock, image_path) values(?,?,?,?,?)";
                try (Connection con = DriverManager.getConnection(url, username, password)) {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, id.getText());
                    ps.setString(2, nombre.getText());
                    ps.setString(3, precio.getText());
                    ps.setString(4, stock.getText());
                    ps.setString(5, destinationFile.getName());
                    mensaje.setText("Producto Registrado");
                    ps.executeUpdate();
                } catch (NumberFormatException e1) {
                    mensaje.setText("Asegúrate de que todos los campos numéricos contengan valores válidos.");
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    mensaje.setText("Producto no Registrado");
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
                imagePath.setText("");
                imagenLabel.setIcon(null);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new form6().setVisible(true);
            }
        });
    }
}

