package GUI;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class FXTable extends VBox {

    // create the table
    private static TableView<PingRow> table = new TableView<>();
    private static TableColumn<PingRow, String> ipColumn = new TableColumn<>("IP Address");
    private static TableColumn<PingRow, String> pingResultColumn = new TableColumn<>("Result");




    public FXTable() {

        // this is the list where we store the data
        table.setItems(FXCollections.observableArrayList());

        // css
        table.getStyleClass().add("ping-results");
        table.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());

        ipColumn.setCellValueFactory(c -> c.getValue().ip);
        pingResultColumn.setCellValueFactory(c -> c.getValue().result);

        table.getColumns().add(ipColumn);
        table.getColumns().add(pingResultColumn);
        ipColumn.setPrefWidth(200);
        pingResultColumn.setPrefWidth(200);

        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        getChildren().add(table);
        setPadding(new Insets(10));
        VBox.setVgrow(table, Priority.ALWAYS);
    }

    // we add the ROW to the TABLE
    public static void addPingResult(String ip, String result) {
        table.getItems().add(new PingRow(ip, result));
    }

    public static void removeResult() {

        if (table != null) {
            for(int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }
        }
    }


}
