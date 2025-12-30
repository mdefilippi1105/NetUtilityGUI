package GUI;

import NetworkClasses.CheckHosts;
import NetworkClasses.GetMacAddress;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Optional;

public class AlertWindow {
   private Alert warning = new Alert(Alert.AlertType.WARNING);
   private Alert error = new Alert(Alert.AlertType.ERROR);
   private Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
   private Alert info = new Alert(Alert.AlertType.INFORMATION);


    public void alertWindowPing() {
        warning.setTitle("Subnet Error");
        warning.setHeaderText("ALERT");
        warning.setContentText("Alert! Please do this thing.");
        warning.getDialogPane().getStyleClass().add("my-alert");
        warning.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        Optional<ButtonType> result = warning.showAndWait();
        }

    public void alertWindowConfirm() {
        try {
            String mac = GetMacAddress.getMacAddress();
            confirmation.setTitle("Continue?");
            confirmation.setHeaderText("Confirm ping?");
            confirmation.setContentText("Would you like to continue a ping for " +
                                       PingForm.getTextField() + ".xx " + "on interface: " + mac + "?");
            confirmation.getDialogPane().getStyleClass().add("my-alert");
            confirmation.getDialogPane().setPrefWidth(450);
            confirmation.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
            Optional<ButtonType> result = confirmation.showAndWait();
//            if (result.get() == ButtonType.CANCEL) {
//                confirmation.close();
//                CheckHosts.cancelPingCount();
//                PingForm.clearSlider();
//            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException();
        }
    }

    public void alertNoHostSet() {

        warning.setTitle("No Host Set");
        warning.setHeaderText("No Host Set");
        warning.setContentText("Please make sure that a host value is selected.");
        warning.getDialogPane().getStyleClass().add("my-alert");
        warning.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        warning.showAndWait();
    }
    public void alertHostFieldEmpty() {
        warning.setTitle("Empty Host Field");
        warning.setHeaderText("Field Empty");
        warning.setContentText("Please enter a domain name such as 'iptechtools.com' or 'google.com'.");
        warning.getDialogPane().getStyleClass().add("my-alert");
        warning.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        warning.showAndWait();
    }

    public void showPingResults(){
        String pingResults = CheckHosts.sortResults();
        info.getDialogPane().getStyleClass().add("my-alert");
        info.getDialogPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        info.setTitle("Ping Results");
        if (pingResults.equals("") || pingResults.isEmpty()) {
            info.setContentText("No host set. Please set host value on the slider.");
        }
        info.setContentText(pingResults);
        info.show();
    }

}
