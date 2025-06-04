package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public abstract class BaseController {
    @FXML
    protected Pane rootPane;

    protected void showScene(String sceneName) throws IOException {
        // 1) Cargo el nuevo FXML
        Parent newRoot = FXMLLoader.load(
                getClass().getResource("/ads/tiendopolis/" + sceneName + ".fxml")
        );

        // 2) Construyo la nueva Scene a partir del root cargado
        javafx.scene.Scene nuevaEscena = new javafx.scene.Scene(newRoot);

        // 3) Cargo la hoja de estilo (CSS). Asegúrate de que "application.css" esté en
        //    el mismo paquete "ads/tiendopolis" o ajusta la ruta si está en otro lugar.
        String css = getClass().getResource("/ads/tiendopolis/application.css").toExternalForm();
        nuevaEscena.getStylesheets().add(css);

        // 4) Obtengo el Stage activo, a través del rootPane inyectado
        Stage stage = (Stage) rootPane.getScene().getWindow();

        // 5) Le asigno la nueva escena y muestro
        stage.setScene(nuevaEscena);
        stage.show();
    }
}
