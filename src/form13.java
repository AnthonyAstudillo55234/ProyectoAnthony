import java.util.ArrayList;
import java.util.List;

public class form13 {
    private static form13 instance;
    private List<CartItem> items;

    private form13() {
        items = new ArrayList<>();
    }

    public static form13 getInstance() {
        if (instance == null) {
            instance = new form13();
        }
        return instance;
    }

    public void addItem(String nombre, int cantidad) {
        items.add(new CartItem(nombre, cantidad));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public static class CartItem {
        private String nombre;
        private int cantidad;

        public CartItem(String nombre, int cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }

        public String getNombre() {
            return nombre;
        }

        public int getCantidad() {
            return cantidad;
        }
    }
}
