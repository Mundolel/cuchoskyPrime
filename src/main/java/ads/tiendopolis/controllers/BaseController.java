package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BaseController {
    @FXML
    protected AnchorPane contenedor;

    protected void showScene(String sceneName) throws IOException {
        AnchorPane pane = FXMLLoader.load(
                getClass().getResource("/ads/tiendopolis/" + sceneName + ".fxml")
        );
        contenedor.getChildren().setAll(pane);
    }
}