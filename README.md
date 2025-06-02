# cuchoskyPrime
Repositorio con los secretos de estado que guarda nuestro queridisimo profesor

Template de modulos:
## // ============= MODULO 1: PRODUCTOS Y CATÁLOGO =============

public class Producto {
    private String idProducto;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Integer stock;
    private List<String> imagenes;
    private String fechaCreacion;
}

public class Categoria {
    private String nombre;
    private String descripcion;
}

public class Inventario {
    private List<Producto> productos;
    private int alertaStockMinimo;
}

public class Reseña {
    private int calificacion; // 1-10
    private String comentario;
    private String fecha;
}

public class Promocion {
    private String codigo;
    private String descripcion;
    private Float valor;
    private String fechaExpiracion;
    private int usosMaximos;
}

## // ============= MODULO 2: CARRITO DE COMPRAS =============

public class Carrito {
    private Float subtotal;
}

public class Item {
    private int cantidad;
    private Float precioUnitario;
}

## // ============= MODULO 3: PEDIDOS Y PAGOS =============

public class Pedido {
    private Float total;
    private Direccion direccionEnvio;
    private String metodoEnvio;
    private String estado;
    private String fechaCreacion;
}

public class Factura {
    private String detallesPago;
    private Float total;
    private String fechaEmision;
}

public class Transaccion {
    private Float monto;
    private Boolean estado;
    private String fecha;
    private int codigoError;
}

## // ============= MODULO 4: ENVÍO Y LOGÍSTICA =============

public class Envio {
    private String guiaSeguimiento;
    private String estado;
    private String fechaEstimadaEntrega;
}

public class ProveedorEnvio {
    private String nombre;
    private List<TarifaEnvio> tarifas;
    private List<String> cobertura;
}

public class TarifaEnvio {
    private String tipoEnvio;
    private Float costoBase;
    private Float costoPorKilometro;
}

## // ============= MODULO 5: DEVOLUCIONES Y REEMBOLSOS =============

public class Devolucion {
    private List<Item> productosDevueltos;
    private String motivo;
    private String estado;
}

public class Reembolso {
    private String motivo;
    private Boolean estado;
    private Float montoReintegrado;
    private String fechaSolicitud;
}

## // ============= MODULO 6: USUARIO Y AUTENTICACIÓN =============

public class Usuario {
    private String idUsuario;
    private String nombre;
    private String correo;
    private String contraseña; // cifrada
    private List<Direccion> direccionesEnvio;
    private List<MetodoPago> metodosPago;
    private Boolean activo;
    private String fechaRegistro;
}

public class Autenticacion {
    private String tokenSesion;
    private String fechaExpiracionToken;
}

public class Direccion {
    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;
    private Boolean esPrincipal;
}

public class MetodoPagoRegistrado {
    private String tipo;
    private String datosCifrados;
    private String fechaExpiracion;
    private Boolean esPredeterminado;
}

## // ============= MODULO 7: SOPORTE Y COMUNICACIÓN =============

public class SoporteCliente {
    private String idTicket;
    private String idUsuario;
    private String asunto;
    private String descripcion;
    private Boolean estado;
    private String fechaCreacion;
}

public class Notificacion {
    private String tipo;
    private String contenido;
    private String fechaEnvio;
    private Boolean estado;
}

public class Reporte {
    private String idReporte;
    private String tipo;
    private String datos;
    private String fechaGeneracion;
}
