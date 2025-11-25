package GUI;

import NetworkClasses.CheckHosts;
import NetworkClasses.GetMacAddress;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DecimalFormat;

public class PingForm extends VBox {

    private GridPane grid;
    public Button btnPing;
    private Label lblIpAddress;
    private Label lblPort;
    private Label lblSlider;
    public static TextField txtipAddress;
    private TextField txtPort;
    private ButtonBar btnBar;
    public static ProgressIndicator spinner;
    Progress progress = new Progress();
    private PingSlider pingSlider;
//    private DecimalFormat df = new DecimalFormat("0");
    public static int ipValue;



    public boolean checkThreeOctets (String text) {
        // make a variable called "parts" that will hold multiple strings
        // parts[0] = "192" , parts[1] = "168"
        // if less than 3, return
        String[] parts = text.trim().split("\\.");
        return parts.length == 3;
    }

    public PingForm() {
        grid = new GridPane();

        lblIpAddress = new Label("IP Address");
        lblPort = new Label("Port");

        txtipAddress = new TextField();
        txtPort = new TextField();

        spinner = new ProgressIndicator();

        btnPing = new Button("Ping");
        btnBar = new ButtonBar();
        btnBar.getButtons().addAll(btnPing);

        pingSlider = new PingSlider();
        Slider slider = pingSlider.getSlider();
        lblSlider = new Label("How many hosts? 0");

        grid.add(lblIpAddress, 0, 0, 1, 1);
        grid.add(txtipAddress, 1, 0, 1, 1);
        grid.add(lblPort, 0, 1, 1, 1);
        grid.add(txtPort, 1, 1, 1, 1);
        grid.add(btnBar, 1, 2, 2, 1);
        grid.add(slider, 0, 3, 2, 1);
        grid.add(lblSlider, 0, 4, 2, 1);

        grid.add(spinner, 0, 2, 2, 1);
        GridPane.setHalignment(spinner, HPos.CENTER);
        spinner.setVisible(false);


        grid.setHgap(20);
        grid.setVgap(5);

        getChildren().add(grid);
        setPadding(new Insets(10));
        VBox.setVgrow(grid, Priority.ALWAYS);

        // this is the slider logic
        // the plan is to grab the value from the slider
        // and set the hosts based on the value selected
        // then ping that amount
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            //convert value to decimal as int and saved as ipValue
            //ipValue will be important as it will be used as the amount of pings.
            ipValue = (int)slider.getValue();
            lblSlider.setText("Hosts:" + ipValue);
        });

        // this is the action for the ping button
        // most of the juice is here
        btnPing.setOnAction(event -> {
            String subnet = txtipAddress.getText();

            if (!checkThreeOctets(subnet)) {
                System.out.println("Error: Subnet should be 3 octets");
                AlertWindow alert = new AlertWindow();
                alert.alertWindowPing();
            } else if (checkThreeOctets(subnet)) {
                AlertWindow  alert = new AlertWindow();
                alert.alertWindowConfirm();
                spinner.setVisible(true);
                Thread t = CheckHosts.checkHostInThread(subnet, null);
                t.start();
                // if the thread is terminated - shut off spinner
                // keep adding end events.
                if (t.getState() == Thread.State.TERMINATED) {
                    PingForm.spinner.setVisible(false);
                }
            } else {
                AlertWindow  alert = new AlertWindow();
                alert.alertNoHostSet();
            }
        });
    }

    //get the value from slider
    public static int getValue() {
        return ipValue;
    }
    //get the value from text box
    public static String getTextField() {
        return txtipAddress.getText();
    }

}
