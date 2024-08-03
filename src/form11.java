import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class form11 extends JDialog {
    private JLabel nombreLabel;
    private JLabel stockLabel;
    private JTextField cantidadField;
    private JButton addButton;
    private JButton cancelButton;

    private String nombre;
    private int stock;
    private float precio;

    public form11(Frame parent, String nombre, int stock, float precio) {
        super(parent, "Añadir al Carrito", true);
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;

        // Configuración de las etiquetas
        nombreLabel = new JLabel("Producto: " + nombre);
        nombreLabel.setFont(new Font("Arial black", Font.BOLD, 12)); // Cambiar la fuente y tamaño
        nombreLabel.setForeground(Color.black); // Cambiar el color de la letra

        stockLabel = new JLabel("Stock Disponible: " + stock);
        stockLabel.setFont(new Font("Arial black", Font.PLAIN, 12)); // Cambiar la fuente y tamaño
        stockLabel.setForeground(Color.black); // Cambiar el color de la letra

        // Configuración del campo de texto
        cantidadField = new JTextField(5);
        cantidadField.setFont(new Font("Arial black", Font.PLAIN, 12)); // Cambiar la fuente y tamaño
        cantidadField.setForeground(Color.BLACK); // Cambiar el color de la letra

        // Configuración de los botones
        addButton = new JButton("Añadir");
        addButton.setFont(new Font("Arial black", Font.BOLD, 14)); // Cambiar la fuente y tamaño
        addButton.setForeground(Color.WHITE); // Cambiar el color de la letra
        addButton.setBackground(Color.blue); // Cambiar el color de fondo

        cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14)); // Cambiar la fuente y tamaño
        cancelButton.setForeground(Color.WHITE); // Cambiar el color de la letra
        cancelButton.setBackground(Color.RED); // Cambiar el color de fondo

        // Añadir la acción de los botones
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cantidad = Integer.parseInt(cantidadField.getText());
                    if (cantidad <= 0 || cantidad > stock) {
                        JOptionPane.showMessageDialog(form11.this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    form13.getInstance().addItem(nombre, cantidad, precio);
                    generarPDF(cantidad);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(form11.this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Configuración del layout
        setLayout(new GridLayout(4, 2, 10, 10)); // Espaciado entre los elementos
        add(nombreLabel);
        add(stockLabel);
        add(new JLabel("Cantidad:"));
        cantidadField.setFont(new Font("Arial black", Font.PLAIN, 12));
        add(cantidadField);
        add(addButton);
        add(cancelButton);

        getContentPane().setBackground(Color.LIGHT_GRAY); // Cambiar color de fondo del panel

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void generarPDF(int cantidad) {
        try {
            Document pdfDocumento = new Document();
            String ruta = System.getProperty("user.home");
            String directorio = ruta + File.separator + "PDF";
            File carpeta = new File(directorio);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            File archivoPDF = new File(directorio + File.separator + "Factura_Compra.pdf");
            PdfWriter.getInstance(pdfDocumento, new FileOutputStream(archivoPDF));

            pdfDocumento.open();
            pdfDocumento.add(new Paragraph("Zapatomart"));

            List<form13.CartItem> items = form13.getInstance().getItems();
            Map<String, Integer> carrito = form13.getInstance().getCarrito();
            Map<String, Float> precios = form13.getInstance().getPrecios();

            if (items == null || carrito == null || precios == null) {
                throw new RuntimeException("El carrito, los precios o los ítems no están inicializados.");
            }

            float total = 0;

            for (form13.CartItem item : items) {
                String nombre = item.getNombre();
                int cantidadItem = item.getCantidad();
                Float precioFloat = item.getPrecio(); // Obtener el precio como Float

                if (precioFloat == null) {
                    precioFloat = 0.0f;  // Asignar un valor predeterminado si el precio es null
                }

                float subtotal = cantidadItem * precioFloat;
                total += subtotal;

                pdfDocumento.add(new Paragraph("Nombre: " + nombre));
                pdfDocumento.add(new Paragraph("Cantidad: " + cantidadItem));
                pdfDocumento.add(new Paragraph("Subtotal: $" + String.format("%.2f", subtotal)));
                pdfDocumento.add(Chunk.NEWLINE);
            }

            pdfDocumento.add(new Paragraph("Total: $" + String.format("%.2f", total)));
            pdfDocumento.close();

            System.out.println("PDF creado en la ruta: " + archivoPDF.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
