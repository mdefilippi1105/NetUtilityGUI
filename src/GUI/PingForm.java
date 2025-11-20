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

public class PingForm extends VBox {

    private GridPane grid;
    public Button btnPing;
    private Label lblIpAddress;
    private Label lblPort;
    private TextField txtipAddress;
    private TextField txtPort;
    private ButtonBar btnBar;
    private ProgressIndicator spinner;
    Progress progress = new Progress();
    private PingSlider pingSlider;
    private Label lblSlider;


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
            lblSlider.setText("Value:" + newValue);
        });


        // this is the action for the ping button
        // most of the juice is here
        btnPing.setOnAction(event -> {
            String subnet = txtipAddress.getText();

            if (!checkThreeOctets(subnet)) {
                System.out.println("Error: Subnet should be 3 octets");
                AlertWindow alert = new AlertWindow();
                alert.alertWindowPing();
                System.out.println(checkThreeOctets(subnet));
            } else {
                try {
                    grid.add(spinner, 0, 2, 2, 1);
                    GridPane.setHalignment(spinner, HPos.CENTER);
                    Thread t = CheckHosts.checkHostInThread(subnet, null);
                    t.start();
                    progress.progressIndicatorOn();
                    GetMacAddress.getMacAddress();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    progress.progressIndicatorOff();
                }
            }
        });
    }
}
