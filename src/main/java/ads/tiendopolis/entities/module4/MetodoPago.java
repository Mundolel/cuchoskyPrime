package ads.tiendopolis.entities.module4;

public class MetodoPago {
    private String tipo;
    private boolean esPredeterminado;

    public MetodoPago(String tipo, boolean esPredeterminado) {
        this.tipo = tipo;
        this.esPredeterminado = esPredeterminado;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public boolean isEsPredeterminado() { return esPredeterminado; }
    public void setEsPredeterminado(boolean esPredeterminado) { this.esPredeterminado = esPredeterminado; }
}
