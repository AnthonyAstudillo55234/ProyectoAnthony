import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form11 extends JDialog {
    private JLabel nombreLabel;
    private JLabel stockLabel;
    private JTextField cantidadField;
    private JButton addButton;
    private JButton cancelButton;

    private String nombre;
    private int stock;

    public form11(Frame parent, String nombre, int stock) {
        super(parent, "Añadir al Carrito", true);
        this.nombre = nombre;
        this.stock = stock;

        nombreLabel = new JLabel("Producto: " + nombre);
        stockLabel = new JLabel("Stock Disponible: " + stock);
        cantidadField = new JTextField(5);
        addButton = new JButton("Añadir");
        cancelButton = new JButton("Cancelar");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = Integer.parseInt(cantidadField.getText());
                if (cantidad <= 0 || cantidad > stock) {
                    JOptionPane.showMessageDialog(form11.this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                form13.getInstance().addItem(nombre, cantidad);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLayout(new GridLayout(4, 2));
        add(nombreLabel);
        add(stockLabel);
        add(new JLabel("Cantidad:"));
        add(cantidadField);
        add(addButton);
        add(cancelButton);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
