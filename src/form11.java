import javax.swing.*;
import java.awt.*;
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

        nombreLabel = new JLabel("Producto: " + nombre);
        stockLabel = new JLabel("Stock Disponible: " + stock);
        cantidadField = new JTextField(5);
        addButton = new JButton("Añadir");
        cancelButton = new JButton("Cancelar");

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

    private void generarPDF(int cantidad) {
        try {
            Document pdfDocumento = new Document();
            String ruta = System.getProperty("user.home");
            String directorio = ruta + File.separator + "Desktop";
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
                Float precioFloat = precio; // Obtener el precio como Float

                if (precioFloat == null) {
                    precioFloat = 0.0f;  // Asignar un valor predeterminado si el precio es null
                }

                float precio = precioFloat;  // Convertir a primitivo
                float subtotal = cantidadItem * precio;
                total += subtotal;

                pdfDocumento.add(new Paragraph("Nombre: " + nombre));
                pdfDocumento.add(new Paragraph("Cantidad: " + cantidadItem));
                pdfDocumento.add(new Paragraph("Subtotal: " + String.format("%.2f", subtotal)));
                pdfDocumento.add(Chunk.NEWLINE);
            }

            pdfDocumento.add(new Paragraph("Total: " + String.format("%.2f", total)));
            pdfDocumento.close();

            System.out.println("PDF creado en la ruta: " + archivoPDF.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String calcularTotal(int cantidad) {
        return String.format("%.2f", cantidad * precio);
    }
}