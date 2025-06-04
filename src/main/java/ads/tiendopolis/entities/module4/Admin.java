package ads.tiendopolis.entities.module4;

public class Admin extends Cuenta{
    private String cargo;

    public Admin(String id, String nombre, String correo, String contrasena, boolean activo, String cargo) {
        super(id, nombre, correo, contrasena, activo);
        this.cargo = cargo;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
