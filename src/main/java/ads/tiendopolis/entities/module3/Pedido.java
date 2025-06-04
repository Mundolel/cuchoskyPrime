package ads.tiendopolis.entities.module3;

import ads.tiendopolis.entities.module2.Carrito;
import ads.tiendopolis.entities.module4.Cliente;
import ads.tiendopolis.entities.module4.Direccion;
import ads.tiendopolis.entities.module4.MetodoPago;

import java.util.Date;

public class Pedido {
    private String idPedido;
    private float total;
    private Date fechaCreacion;
    private boolean cancelado;
    private String metodoEnvio;
    private MetodoPago metodoPago;
    private Cliente cliente;
    private Direccion direccionEnvio;
    private Envio envio;
    private Carrito carrito;
    private Transaccion transaccion;

    public Pedido() {}

    public Pedido(String idPedido, float total, Date fechaCreacion, boolean cancelado, String metodoEnvio,
                  MetodoPago metodoPago, Cliente cliente, Direccion direccionEnvio, Envio envio,
                  Carrito carrito, Transaccion transaccion) {
        this.idPedido = idPedido;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.cancelado = cancelado;
        this.metodoEnvio = metodoEnvio;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.direccionEnvio = direccionEnvio;
        this.envio = envio;
        this.carrito = carrito;
        this.transaccion = transaccion;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public String getMetodoEnvio() {
        return metodoEnvio;
    }

    public void setMetodoEnvio(String metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Direccion getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(Direccion direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }
}