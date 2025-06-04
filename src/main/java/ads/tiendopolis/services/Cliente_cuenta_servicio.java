package ads.tiendopolis.services;

import ads.tiendopolis.entities.module4.*;
import ads.tiendopolis.entities.module5.Devolucion;

import java.util.ArrayList;
import java.util.List;

public class Cliente_cuenta_servicio extends Cuenta_servicio {

    public Cliente_cuenta_servicio(List<Cuenta> cuentas) {
        super(cuentas); // inicializa la lista de cuentas desde CuentaServicio
    }

    public void agregarMetodoPago(String idCliente, MetodoPago nuevoMetodo) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente != null) {
            cliente.getMetodosPagoRegistrados().add(nuevoMetodo);
        }
    }

    public List<Direccion> obtenerDirecciones(String idCliente) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente != null) {
            return cliente.getDireccionesEnvio();
        }
        return new ArrayList<>();
    }

    public void establecerMetodoPagoPredeterminado(String idCliente, MetodoPago metodo) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente != null) {
            List<MetodoPago> metodos = cliente.getMetodosPagoRegistrados();
            for (MetodoPago m : metodos) {
                m.setEsPredeterminado(false); // desmarcar todos
            }

            for (MetodoPago m : metodos) {
                if (m.equals(metodo)) {
                    m.setEsPredeterminado(true); // marcar el correcto
                    break;
                }
            }
        }
    }

    public List<Devolucion> listarDevoluciones(String idCliente) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente != null) {
            return cliente.getDevoluciones();
        }
        return new ArrayList<>();
    }

    private Cliente buscarClientePorId(String idCliente) {
        for (Cuenta cuenta : this.cuentas) {
            if (cuenta instanceof Cliente && cuenta.getId().equals(idCliente)) {
                return (Cliente) cuenta;
            }
        }
        return null;
    }

    public List<Notificacion> consultarNotificaciones(String idCliente) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente != null) {
            return cliente.getNotificaciones();
        }
        return new ArrayList<>();
    }

    public void marcarComoLeido(String idCliente, Notificacion notificacion) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente != null) {
            for (Notificacion n : cliente.getNotificaciones()) {
                if (n.equals(notificacion)) {
                    n.setLeido(true);
                    break;
                }
            }
        }
    }

}
