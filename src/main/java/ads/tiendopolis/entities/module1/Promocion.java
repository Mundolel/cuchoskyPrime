package ads.tiendopolis.entities.module1;

import java.util.List;

public class Promocion {

    private String codigo;
    private Float valor;
    private List<Producto> productos;

    public Promocion(String codigo, String descripcion, Float valor, String fechaExpiracion, int usosMaximos, List<Producto> productos ) {
        this.codigo = codigo;
        this.valor = valor;
        this.productos = productos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }


    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


}
