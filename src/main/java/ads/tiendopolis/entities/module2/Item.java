package ads.tiendopolis.entities.module2;

import ads.tiendopolis.entities.module1.Producto;

public class Item {
    private Producto producto;
    private int cantidad;
    private float precioUnitario;

    // Constructor
    public Item(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio(); // se guarda al momento
    }

    // Getters y setters
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public float getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(float precioUnitario) { this.precioUnitario = precioUnitario; }

    public float getTotal() {
        return precioUnitario * cantidad;
    }
}
