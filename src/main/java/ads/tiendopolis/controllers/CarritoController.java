package ads.tiendopolis.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class CarritoController extends BaseController{

    @FXML
    protected void stockView() throws IOException{
        showScene("stock-view");
    }

    @FXML
    protected void pagoView() throws IOException{
        showScene("pago-view");
    }
}
