import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class form9 extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;
    private JButton comprarButton;
    private JButton regresarButton;

    public form9() {
        setTitle("Catalogo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Stock", "Nombre", "Precio", "Imagen", "Acción"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return ImageIcon.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(100); // Ajustar la altura de las filas a 100 píxeles
        table.getColumn("Imagen").setCellRenderer(new ImageRenderer());
        table.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        table.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox(), table));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        comprarButton = new JButton("Ver Carrito");
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form12(form9.this);
            }
        });

        regresarButton = new JButton("Regresar");
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form10();
                setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(comprarButton);
        buttonPanel.add(regresarButton);

        add(buttonPanel, BorderLayout.SOUTH);

        fetchProductData();
    }

    public void fetchProductData() {
        String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10722403";
        String username = "sql10722403";
        String password = "4gdmDFBIMd";
        String query = "SELECT stock, nombre, precio, image_path FROM zapatos";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            tableModel.setRowCount(0); // Limpiar datos existentes

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("stock"));
                row.add(rs.getString("nombre"));
                row.add(rs.getString("precio"));

                // Load image
                String imagePath = "C:\\Users\\User\\IdeaProjects\\ProyectoAnthony\\src\\img\\" + rs.getString("image_path");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                row.add(imageIcon);
                row.add("Comprar");
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new form9().setVisible(true);
            }
        });
    }
}

class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            JLabel label = new JLabel((ImageIcon) value);
            label.setHorizontalAlignment(JLabel.CENTER); // Centrar la imagen
            return label;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private String label;
    private JButton button;
    private boolean isPushed;
    private JTable table;

    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // Actions when button is pressed
            int row = table.getEditingRow();
            String nombre = (String) table.getValueAt(row, 1);
            int stock = (int) table.getValueAt(row, 0);
            float precio = Float.parseFloat((String) table.getValueAt(row, 2));
            new form11(null, nombre, stock, precio);
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
