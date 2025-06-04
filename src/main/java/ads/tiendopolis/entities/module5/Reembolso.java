package ads.tiendopolis.entities.module5;

import java.util.Date;

public class Reembolso {
    private String motivo;
    private boolean estado;
    private float montoReintegrado;

    // Constructor completo
    public Reembolso(String motivo, boolean estado, float montoReintegrado) {
        this.motivo = motivo;
        this.estado = estado;
        this.montoReintegrado = montoReintegrado;
    }

    // Constructor vacío
    public Reembolso() {
    }

    // Getters y Setters
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public float getMontoReintegrado() {
        return montoReintegrado;
    }

    public void setMontoReintegrado(float montoReintegrado) {
        this.montoReintegrado = montoReintegrado;
    }
}
