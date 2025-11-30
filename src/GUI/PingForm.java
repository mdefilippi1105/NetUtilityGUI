package GUI;

import NetworkClasses.CheckHosts;
import NetworkClasses.GetMacAddress;
import NetworkClasses.IPLookup;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;

import static NetworkClasses.IPLookup.ipLookup;

public class PingForm extends VBox {

    private GridPane grid;
    public Button btnPing;
    public Button btnLookup;
    private Label lblIpAddress;
    private Label lblPort;
    private Label lblSlider;
    public static TextField txtipAddress;
    public static TextField txtPort;
    private ButtonBar btnBar;
    public static ProgressIndicator spinner;
    Progress progress = new Progress();
    private PingSlider pingSlider;
//    private DecimalFormat df = new DecimalFormat("0");
    public static int ipValue;
    AlertWindow alertWindow =  new AlertWindow();



    public boolean checkThreeOctets (String text) {
        // make a variable called "parts" that will hold multiple strings
        // parts[0] = "192" , parts[1] = "168"
        // if less than 3, return
        String[] parts = text.trim().split("\\.");
        return parts.length == 3;
    }

    public PingForm() {
        grid = new GridPane();
                      // spacing between rows

        lblIpAddress = new Label("IP Address");
        lblPort = new Label("URL Search");
        txtipAddress = new TextField();
        txtPort = new TextField();
        spinner = new ProgressIndicator();
        btnPing = new Button("Ping");
        btnLookup = new Button("Lookup");
        pingSlider = new PingSlider();

        Slider slider = pingSlider.getSlider();
        lblSlider = new Label("How many hosts?");
        // ping field
        grid.add(lblIpAddress, 0, 0, 1, 1);
        grid.add(txtipAddress, 0, 1, 1, 1);
        lblIpAddress.setId("lblIpAddress");//css tag
        grid.add(btnPing, 0,4, 1, 1);
        GridPane.setMargin(btnPing, new Insets(12, 0, 0, 0)); // 10px below txt1
        btnPing.setId("ping-button");

        // slider
        grid.add(slider, 0, 2, 2, 1);
        grid.add(lblSlider, 0, 3, 2, 1);

        // URL search
        grid.add(lblPort, 0, 5, 1, 1);
        //this creates padding, kind of like pad x/pad y
        GridPane.setMargin(lblPort, new Insets(42, 0, 0, 0));
        lblPort.setId("lblPort");
        grid.add(txtPort, 0, 6, 2, 1);
        grid.add(btnLookup, 0,9, 1, 1);
        btnLookup.setId("lookup-button");


        grid.add(spinner, 0, 12, 2, 1);
        spinner.setId("spinner");
        GridPane.setMargin(spinner, new Insets(12, 0, 0, 0));
        GridPane.setHalignment(spinner, HPos.LEFT);
        spinner.setVisible(false);

        grid.setHgap(20);
        grid.setVgap(5);

        getChildren().add(grid);
        setPadding(new Insets(10));
        VBox.setVgrow(grid, Priority.ALWAYS);

//------------------------------------------------------------------------------------------

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
//----------------------------------------------------------------------------------------------------------------------

        // this is the action for the ping button
        // most of the juice is here
        btnPing.setOnAction(event -> {
            String subnet = txtipAddress.getText();

            if (!checkThreeOctets(subnet)) {
                System.out.println("Error: Subnet should be 3 octets");
                alertWindow.alertWindowPing();

            } else if (checkThreeOctets(subnet)) {
                alertWindow.alertWindowConfirm();
                spinner.setVisible(true);
                Thread t = CheckHosts.checkHostInThread(subnet, null);
                t.start();
                // if the thread is terminated - shut off spinner
                // TODO: keep adding end events.
                if (t.getState() == Thread.State.TERMINATED) {
                    PingForm.spinner.setVisible(false);
                }
            } else {
                alertWindow.alertNoHostSet();
            }
        });

//----------------------------------------------------------------------------------------------------------------------
        // this is the action for the LOOKUP button
        btnLookup.setOnAction(event -> {
            if (getNameField() == null) {
                alertWindow.alertHostFieldEmpty();
            } else {
                try {
                    ipLookup();
                } catch (UnknownHostException e) {
                  throw new RuntimeException(e);
                } finally {
                    txtPort.setText("");
                    setFieldNull();

                }
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
    //get the value from port box
    public static String getNameField() {
        return txtPort.getText();
    }
    public static String setFieldNull() {
        txtPort.setText("");
        txtPort.clear();
        return "";
    }


}
