package Main;

import GUI.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
to do:
trim decibels off of the slider - done
grab value from the slider - done
do something with the port field
set ping second interval
at the end of ping: display how many reachable, and how many not
display mac address somewhere
button that clears the table
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

    private FXMenu fxMenu;
    private FXToolBar fxToolBar;
    private PingForm fxForm;
    private FXTable fxTable;
    private FXStatusBar fxStatusBar;

    public MainView(Stage stage) {
        this.stage = stage;
        buildUI();
    }
    private void buildUI() {
        root = new BorderPane();
//        root.addEventFilter(PersonEvent.ANY, this::handlePersonEvent);

        fxMenu = new FXMenu();
        fxToolBar = new FXToolBar();

        VBox top = new VBox(fxMenu, fxToolBar);
        root.setTop(top);

        fxForm = new PingForm();
        root.setLeft(fxForm);

        fxTable = new FXTable();
        root.setCenter(fxTable);

        fxStatusBar = new FXStatusBar();
        root.setBottom(fxStatusBar);



        scene = new Scene(root, 1200, 500);
        scene.getStylesheets().add("stylesheet.css");
        stage.setTitle("Tool Menu");
        stage.setScene(scene);
        stage.show();
    }

}

