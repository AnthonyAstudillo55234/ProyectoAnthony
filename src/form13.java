import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class form13 {
    private static form13 instance;
    private List<CartItem> items;
    private Map<String, Integer> carrito;
    private Map<String, Float> precios;

    private form13() {
        items = new ArrayList<>();  // Inicializa la lista
        carrito = new HashMap<>();
        precios = new HashMap<>();
    }

    public static form13 getInstance() {
        if (instance == null) {
            instance = new form13();
        }
        return instance;
    }

    public void addItem(String nombre, int cantidad, float precio) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(new CartItem(nombre, cantidad, precio));
        carrito.put(nombre, carrito.getOrDefault(nombre, 0) + cantidad);
        precios.put(nombre, precio);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clear() {
        if (items != null) {
            items.clear();
        }
    }

    public Map<String, Integer> getCarrito() {
        return carrito;
    }

    public Map<String, Float> getPrecios() {
        return precios;
    }

    public void clearCarrito() {
        if (carrito != null) {
            carrito.clear();
        }
        if (precios != null) {
            precios.clear();
        }
    }

    public static class CartItem {
        private String nombre;
        private int cantidad;
        private float precio;

        public CartItem(String nombre, int cantidad, float precio) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public int getCantidad() {
            return cantidad;
        }

        public float getPrecio() {
            return precio;
        }
    }
}
