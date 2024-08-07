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

// Clase que extiende JDialog para mostrar un diálogo para añadir un producto al carrito
public class form11 extends JDialog {
    private JLabel nombreLabel; // Etiqueta para mostrar el nombre del producto
    private JLabel stockLabel; // Etiqueta para mostrar el stock disponible
    private JTextField cantidadField; // Campo de texto para ingresar la cantidad
    private JButton addButton; // Botón para añadir el producto al carrito
    private JButton cancelButton; // Botón para cancelar la acción

    private String nombre; // Nombre del producto
    private int stock; // Cantidad de stock disponible
    private float precio; // Precio del producto

    public form11(Frame parent, String nombre, int stock, float precio) {
        super(parent, "Añadir al Carrito", true); // Configura el diálogo modal
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

        // Configuración del campo de texto para la cantidad
        cantidadField = new JTextField(5);
        cantidadField.setFont(new Font("Arial black", Font.PLAIN, 12)); // Cambiar la fuente y tamaño
        cantidadField.setForeground(Color.BLACK); // Cambiar el color de la letra

        // Configuración del botón "Añadir"
        addButton = new JButton("Añadir");
        addButton.setFont(new Font("Arial black", Font.BOLD, 14)); // Cambiar la fuente y tamaño
        addButton.setForeground(Color.WHITE); // Cambiar el color de la letra
        addButton.setBackground(Color.blue); // Cambiar el color de fondo

        // Configuración del botón "Cancelar"
        cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14)); // Cambiar la fuente y tamaño
        cancelButton.setForeground(Color.WHITE); // Cambiar el color de la letra
        cancelButton.setBackground(Color.RED); // Cambiar el color de fondo

        // Añadir la acción al botón "Añadir"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cantidad = Integer.parseInt(cantidadField.getText()); // Obtener la cantidad ingresada
                    if (cantidad <= 0 || cantidad > stock) { // Validar la cantidad
                        JOptionPane.showMessageDialog(form11.this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    form13.getInstance().addItem(nombre, cantidad, precio); // Añadir el ítem al carrito
                    generarPDF(cantidad); // Generar el PDF de la compra
                    dispose(); // Cerrar el diálogo
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(form11.this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Añadir la acción al botón "Cancelar"
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar el diálogo sin hacer cambios
            }
        });

        // Configuración del layout
        setLayout(new GridLayout(4, 2, 10, 10)); // Layout de cuadrícula con espaciado
        add(nombreLabel);
        add(stockLabel);
        add(new JLabel("Cantidad:")); // Etiqueta para el campo de cantidad
        cantidadField.setFont(new Font("Arial black", Font.PLAIN, 12));
        add(cantidadField);
        add(addButton);
        add(cancelButton);

        getContentPane().setBackground(Color.LIGHT_GRAY); // Cambiar color de fondo del panel

        pack(); // Ajustar el tamaño del diálogo al contenido
        setLocationRelativeTo(parent); // Centrar el diálogo respecto al frame padre
        setVisible(true); // Hacer visible el diálogo
    }

    // Método para generar un archivo PDF con los detalles de la compra
    private void generarPDF(int cantidad) {
        try {
            Document pdfDocumento = new Document(); // Crear un nuevo documento PDF
            String ruta = System.getProperty("user.home"); // Obtener el directorio de usuario
            String directorio = ruta + File.separator + "PDF"; // Directorio para guardar el PDF
            File carpeta = new File(directorio);
            if (!carpeta.exists()) {
                carpeta.mkdirs(); // Crear el directorio si no existe
            }
            File archivoPDF = new File(directorio + File.separator + "Factura_Compra.pdf");
            PdfWriter.getInstance(pdfDocumento, new FileOutputStream(archivoPDF)); // Crear el escritor de PDF

            pdfDocumento.open(); // Abrir el documento PDF
            pdfDocumento.add(new Paragraph("Zapatomart")); // Añadir título al PDF

            // Obtener los ítems del carrito y sus detalles
            List<form13.CartItem> items = form13.getInstance().getItems();
            Map<String, Integer> carrito = form13.getInstance().getCarrito();
            Map<String, Float> precios = form13.getInstance().getPrecios();

            if (items == null || carrito == null || precios == null) {
                throw new RuntimeException("El carrito, los precios o los ítems no están inicializados.");
            }

            float total = 0; // Variable para acumular el total

            // Añadir los detalles de cada ítem al PDF
            for (form13.CartItem item : items) {
                String nombre = item.getNombre();
                int cantidadItem = item.getCantidad();
                Float precioFloat = item.getPrecio(); // Obtener el precio como Float

                if (precioFloat == null) {
                    precioFloat = 0.0f;  // Asignar un valor predeterminado si el precio es null
                }

                float subtotal = cantidadItem * precioFloat; // Calcular el subtotal
                total += subtotal; // Acumular el total

                // Añadir detalles al PDF
                pdfDocumento.add(new Paragraph("Nombre: " + nombre));
                pdfDocumento.add(new Paragraph("Cantidad: " + cantidadItem));
                pdfDocumento.add(new Paragraph("Subtotal: $" + String.format("%.2f", subtotal)));
                pdfDocumento.add(Chunk.NEWLINE);
            }

            pdfDocumento.add(new Paragraph("Total: $" + String.format("%.2f", total))); // Añadir el total al PDF
            pdfDocumento.close(); // Cerrar el documento PDF

            System.out.println("PDF creado en la ruta: " + archivoPDF.getAbsolutePath()); // Imprimir la ruta del PDF creado
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir errores si ocurren durante la generación del PDF
        }
    }
}
