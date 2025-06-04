package ads.tiendopolis.entities.module5;

import ads.tiendopolis.entities.module2.Item;
import ads.tiendopolis.entities.module3.Pedido;
import ads.tiendopolis.entities.module4.Cliente;

import java.util.Date;
import java.util.List;

public class Devolucion {
    private Pedido pedido;
    private Cliente cliente;
    private List<Item> productosDevueltos;
    private Reembolso reembolso;
    private Date fecha;
    private boolean estado;

    // Constructor completo incluyendo estado
    public Devolucion(Pedido pedido, Cliente cliente, List<Item> productosDevueltos, Reembolso reembolso, Date fecha, boolean estado) {
        this.pedido = pedido;
        this.cliente = cliente;
        this.productosDevueltos = productosDevueltos;
        this.reembolso = reembolso;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Constructor vacío
    public Devolucion() {
    }

    // Getters y Setters
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getProductosDevueltos() {
        return productosDevueltos;
    }

    public void setProductosDevueltos(List<Item> productosDevueltos) {
        this.productosDevueltos = productosDevueltos;
    }

    public Reembolso getReembolso() {
        return reembolso;
    }

    public void setReembolso(Reembolso reembolso) {
        this.reembolso = reembolso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}