package GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertWindow {

    public void alertWindowPing() {

    Alert alert =  new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Subnet Error");
    alert.setHeaderText("ALERT");
    alert.setContentText("Alert! Please do this thing.");
    alert.getDialogPane().getStyleClass().add("my-alert");
    alert.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
    } else {
    }

  }

}
