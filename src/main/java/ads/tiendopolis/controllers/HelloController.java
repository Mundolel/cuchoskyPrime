package ads.tiendopolis.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class HelloController extends BaseController{


    @FXML
    protected void algoView() throws IOException {
        showScene("algo-view");
    }

    @FXML
    protected void testView() throws IOException {
        showScene("test-view");
    }

    @FXML
    protected void productView() throws IOException {
        showScene("producto-view");
    }
    @FXML
    protected void carritoView() throws IOException {
        showScene("carrito-view");
    }

}