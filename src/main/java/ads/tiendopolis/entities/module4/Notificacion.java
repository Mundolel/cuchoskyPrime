package ads.tiendopolis.entities.module4;

import java.util.Date;

public class Notificacion {
    private Cliente cliente;
    private String tipo;
    private String contenido;
    private Date fechaEnvio;
    private boolean leido;

    // Constructor vacío
    public Notificacion() {
    }

    // Constructor completo
    public Notificacion(Cliente cliente, String tipo, String contenido, Date fechaEnvio, boolean leido) {
        this.cliente = cliente;
        this.tipo = tipo;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
        this.leido = leido;
    }

    // Getters y Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
