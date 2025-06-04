package ads.tiendopolis.entities.module1;

import java.util.List;

public class Producto {

    private String idProducto;
    private String nombre;
    private String descripcion;
    private Float precio;
    private int stock;
    private String imagen;

    // Atributos de tipo reseña
    private List<Resena> resenas;
    // Atributos de tipo categoría
    private Categoria categoria;
    // Atributos de tipo inventario
    private Inventario inventario;

    // Constructor
    public Producto(String idProducto, String nombre, String descripcion, Float precio, String imagen, int stock,
                    List<Resena> resenas,
                    Categoria categoria, Inventario inventario) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.resenas = resenas;
        this.categoria = categoria;
        this.inventario = inventario;
    }

    // Getters y Setters
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public float getPromedioCalificacion() {
        if (resenas == null || resenas.isEmpty()) {
            return 0;
        }
        float suma = 0;
        for (Resena r : resenas) {
            suma += r.getCalificacion();
        }
        return suma / resenas.size();
    }
}