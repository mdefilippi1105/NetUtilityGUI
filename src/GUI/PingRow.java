package GUI;
import javafx.beans.property.SimpleStringProperty;


public class PingRow {

    public SimpleStringProperty ip = new SimpleStringProperty();
    public SimpleStringProperty result = new SimpleStringProperty();

    public PingRow(String ip, String result, String mac) {
        this.ip.set(ip);
        this.result.set(result);

    }
}
