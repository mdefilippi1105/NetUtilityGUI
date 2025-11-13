package GUI;

import NetworkClasses.CheckHosts;
import NetworkClasses.GetMacAddress;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PingForm extends VBox {

    private GridPane grid;
    private Button btnPing;
    private Label lblIpAddress;
    private Label lblPort;
    private TextField txtipAddress;
    private TextField txtPort;
    private ButtonBar btnBar;

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

        btnPing = new Button("Ping");


        btnPing.setOnAction(event -> {
            String subnet = txtipAddress.getText();

            if (!checkThreeOctets(subnet)) {
                System.out.println("Error: Subnet should be 3 octets");
                System.out.println(checkThreeOctets(subnet));
            } else {
                try {
                    System.out.println(checkThreeOctets(subnet));
                    checkThreeOctets(subnet);
                    CheckHosts.checkHosts(subnet);
                    GetMacAddress.getMacAddress();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnBar = new ButtonBar();
        btnBar.getButtons().addAll(btnPing);

        grid.add(lblIpAddress, 0, 0, 1, 1);
        grid.add(txtipAddress, 1, 0, 1, 1);
        grid.add(lblPort, 0, 1, 1, 1);
        grid.add(txtPort, 1, 1, 1, 1);
        grid.add(btnBar, 0, 2, 2, 1);

        grid.setHgap(10);
        grid.setVgap(5);

        getChildren().add(grid);
        setPadding(new Insets(10));
        VBox.setVgrow(grid, Priority.ALWAYS);
    }
}
