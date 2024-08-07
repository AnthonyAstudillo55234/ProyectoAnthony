import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

// Clase principal que extiende JFrame para crear una ventana de catálogo de productos
public class form9 extends JFrame {
    private JTable table; // Tabla para mostrar los productos del catálogo
    private DefaultTableModel tableModel; // Modelo de la tabla para manejar datos y estructura
    private JPanel mainPanel; // Panel principal
    private JButton comprarButton; // Botón para ver el carrito de compras
    private JButton regresarButton; // Botón para regresar a la ventana anterior

    public form9() {
        setTitle("Catalogo"); // Título de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana al pulsar "Cerrar"
        setSize(600, 400); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        // Definir la estructura y tipos de datos de las columnas de la tabla
        tableModel = new DefaultTableModel(new Object[]{"Stock", "Nombre", "Precio", "Imagen", "Acción"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna de "Acción" es editable (para el botón)
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return ImageIcon.class; // La columna de "Imagen" manejará objetos ImageIcon
                }
                return super.getColumnClass(columnIndex);
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(100); // Ajustar la altura de las filas a 100 píxeles
        table.setBackground(Color.lightGray); // Cambiar color de fondo de la tabla

        // Configuración del encabezado de la tabla
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial black", Font.BOLD, 14)); // Cambiar tamaño y tipo de letra de los encabezados
        header.setForeground(Color.black); // Cambiar color de la letra de los encabezados

        // Renderizador para personalizar la fuente y el color de las celdas de la tabla
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Arial black", Font.PLAIN, 12)); // Cambiar tamaño y tipo de letra de las celdas
                c.setForeground(Color.BLACK); // Cambiar color de letra de las celdas
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, cellRenderer); // Aplicar el renderizador a todas las celdas
        table.getColumn("Imagen").setCellRenderer(new ImageRenderer()); // Aplicar renderizador para imágenes
        table.getColumn("Acción").setCellRenderer(new ButtonRenderer()); // Aplicar renderizador para botones
        table.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox(), table)); // Configurar el editor para la columna de botones

        JScrollPane scrollPane = new JScrollPane(table); // Agregar la tabla a un JScrollPane para el desplazamiento
        add(scrollPane, BorderLayout.CENTER); // Colocar el JScrollPane en el centro del panel principal

        // Configuración del botón "Ver Carrito"
        comprarButton = new JButton("Ver Carrito");
        comprarButton.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar tamaño y tipo de letra del botón
        comprarButton.setForeground(Color.WHITE); // Cambiar color de la letra del botón
        comprarButton.setBackground(Color.BLUE); // Cambiar color de fondo del botón
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form12(form9.this); // Abre el formulario del carrito de compras
            }
        });

        // Configuración del botón "Regresar"
        regresarButton = new JButton("Regresar");
        regresarButton.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar tamaño y tipo de letra del botón
        regresarButton.setForeground(Color.WHITE); // Cambiar color de la letra del botón
        regresarButton.setBackground(Color.RED); // Cambiar color de fondo del botón
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form10(); // Abre el formulario anterior
                setVisible(false); // Oculta la ventana actual
            }
        });

        // Panel de botones en la parte inferior
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Colocar los botones en el centro
        buttonPanel.add(comprarButton);
        buttonPanel.add(regresarButton);

        add(buttonPanel, BorderLayout.SOUTH); // Agregar el panel de botones al panel principal

        fetchProductData(); // Cargar los datos de los productos desde la base de datos
    }

    // Método para obtener los datos de productos de la base de datos y mostrarlos en la tabla
    public void fetchProductData() {
        String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680"; // URL de la base de datos
        String username = "sql10723680"; // Usuario de la base de datos
        String password = "uNjR5yDxj2"; // Contraseña de la base de datos
        String query = "SELECT stock, nombre, precio, image_path FROM zapatos"; // Consulta SQL

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            tableModel.setRowCount(0); // Limpiar datos existentes en la tabla

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("stock")); // Agregar la cantidad de stock del producto
                row.add(rs.getString("nombre")); // Agregar el nombre del producto
                row.add(rs.getString("precio")); // Agregar el precio del producto

                // Cargar la imagen desde la ruta almacenada en la base de datos
                String imagePath = "C:\\Users\\User\\IdeaProjects\\ProyectoAnthony\\src\\img\\" + rs.getString("image_path");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                row.add(imageIcon); // Agregar la imagen escalada a la fila
                row.add("Comprar"); // Agregar el texto del botón de compra
                tableModel.addRow(row); // Añadir la fila al modelo de la tabla
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir el error en caso de fallo en la conexión o consulta
        }
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new form9().setVisible(true); // Crear y mostrar la ventana del catálogo
            }
        });
    }
}

// Clase para renderizar imágenes en la tabla
class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            JLabel label = new JLabel((ImageIcon) value);
            label.setHorizontalAlignment(JLabel.CENTER); // Centrar la imagen en la celda
            return label;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

// Clase para renderizar botones en la tabla
class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true); // Hacer que el botón sea opaco para mostrar colores
        setFont(new Font("Arial black", Font.BOLD, 14)); // Cambiar tamaño y tipo de letra del botón en la tabla
        setForeground(Color.black); // Cambiar color de letra del botón
        setBackground(Color.green); // Cambiar color de fondo del botón
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString()); // Mostrar el texto del botón
        return this;
    }
}

// Clase para manejar la acción de los botones en la tabla
class ButtonEditor extends DefaultCellEditor {
    private String label; // Texto del botón
    private JButton button; // El botón en sí
    private boolean isPushed; // Bandera para verificar si el botón fue presionado
    private JTable table; // Referencia a la tabla

    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton();
        button.setOpaque(true);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Cambiar tamaño y tipo de letra del botón en la tabla
        button.setForeground(Color.WHITE); // Cambiar color de letra del botón
        button.setBackground(Color.GREEN); // Cambiar color de fondo del botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Detener la edición cuando se presiona el botón
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString(); // Obtener el texto del botón
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            int row = table.getEditingRow(); // Obtener la fila donde se presionó el botón
            String nombre = (String) table.getValueAt(row, 1); // Obtener el nombre del producto
            int stock = (int) table.getValueAt(row, 0); // Obtener el stock del producto
            float precio = Float.parseFloat((String) table.getValueAt(row, 2)); // Obtener el precio del producto
            new form11(null, nombre, stock, precio); // Abrir el formulario de compra del producto
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false; // Restablecer la bandera cuando se detiene la edición
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped(); // Detener la edición
    }
}


