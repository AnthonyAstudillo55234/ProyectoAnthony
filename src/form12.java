import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form12 extends JDialog {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton buyButton;
    private JButton cancelButton;

    public form12(Frame parent) {
        super(parent, "Carrito de Compras", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        tableModel = new DefaultTableModel(new Object[]{"Producto", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir la edición de celdas
            }
        };

        table = new JTable(tableModel);
        table.setBackground(Color.LIGHT_GRAY); // Cambiar color de fondo de la tabla
        table.setFont(new Font("Arial black", Font.PLAIN, 12)); // Cambiar fuente y tamaño de la letra de la tabla
        table.setRowHeight(30); // Ajustar la altura de las filas

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial black", Font.BOLD, 14)); // Cambiar tamaño y tipo de letra de los encabezados
        header.setForeground(Color.black);

        // Cambiar el color y la fuente de las celdas de la tabla
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER); // Centrar texto en celdas
        cellRenderer.setForeground(Color.BLACK); // Cambiar color de la letra en las celdas
        cellRenderer.setFont(new Font("Arial", Font.PLAIN, 14)); // Cambiar fuente y tamaño de la letra en las celdas
        table.setDefaultRenderer(Object.class, cellRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buyButton = new JButton("Comprar");
        buyButton.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar tamaño y tipo de letra del botón
        buyButton.setForeground(Color.WHITE); // Cambiar color de la letra del botón
        buyButton.setBackground(Color.blue); // Cambiar color de fondo del botón

        cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar tamaño y tipo de letra del botón
        cancelButton.setForeground(Color.WHITE); // Cambiar color de la letra del botón
        cancelButton.setBackground(Color.RED); // Cambiar color de fondo del botón

        buttonPanel.add(buyButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarCompra();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        loadCartItems();
        setVisible(true);
    }

    private void loadCartItems() {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (form13.CartItem item : form13.getInstance().getItems()) {
            tableModel.addRow(new Object[]{item.getNombre(), item.getCantidad()});
        }
    }

    private void realizarCompra() {
        String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10723680";
        String username = "sql10723680";
        String password = "uNjR5yDxj2";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            for (form13.CartItem item : form13.getInstance().getItems()) {
                String updateQuery = "UPDATE zapatos SET stock = stock - ? WHERE nombre = ?";
                try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
                    ps.setInt(1, item.getCantidad());
                    ps.setString(2, item.getNombre());
                    ps.executeUpdate();
                }
            }
            JOptionPane.showMessageDialog(this, "Compra realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            form13.getInstance().clear();
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al realizar la compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
