package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController extends BaseController{
    @FXML
    private Label welcomeText;

    @FXML
    protected void algoView() throws IOException {
        showScene("algo-view");
    }
}