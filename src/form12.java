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

// Clase que extiende JDialog para mostrar el carrito de compras
public class form12 extends JDialog {
    private JTable table; // Tabla para mostrar los ítems en el carrito
    private DefaultTableModel tableModel; // Modelo de la tabla
    private JButton buyButton; // Botón para realizar la compra
    private JButton cancelButton; // Botón para cancelar la acción

    public form12(Frame parent) {
        super(parent, "Carrito de Compras", true); // Configura el diálogo modal
        setSize(400, 300); // Tamaño del diálogo
        setLocationRelativeTo(parent); // Centrar el diálogo respecto al frame padre
        // Configuración del modelo de la tabla
        tableModel = new DefaultTableModel(new Object[]{"Producto", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir la edición de celdas
            }
        };
        // Configuración de la tabla
        table = new JTable(tableModel);
        table.setBackground(Color.LIGHT_GRAY); // Cambiar color de fondo de la tabla
        table.setFont(new Font("Arial black", Font.PLAIN, 12)); // Cambiar fuente y tamaño de la letra de la tabla
        table.setRowHeight(30); // Ajustar la altura de las filas

        // Configuración de los encabezados de la tabla
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial black", Font.BOLD, 14)); // Cambiar tamaño y tipo de letra de los encabezados
        header.setForeground(Color.black); // Cambiar color de la letra de los encabezados

        // Configuración del renderizador de celdas
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER); // Centrar texto en las celdas
        cellRenderer.setForeground(Color.BLACK); // Cambiar color de la letra en las celdas
        cellRenderer.setFont(new Font("Arial", Font.PLAIN, 14)); // Cambiar fuente y tamaño de la letra en las celdas
        table.setDefaultRenderer(Object.class, cellRenderer);

        // Añadir la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Configuración del panel de botones
        JPanel buttonPanel = new JPanel();
        buyButton = new JButton("Comprar");
        buyButton.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar tamaño y tipo de letra del botón
        buyButton.setForeground(Color.WHITE); // Cambiar color de la letra del botón
        buyButton.setBackground(Color.blue); // Cambiar color de fondo del botón

        cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16)); // Cambiar tamaño y tipo de letra del botón
        cancelButton.setForeground(Color.WHITE); // Cambiar color de la letra del botón
        cancelButton.setBackground(Color.RED); // Cambiar color de fondo del botón

        buttonPanel.add(buyButton); // Añadir el botón "Comprar" al panel
        buttonPanel.add(cancelButton); // Añadir el botón "Cancelar" al panel
        add(buttonPanel, BorderLayout.SOUTH); // Añadir el panel de botones al borde inferior del diálogo

        // Añadir acción al botón "Comprar"
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarCompra(); // Llamar al método para realizar la compra
            }
        });

        // Añadir acción al botón "Cancelar"
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar el diálogo sin realizar cambios
            }
        });

        // Cargar los ítems del carrito en la tabla
        loadCartItems();
        setVisible(true); // Hacer visible el diálogo
    }

    // Método para cargar los ítems del carrito en la tabla
    private void loadCartItems() {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (form13.CartItem item : form13.getInstance().getItems()) {
            tableModel.addRow(new Object[]{item.getNombre(), item.getCantidad()}); // Añadir cada ítem a la tabla
        }
    }

    // Método para realizar la compra
    private void realizarCompra() {
        String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10724628";
        String username = "sql10724628";
        String password = "7NShjCC9Ev";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            // Actualizar el stock de cada ítem en la base de datos
            for (form13.CartItem item : form13.getInstance().getItems()) {
                String updateQuery = "UPDATE zapatos SET stock = stock - ? WHERE nombre = ?";
                try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
                    ps.setInt(1, item.getCantidad()); // Establecer la cantidad para reducir el stock
                    ps.setString(2, item.getNombre()); // Establecer el nombre del producto
                    ps.executeUpdate(); // Ejecutar la actualización
                }
            }
            JOptionPane.showMessageDialog(this, "Compra realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            form13.getInstance().clear(); // Limpiar los ítems del carrito
            dispose(); // Cerrar el diálogo
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir el error en caso de excepción
            JOptionPane.showMessageDialog(this, "Error al realizar la compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

