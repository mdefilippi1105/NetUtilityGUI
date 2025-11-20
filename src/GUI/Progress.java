package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

public class Progress {


private final ProgressIndicator progress = new ProgressIndicator();

    public void progressIndicatorOn() {
       progress.setProgress(-1);
       progress.setVisible(true);
       progress.setPrefSize(20, 20);
    }
    public void progressIndicatorOff() {
        progress.setProgress(0);
        progress.setVisible(false);
    }


    // this makes the "node" aka button, etc slide from left to right
    // duration is milliseconds
    public void animateKeyframe(Node node) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                new KeyValue(node.translateXProperty(), 25)));
        timeline.play();
    }

}
