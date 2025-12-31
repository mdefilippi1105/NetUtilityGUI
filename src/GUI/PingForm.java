package GUI;

import NetworkClasses.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.UnknownHostException;

import static NetworkClasses.IPLookup.ipLookup;
import static NetworkClasses.IPLookup.resetIPLookup;

public class PingForm extends VBox {
    private final GridPane grid;
    public final Button btnPing;
    public final Button btnCancel;
    public final Button btnLookup;
    public final Button btnClear;
    public final Label lblIpAddress;
    public final Label lblPort;
    private final Label lblSlider;
    public static TextField txtipAddress;
    public static TextField txtPort;
    public static ProgressIndicator spinner;
    public  PingSlider pingSlider = new PingSlider();
    AlertWindow alertWindow =  new AlertWindow();
    public String subnet;
    private Thread  thread;
    private int ipValue = 0;



    public PingForm() {

        grid = new GridPane();
        lblIpAddress = new Label("IP Address");
        lblPort = new Label("URL Search");
        txtipAddress = new TextField();
        txtPort = new TextField();
        spinner = new ProgressIndicator();
        btnPing = new Button("   Ping   ");
        btnLookup = new Button("Lookup");
        btnCancel = new Button("Cancel");
        btnClear = new Button("  Clear  ");

        Slider slider = pingSlider.getSlider();
        lblSlider = new Label("How many hosts?");
        // the plan is to grab the value from the slider
        // and set the hosts based on the value selected
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            //convert value to decimal as int and saved as ipValue
            ipValue = newValue.intValue();
            lblSlider.setText("Hosts:" + ipValue);
        });

        // ping field
        grid.add(lblIpAddress, 0, 0, 1, 1);
        grid.add(txtipAddress, 0, 1, 1, 1);
        //css tag

        // Ping button
        grid.add(btnPing, 0,4, 1, 1);
        // Cancel button
        grid.add(btnCancel, 0,10, 1, 1);
        GridPane.setMargin(btnPing, new Insets(12, 0, 0, 0)); // 12px below txt1
        GridPane.setMargin(btnCancel, new Insets(12, 0, 0, 0));

        // css tags
        lblIpAddress.setId("lblIpAddress");
        btnPing.setId("ping-button");
        btnCancel.setId("cancel-button");
        btnClear.setId("clear-button");
        lblPort.setId("lblPort");
        btnLookup.setId("lookup-button");
        spinner.setId("spinner");

        // slider
        grid.add(slider, 0, 2, 2, 1);
        grid.add(lblSlider, 0, 3, 2, 1);

        // URL search
        grid.add(lblPort, 0, 5, 1, 1);

        //this creates padding, kind of like pad x/pad y
        GridPane.setMargin(lblPort, new Insets(42, 0, 0, 0));

        //Lookup button
        grid.add(txtPort, 0, 6, 2, 1);
        grid.add(btnLookup, 0,9, 1, 1);

        //Clear button
        grid.add(btnClear, 1,10, 1, 1);
        GridPane.setMargin(btnClear, new Insets(12, 0, 0, 0));

        //add the spinner
        grid.add(spinner, 0, 9, 2, 1);
        GridPane.setMargin(spinner, new Insets(0, 0, 0, 0));
        GridPane.setHalignment(spinner, HPos.RIGHT);
        spinner.setVisible(false);

        //spacing for the visual elements
        grid.setHgap(20);
        grid.setVgap(5);

        //add em and pad em
        getChildren().add(grid);
        setPadding(new Insets(10));
        VBox.setVgrow(grid, Priority.ALWAYS);


//----------------------------------------------------------------------------------------------------------------------
        // this is the action for the ping button
        // most of the juice is here
        btnPing.setOnAction(event -> {
            subnet = txtipAddress.getText();
            int pingCount = ipValue;
            CheckHosts.resetStopFlag();
            thread = CheckHosts.checkHostInThread(subnet, pingCount, null);
            try {
                if (!ValidateInput.checkThreeOctets(subnet)) {
                    alertWindow.alertWindowPing();

                } else if (ValidateInput.checkThreeOctets(subnet)) {
                    alertWindow.alertWindowConfirm();
                    spinner.setVisible(true);
                    thread.start();
                }
            } finally {
                boolean running = thread.isAlive();
                if (!running) {
                    System.out.println("thread dead");
                } else  {
                    System.out.println("thread is alive");
                }
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
                    setFieldNull();
                    resetIPLookup();
                }
            }
        });
//----------------------------------------------------------------------------------------------------------------------
//         this is the action for the CANCEL button
        btnCancel.setOnAction( e -> {
            spinner.setVisible(false);
            slider.setValue(0);
            CheckHosts.setStopRequested();
        });
//----------------------------------------------------------------------------------------------------------------------
//         this is the action for the CLEAR button
        btnClear.setOnAction( e -> {
            FXTable.removeResult();
            CheckHosts.clearResults();
        });








    }

















    //get the value from slider
    public int getValue() {
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
    //set the URL field blank
    public static void setFieldNull() {
        txtPort.clear();
    }



}
