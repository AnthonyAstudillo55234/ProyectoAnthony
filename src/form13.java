import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Clase singleton para manejar el carrito de compras
public class form13 {
    private static form13 instance; // Instancia única de la clase
    private List<CartItem> items; // Lista de ítems en el carrito
    private Map<String, Integer> carrito; // Mapa que almacena la cantidad de cada producto en el carrito
    private Map<String, Float> precios; // Mapa que almacena el precio de cada producto

    // Constructor privado para inicializar la lista y los mapas
    private form13() {
        items = new ArrayList<>();  // Inicializa la lista de ítems
        carrito = new HashMap<>();   // Inicializa el mapa del carrito
        precios = new HashMap<>();   // Inicializa el mapa de precios
    }

    // Método para obtener la instancia única de la clase (singleton)
    public static form13 getInstance() {
        if (instance == null) {
            instance = new form13(); // Crea la instancia si no existe
        }
        return instance; // Retorna la instancia única
    }

    // Método para añadir un ítem al carrito
    public void addItem(String nombre, int cantidad, float precio) {
        if (items == null) {
            items = new ArrayList<>(); // Inicializa la lista si es nula
        }
        items.add(new CartItem(nombre, cantidad, precio)); // Añade el ítem a la lista de ítems
        carrito.put(nombre, carrito.getOrDefault(nombre, 0) + cantidad); // Actualiza la cantidad del producto en el carrito
        precios.put(nombre, precio); // Actualiza el precio del producto
    }

    // Método para obtener la lista de ítems en el carrito
    public List<CartItem> getItems() {
        return items; // Retorna la lista de ítems
    }

    // Método para limpiar los ítems del carrito
    public void clear() {
        if (items != null) {
            items.clear(); // Limpia la lista de ítems
        }
    }

    // Método para obtener el mapa del carrito
    public Map<String, Integer> getCarrito() {
        return carrito; // Retorna el mapa del carrito
    }

    // Método para obtener el mapa de precios
    public Map<String, Float> getPrecios() {
        return precios; // Retorna el mapa de precios
    }

    // Método para limpiar el carrito y los precios
    public void clearCarrito() {
        if (carrito != null) {
            carrito.clear(); // Limpia el mapa del carrito
        }
        if (precios != null) {
            precios.clear(); // Limpia el mapa de precios
        }
    }

    // Clase interna para representar un ítem en el carrito
    public static class CartItem {
        private String nombre; // Nombre del producto
        private int cantidad; // Cantidad del producto
        private float precio; // Precio del producto

        // Constructor de la clase CartItem
        public CartItem(String nombre, int cantidad, float precio) {
            this.nombre = nombre; // Inicializa el nombre del producto
            this.cantidad = cantidad; // Inicializa la cantidad del producto
            this.precio = precio; // Inicializa el precio del producto
        }

        // Método para obtener el nombre del producto
        public String getNombre() {
            return nombre;
        }

        // Método para obtener la cantidad del producto
        public int getCantidad() {
            return cantidad;
        }

        // Método para obtener el precio del producto
        public float getPrecio() {
            return precio;
        }
    }
}
