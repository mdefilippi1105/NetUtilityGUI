package GUI;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class PingSlider {

    private Slider slider;
    double value;

    public void showSlider() {
        slider = new Slider();
        slider.setMin(0);
        slider.setMax(255);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(50);
        slider.setBlockIncrement(115);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.setSnapToTicks(false);

        value = slider.getValue();
        Label valueLabel = new Label("How many hosts?" + value);
        System.out.println(value);
    }

    public Slider getSlider() {
        showSlider();
        return slider;
    }

    public double getValue() {
        value = slider.getValue();
        return value;
    }

    public void setSliderValue(double value) {
        value = 0.0;
        slider.setValue(value);
    }




}
