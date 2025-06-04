package ads.tiendopolis.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class PagoController extends BaseController {
    @FXML
    protected void carritoView() throws IOException {
        showScene("carrito-view");
    }

    @FXML
    protected void resumenPedidoView() throws IOException {
        showScene("resumenPedido-view");
    }
}
