package ads.tiendopolis.entities.module4;

import ads.tiendopolis.entities.module3.Pedido;
import ads.tiendopolis.entities.module5.Devolucion;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Cuenta {
    private List<Direccion> direccionesEnvio;
    private List<MetodoPago> metodosPagoRegistrados;
    private List<Pedido> pedidos;
    private List<Devolucion> devoluciones;
    private List<Notificacion> notificaciones;

    // Constructor vacío
    public Cliente() {
        super(); // Llama al constructor vacío de Cuenta
        this.direccionesEnvio = new ArrayList<>();
        this.metodosPagoRegistrados = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.devoluciones = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
    }

    // Constructor con datos básicos
    public Cliente(String id, String nombre, String correo, String contrasena, boolean activo) {
        super(id, nombre, correo, contrasena, activo);
        this.direccionesEnvio = new ArrayList<>();
        this.metodosPagoRegistrados = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.devoluciones = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
    }

    // Constructor completo con listas
    public Cliente(String id, String nombre, String correo, String contrasena, boolean activo,
                   List<Direccion> direccionesEnvio, List<MetodoPago> metodosPagoRegistrados,
                   List<Pedido> pedidos, List<Devolucion> devoluciones, List<Notificacion> notificaciones) {
        super(id, nombre, correo, contrasena, activo);
        this.direccionesEnvio = direccionesEnvio;
        this.metodosPagoRegistrados = metodosPagoRegistrados;
        this.pedidos = pedidos;
        this.devoluciones = devoluciones;
        this.notificaciones = notificaciones;
    }

    // Getters y setters

    public List<Direccion> getDireccionesEnvio() {
        return direccionesEnvio;
    }

    public void setDireccionesEnvio(List<Direccion> direccionesEnvio) {
        this.direccionesEnvio = direccionesEnvio;
    }

    public List<MetodoPago> getMetodosPagoRegistrados() {
        return metodosPagoRegistrados;
    }

    public void setMetodosPagoRegistrados(List<MetodoPago> metodosPagoRegistrados) {
        this.metodosPagoRegistrados = metodosPagoRegistrados;
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
}
