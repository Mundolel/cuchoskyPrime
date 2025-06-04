package ads.tiendopolis.entities.module4;

public abstract class Cuenta {
    protected String id;
    protected String nombre;
    protected String correo;
    protected String contrasena;
    protected boolean activo; // True: Activa y False: Deshabilitada

    public Cuenta() {

    }

    public Cuenta(String id, String nombre, String correo, String contrasena, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.activo = activo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
