package ads.tiendopolis.services;

import ads.tiendopolis.entities.module4.Cuenta;

import java.util.List;

public class Autenticacion_servicio {

    private List<Cuenta> cuentas;
    private Cuenta sesionActual;

    public Autenticacion_servicio(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
        this.sesionActual = null;
    }

    public boolean iniciarSesion(String correo, String contrasena) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getCorreo().equals(correo) && cuenta.getContrasena().equals(contrasena)) {
                if (cuenta.isActivo()) {
                    sesionActual = cuenta;
                    return true;
                } else {
                    throw new IllegalStateException("Cuenta inactiva.");
                }
            }
        }
        return false;
    }

    public boolean validarCredenciales(String correo, String contrasena) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getCorreo().equals(correo) && cuenta.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public void cerrarSesion() {
        sesionActual = null;
    }

    public Cuenta getSesionActual() {
        return sesionActual;
    }

    public boolean haySesionActiva() {
        return sesionActual != null;
    }
}
