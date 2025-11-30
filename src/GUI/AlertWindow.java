package GUI;

import NetworkClasses.GetMacAddress;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.SocketException;
import java.net.UnknownHostException;
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
        try {
            String mac = GetMacAddress.getMacAddress();
            Alert windowAlert =  new Alert(Alert.AlertType.CONFIRMATION);
            windowAlert.setTitle("Continue?");
            windowAlert.setHeaderText("Confirm ping?");
            windowAlert.setContentText("Would you like to continue a ping for " +
                                       PingForm.getTextField() + ".xx " + "on interface: " + mac + "?");
            windowAlert.getDialogPane().getStyleClass().add("my-alert");
            windowAlert.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
            windowAlert.showAndWait();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
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
    public void alertHostFieldEmpty() {
        Alert alertNoHost =  new Alert(Alert.AlertType.WARNING);
        alertNoHost.setTitle("Empty Host Field");
        alertNoHost.setHeaderText("Field Empty");
        alertNoHost.setContentText("Please enter a domain name such as 'iptechtools.com' or 'google.com'.");
        alertNoHost.getDialogPane().getStyleClass().add("my-alert");
        alertNoHost.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        alertNoHost.showAndWait();
    }



}
