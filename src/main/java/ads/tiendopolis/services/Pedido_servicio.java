package ads.tiendopolis.services;

import ads.tiendopolis.entities.module1.Producto;
import ads.tiendopolis.entities.module2.Item;
import ads.tiendopolis.entities.module2.Carrito;
import ads.tiendopolis.entities.module3.Pedido;

import java.util.Date;
import java.util.List;

public class Pedido_servicio {

    private List<Pedido> pedidos;

    public Pedido_servicio(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void confirmarPedido(Pedido pedido) {
        if (pedido == null || pedido.getCarrito().getItems() == null || pedido.getCarrito().getItems().isEmpty()) {
            throw new IllegalArgumentException("No se puede confirmar un pedido vacío.");
        }

        pedido.setFechaCreacion(new Date());
        pedido.setCancelado(false);
        pedidos.add(pedido);
    }

    public void aplicarPromocion(Pedido pedido, float porcentajeDescuento) {
        float totalActual = pedido.getTotal();
        float descuento = totalActual * (porcentajeDescuento / 100);
        pedido.setTotal(totalActual - descuento);
    }

    public void procesarTransaccion(Pedido pedido) {
        // Simulación de transacción exitosa
        pedido.setCancelado(false);
    }

    public String obtenerEstado(Pedido pedido) {
        if (pedido.isCancelado()) {
            return "Cancelado";
        } else {
            return "En proceso / Confirmado";
        }
    }

    public void cancelarPedido(String idPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido().equals(idPedido)) {
                pedido.setCancelado(true);
                return;
            }
        }
        throw new IllegalArgumentException("Pedido no encontrado para cancelar.");
    }

    public void actualizarStock(Pedido pedido) {
        for (Item item : pedido.getCarrito().getItems()) {
            Producto producto = item.getProducto();
            int nuevoStock = producto.getStock() - item.getCantidad();
            if (nuevoStock < 0) {
                throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getNombre());
            }
            producto.setStock(nuevoStock);
        }
    }

    public float calcularTotal(Pedido pedido) {
        float total = 0;
        for (Item item : pedido.getCarrito().getItems()) {
            total += item.getTotal();
        }
        pedido.setTotal(total);
        return total;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
