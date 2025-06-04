package ads.tiendopolis.services;

import ads.tiendopolis.entities.module4.Cuenta;

import java.util.List;

public class Cuenta_servicio {

    protected List<Cuenta> cuentas;

    public Cuenta_servicio(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void registrarCuenta(Cuenta nuevaCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getCorreo().equals(nuevaCuenta.getCorreo())) {
                throw new IllegalArgumentException("Ya existe una cuenta con este correo.");
            }
        }
        cuentas.add(nuevaCuenta);
    }

    public void actualizarPerfil(Cuenta cuentaActualizada) {
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getId().equals(cuentaActualizada.getId())) {
                cuentas.set(i, cuentaActualizada);
                return;
            }
        }
        throw new IllegalArgumentException("Cuenta no encontrada para actualizar.");
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }
}
