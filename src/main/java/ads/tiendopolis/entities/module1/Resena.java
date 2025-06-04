package ads.tiendopolis.entities.module1;

import java.util.Date;

public class Resena {

    private int calificacion; // Int (1-10)
    private String comentario;
    private Date fecha;

    // Constructor
    public Resena(int calificacion, String comentario, Date fecha) {
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
