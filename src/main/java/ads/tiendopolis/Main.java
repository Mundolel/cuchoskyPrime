package ads.tiendopolis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Parent algo = FXMLLoader.load(getClass().getResource("algo-view.fxml"));
        Scene scene = new Scene(root, 800, 600);
        Scene scene1 = new Scene(algo, 800, 600);
        String css = this.getClass().getResource("application.css").toExternalForm();
        stage.setTitle("CUCHOSKY TE AMOOOOOO");
        scene.getStylesheets().add(css);
        scene1.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}