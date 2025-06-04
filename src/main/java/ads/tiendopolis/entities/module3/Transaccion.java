package ads.tiendopolis.entities.module3;

import java.util.Date;

public class Transaccion {
    private float monto;
    private boolean estado;
    private Date fecha;

    public Transaccion() {}

    public Transaccion(float monto, boolean estado, Date fecha) {
        this.monto = monto;
        this.estado = estado;
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}