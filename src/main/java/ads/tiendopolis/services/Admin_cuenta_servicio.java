package ads.tiendopolis.services;

import ads.tiendopolis.entities.module4.Admin;
import ads.tiendopolis.entities.module4.Cuenta;
import java.util.List;

public class Admin_cuenta_servicio {

    private List<Cuenta> cuentas;

    public Admin_cuenta_servicio(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void agregarCargo(String idCuenta, String nuevoCargo) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getId().equals(idCuenta)) {
                if (cuenta instanceof Admin) {
                    ((Admin) cuenta).setCargo(nuevoCargo);
                    return;
                } else {
                    throw new IllegalArgumentException("La cuenta no es un administrador.");
                }
            }
        }
        throw new IllegalArgumentException("Cuenta con ID " + idCuenta + " no encontrada.");
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }
}
