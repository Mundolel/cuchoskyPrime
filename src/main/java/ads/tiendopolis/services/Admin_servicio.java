package ads.tiendopolis.services;

import ads.tiendopolis.entities.module1.Inventario;
import ads.tiendopolis.entities.module1.Producto;
import ads.tiendopolis.entities.module3.Pedido;
import ads.tiendopolis.entities.module4.Cliente;
import ads.tiendopolis.entities.module4.Notificacion;
import ads.tiendopolis.entities.module5.Devolucion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Admin_servicio {

    private Inventario inventario;
    private List<Pedido> pedidos;
    private List<Devolucion> devoluciones;
    private List<Notificacion> notificaciones;

    public Admin_servicio() {
        inventario = new Inventario();
        pedidos = new ArrayList<>();
        devoluciones = new ArrayList<>();
        notificaciones = new ArrayList<>();
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Devolucion> getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(List<Devolucion> devoluciones) {
        this.devoluciones = devoluciones;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }


    public void añadirProducto(Producto producto) {
        inventario.getProductos().add(producto);
    }

    public void actualizarEstadoDevolucion(String idPedido, boolean nuevoEstado) {
        for (Devolucion d : devoluciones) {
            if (d.getPedido().getIdPedido().equals(idPedido)) {
                d.setEstado(nuevoEstado);
                break;
            }
        }
    }

    public void actualizarEstadoEnvio(String idPedido, boolean nuevoEstado) {
        for (Pedido p : pedidos) {
            if (p.getIdPedido().equals(idPedido)) {
                if (p.getEnvio() != null) {
                    p.getEnvio().setEstado(nuevoEstado);
                }
                break;
            }
        }
    }

    public void enviarNotificacion(Cliente cliente, String tipo, String contenido) {
        Notificacion n = new Notificacion();
        n.setCliente(cliente);
        n.setTipo(tipo);
        n.setContenido(contenido);
        n.setFechaEnvio(new Date());
        n.setLeido(false);

        cliente.getNotificaciones().add(n);
        notificaciones.add(n);
    }
}
