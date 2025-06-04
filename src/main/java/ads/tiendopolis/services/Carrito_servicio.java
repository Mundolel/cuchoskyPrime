package ads.tiendopolis.services;

import ads.tiendopolis.entities.module1.Producto;
import ads.tiendopolis.entities.module2.Carrito;
import ads.tiendopolis.entities.module2.Item;

import java.util.List;

public class Carrito_servicio {

    private Carrito carrito;

    public Carrito_servicio() {
        this.carrito = new Carrito();
    }

    public void agregarProductoAlCarrito(Producto producto, int cantidad) {
        if (producto.getStock() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock del producto: " + producto.getNombre());
        }
        carrito.agregarProducto(producto, cantidad);
    }

    public void eliminarProducto(String idProducto) {
        carrito.eliminarProducto(idProducto);
    }

    public void modificarCantidad(String idProducto, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            eliminarProducto(idProducto);
            return;
        }

        for (Item item : carrito.getItems()) {
            if (item.getProducto().getIdProducto().equals(idProducto)) {
                if (item.getProducto().getStock() < nuevaCantidad) {
                    throw new IllegalArgumentException("Stock insuficiente para modificar la cantidad.");
                }
                item.setCantidad(nuevaCantidad);
                return;
            }
        }
    }

    public List<Item> verResumenCarrito() {
        return carrito.getItems();
    }

    public float calcularSubTotal() {
        return carrito.calcularSubtotal();
    }

    public void vaciarCarrito() {
        carrito.vaciarCarrito();
    }

    public boolean estaVacio() {
        return carrito.getItems().isEmpty();
    }

    public Carrito getCarrito() {
        return carrito;
    }
}