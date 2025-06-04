package ads.tiendopolis.services;

import ads.tiendopolis.entities.module1.Categoria;
import ads.tiendopolis.entities.module1.Inventario;
import ads.tiendopolis.entities.module1.Producto;
import ads.tiendopolis.entities.module2.Item;
import ads.tiendopolis.entities.module3.Pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producto_servicio {

    private Inventario inventario;

    public Producto_servicio(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<Producto> listarProductos() {
        return inventario.getProductos();
    }

    public List<Producto> buscarProductoPorNombre(String nombreProducto) {
        List<Producto> resultado = new ArrayList<>();
        List<Producto> productos = inventario.getProductos();

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    public List<Producto> filtrarPorCategoria(Categoria categoria) {
        List<Producto> resultado = new ArrayList<>();
        List<Producto> productos = inventario.getProductos();

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getCategoria().equals(categoria)) {
                resultado.add(p);
            }
        }

        return resultado;
    }

    public Producto verDetalleProducto(Producto producto) {
        List<Producto> productos = inventario.getProductos();

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getIdProducto().equals(producto.getIdProducto())) {
                return p;
            }
        }

        return null;
    }

    public List<Producto> ordenarProductos(String criterio, String nombreFiltro) {
        List<Producto> productos = buscarProductoPorNombre(nombreFiltro);

        if (criterio.equalsIgnoreCase("calificacion")) {
            bubbleSortPorCalificacion(productos);
        } else if (criterio.equalsIgnoreCase("precio")) {
            bubbleSortPorPrecio(productos);
        }

        return productos;
    }

    public boolean verificarDisponibilidad(Producto producto) {
        Producto encontrado = verDetalleProducto(producto);
        if (encontrado != null && encontrado.getStock() > 0) {
            return true;
        }
        return false;
    }

    private void bubbleSortPorPrecio(List<Producto> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                float precio1 = lista.get(j).getPrecio();
                float precio2 = lista.get(j + 1).getPrecio();
                if (precio1 > precio2) {
                    Producto temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }


    private void bubbleSortPorCalificacion(List<Producto> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                double calif1 = lista.get(j).getPromedioCalificacion();
                double calif2 = lista.get(j + 1).getPromedioCalificacion();
                if (calif1 > calif2) {
                    Producto temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}