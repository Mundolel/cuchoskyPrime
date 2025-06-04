package ads.tiendopolis.entities.module2;

import ads.tiendopolis.entities.module1.Producto;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Item> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        for (Item item : items) {
            if (item.getProducto().getIdProducto().equals(producto.getIdProducto())) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new Item(producto, cantidad));
    }

    public void eliminarProducto(String idProducto) {
        items.removeIf(item -> item.getProducto().getIdProducto().equals(idProducto));
    }

    public float calcularSubtotal() {
        float subtotal = 0;
        for (Item item : items) {
            subtotal += item.getTotal();
        }
        return subtotal;
    }

    public void vaciarCarrito() {
        items.clear();
    }
}
