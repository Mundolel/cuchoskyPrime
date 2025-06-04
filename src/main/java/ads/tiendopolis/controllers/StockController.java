package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class StockController extends BaseController{

    @FXML
    public void onProductoClick(MouseEvent mouseEvent) throws IOException {
        showScene("producto-view");
    }

    @FXML
    protected void gestionView() throws IOException {
        showScene("gestion-view");
    }

    @FXML
    protected void carritoView() throws IOException {
        showScene("carrito-view");
    }
}
