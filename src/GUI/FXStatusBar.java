package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class FXStatusBar extends HBox {

    public Label label;

    public FXStatusBar() {
        this.label = new Label(" ");
        this.getChildren().add(label);
        this.setId("statusbar");
        this.setPadding(new Insets(3));
        HBox.setHgrow(label, Priority.ALWAYS);
    }

    public void setStatusText(String text) {
        this.label.setText(text);

    }


}
