package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;

public class IniciarController extends BaseController{

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    protected void crearCuentaView() throws IOException {
        showScene("crearCuenta-view");
    }

    @FXML
    protected void auth() throws IOException {
        //implementar con el back
        String user = txtUsername.getText();
        String pass = txtPassword.getText();

        if(user.equals("admin") && pass.equals("admin")){
            showScene("admin-view");
        } else if (user.equals(("user")) && pass.equals("user")) {
            showScene("stock-view");
        }else{
            showInfoAlert("Notificación", "Autenticación inválida");
        }
    }

    private void showInfoAlert(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        a.setTitle(title);
        a.showAndWait();
    }
}
