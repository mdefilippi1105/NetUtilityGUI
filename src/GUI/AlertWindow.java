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
    }
    public void alertWindowConfirm() {

        Alert windowAlert =  new Alert(Alert.AlertType.CONFIRMATION);
        windowAlert.setTitle("Continue?");
        windowAlert.setHeaderText("Confirm ping?");

        windowAlert.setContentText("Would you like to continue a ping for " +
                PingForm.getTextField() + ".xx ?");
        windowAlert.getDialogPane().getStyleClass().add("my-alert");
        windowAlert.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        windowAlert.showAndWait();
    }

    public void alertNoHostSet() {

        Alert alertHost =  new Alert(Alert.AlertType.WARNING);
        alertHost.setTitle("No Host Set");
        alertHost.setHeaderText("No Host Set");
        alertHost.setContentText("Please make sure that a host value is selected.");
        alertHost.getDialogPane().getStyleClass().add("my-alert");
        alertHost.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        alertHost.showAndWait();
    }



}
