import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buyButton = new JButton("Comprar");
        cancelButton = new JButton("Cancelar");
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
        String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10722403";
        String username = "sql10722403";
        String password = "4gdmDFBIMd";

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