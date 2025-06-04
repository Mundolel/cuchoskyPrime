package ads.tiendopolis.entities.module3;

import java.util.Date;

public class Envio {
    private int id;
    private boolean estado;
    private Date fechaEntregaEstimada;
    private float costo = 10000;
    private Pedido pedido;
    private String proveedorEnvio;

    public Envio() {}

    public Envio(int id, boolean estado, Date fechaEntregaEstimada, float costo, Pedido pedido, String proveedorEnvio) {
        this.id = id;
        this.estado = estado;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.costo = costo;
        this.pedido = pedido;
        this.proveedorEnvio = proveedorEnvio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(Date fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getProveedorEnvio() {
        return proveedorEnvio;
    }

    public void setProveedorEnvio(String proveedorEnvio) {
        this.proveedorEnvio = proveedorEnvio;
    }
}
