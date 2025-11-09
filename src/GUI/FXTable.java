package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class FXTable extends VBox {

    private static TableView<PingRow> table;
    private TableColumn<PingRow, String> ipColumn;
    private TableColumn<PingRow, String> pingResultColumn;
    private TableColumn<PingRow, String> macResultColumn;

    public FXTable() {
        table = new TableView<>();
        ipColumn = new TableColumn<>("IP Address");
        pingResultColumn = new TableColumn<>("Result");
        macResultColumn = new TableColumn<>("Mac Address");
        table.getStyleClass().add("ping-results");

        ipColumn.setCellValueFactory(c -> c.getValue().ip);
        pingResultColumn.setCellValueFactory(c -> c.getValue().result);
        macResultColumn.setCellValueFactory(c -> c.getValue().mac);

        table.getColumns().add(ipColumn);
        table.getColumns().add(pingResultColumn);
        table.getColumns().add(macResultColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        getChildren().add(table);
        setPadding(new Insets(10));
        VBox.setVgrow(table, Priority.ALWAYS);
    }

    public static void addPingResult(String ip, String result, String mac) {
        table.getItems().add(new PingRow(ip, result, mac));
    }

}
