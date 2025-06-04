package ads.tiendopolis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class GestionController extends BaseController{

    @FXML
    protected void stockView() throws IOException {
        showScene("stock-view");
    }

    @FXML
    protected void crearCuentaView() throws IOException {
        showScene("crearCuenta-view");
    }
    @FXML
    protected void guardarConfig(){
        // implementación pendiente con servicios
    }
    @FXML
    protected void historialView() throws IOException {
        showScene("historial-view");
    }

    @FXML
    protected void notificacionView() throws IOException {
        showScene("notificacion-view");
    }

    @FXML
    protected void devolucionesView() throws IOException {
        showScene("devoluciones-view");
    }
}
