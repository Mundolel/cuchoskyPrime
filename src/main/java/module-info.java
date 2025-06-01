module ads.tiendopolis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens ads.tiendopolis to javafx.fxml;
    exports ads.tiendopolis;
    exports ads.tiendopolis.controllers;
    opens ads.tiendopolis.controllers to javafx.fxml;
}