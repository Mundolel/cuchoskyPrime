package ads.tiendopolis.services;

import ads.tiendopolis.entities.module3.Pedido;
import ads.tiendopolis.entities.module3.Transaccion;
import ads.tiendopolis.entities.module4.Cliente;
import ads.tiendopolis.entities.module4.MetodoPago;

import java.util.Date;

public class Pagos_servicio {

    public void seleccionarMetodoPago(Pedido pedido, MetodoPago metodo) {
        pedido.setMetodoPago(metodo);
    }

    public void mostrarMetodosPago(Cliente cliente) {
        for (MetodoPago metodo : cliente.getMetodosPagoRegistrados()) {
            System.out.println("Método: " + metodo.getTipo() +
                    (metodo.isEsPredeterminado() ? " (Predeterminado)" : ""));
        }
    }

    public float mostrarTotal(Pedido pedido) {
        return pedido.getTotal();
    }

    public void pagar(Pedido pedido) {
        if (pedido.isCancelado()) {
            System.out.println("El pedido está cancelado. No se puede procesar el pago.");
            return;
        }

        MetodoPago metodo = pedido.getMetodoPago();
        if (metodo == null) {
            System.out.println("No hay método de pago seleccionado.");
            return;
        }

        // Simular transacción
        Transaccion transaccion = new Transaccion(pedido.getTotal(), true, new Date());
        System.out.println("Pago exitoso con método: " + metodo.getTipo());
        pedido.setTransaccion(transaccion);
    }
}
