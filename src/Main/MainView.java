package Main;

import GUI.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
TODO:
trim decibels off of the slider - done
grab value from the slider - done
do something with the port field - done
display mac address somewhere - done
button that stops the ping - done
change Ready to "running" while ping running - removed
at the end of ping: display how many reachable, and how many not - done
button that clears the table - done
spinner shrunk-done


thrown an error when no route to host -> now it just crashes to console
organize results at end
set ping second interval
when prompted to ping, clicking cancel pings anyway -> change this
file menu -> action
edit menu -> action
help menu -> action
new button -> action
save button -> action
quit button -> action
 */


public class MainView {
    private Stage stage;
    private BorderPane root;
    private Scene scene;

    private final FXMenu fxMenu = new FXMenu();
    private final FXToolBar fxToolBar = new FXToolBar();
    public PingForm fxForm = new PingForm();
    public FXTable fxTable = new FXTable();
    public FXStatusBar fxStatusBar = new FXStatusBar();


    private void buildUI() {
        root = new BorderPane();
        VBox top = new VBox(fxMenu, fxToolBar);
        root.setTop(top);
        root.setLeft(fxForm);
        root.setCenter(fxTable);
        scene = new Scene(root, 700, 500);
        scene.getStylesheets().add("stylesheet.css");
        stage.setTitle("Tool Menu");
        stage.setScene(scene);
        stage.show();
    }
    public MainView(Stage stage) {
        this.stage = stage;
        buildUI();
    }

}

