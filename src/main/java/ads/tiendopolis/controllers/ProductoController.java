package ads.tiendopolis.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class ProductoController extends BaseController{

    @FXML
    protected void stockView() throws IOException {
        showScene("stock-view");
    }

    @FXML
    private void addCarrito(){
        // por implementar
    }

    @FXML
    private void carritoView() throws IOException {
        showScene("carrito-view");
    }
}
