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

// Clase que representa un formulario para agregar un nuevo producto
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
        setTitle("Agregar Producto"); // Título de la ventana
        setContentPane(mainPanel); // Establece el panel principal del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura el cierre de la aplicación al cerrar la ventana
        setPreferredSize(new Dimension(600, 700)); // Establece el tamaño preferido de la ventana
        pack(); // Ajusta el tamaño de la ventana para que se ajuste a los componentes
        setVisible(true); // Hace visible la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        seleccionarImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); // Crea un selector de archivos
                int result = fileChooser.showOpenDialog(form6.this); // Muestra el selector de archivos
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Si el usuario selecciona un archivo, obtiene su ruta
                    File selectedFile = fileChooser.getSelectedFile();
                    imagePath.setText(selectedFile.getAbsolutePath());
                    // Muestra una vista previa de la imagen seleccionada
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                    imagenLabel.setIcon(imageIcon);
                }
            }
        });

        AGREGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si todos los campos están llenos
                if (id.getText().equals("") || nombre.getText().equals("") || precio.getText().equals("") || stock.getText().equals("") || imagePath.getText().equals("")) {
                    mensaje.setText("Ingrese todos los campos");
                    return;
                }
                // Convierte el precio y el stock a sus tipos de datos correspondientes
                float pr = Float.parseFloat(precio.getText());
                String stockText = stock.getText().replace(",", ".");
                int stock_produc = Integer.parseInt(stockText);
                // Guarda la imagen seleccionada en un directorio específico
                File imageFile = new File(imagePath.getText());
                File destinationFile = new File("C:\\Users\\User\\IdeaProjects\\ProyectoAnthony\\src\\img\\" + imageFile.getName());
                try {
                    Files.copy(imageFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception ex) {
                    mensaje.setText("Error al guardar la imagen.");
                    ex.printStackTrace();
                    return;
                }
                // Crea un objeto producto con los datos ingresados
                productos produc = new productos(id.getText(), nombre.getText(), pr, stock_produc);
                // Conexión a la base de datos
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
                    ps.executeUpdate(); // Ejecuta la inserción en la base de datos
                    mensaje.setText("Producto Registrado"); // Muestra un mensaje de éxito
                } catch (NumberFormatException e1) {
                    mensaje.setText("Asegúrate de que todos los campos numéricos contengan valores válidos.");
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    mensaje.setText("Producto no Registrado"); // Muestra un mensaje de error si ocurre algún problema
                }
            }
        });

        BORRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpia todos los campos de texto y el mensaje
                id.setText("");
                nombre.setText("");
                precio.setText("");
                stock.setText("");
                imagePath.setText("");
                imagenLabel.setIcon(null); // Limpia la vista previa de la imagen
                mensaje.setText("");
            }
        });

        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form5(); // Abre el formulario anterior (form5)
                setVisible(false); // Oculta la ventana actual
            }
        });
    }
    // Método principal para ejecutar el formulario de agregar producto
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new form6().setVisible(true);
            }
        });
    }
}

