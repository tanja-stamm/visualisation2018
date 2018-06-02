package View;

import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class ToggleSwitch extends Parent {

    private BooleanProperty left = new SimpleBooleanProperty(false);
    private TranslateTransition translateAnimation = new TranslateTransition( Duration.seconds(0.02));

    public ToggleSwitch(boolean Left) {
        setLeft(Left);

        Rectangle background = new Rectangle(40,20);
        background.setArcHeight(20);
        background.setArcWidth(20);
        background.setFill(Color.GREEN);
        background.setStroke(Color.LIGHTGRAY);

        Circle trigger = new Circle(10);
        trigger.setCenterX(10);
        trigger.setCenterY(10);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);

        translateAnimation.setNode(trigger);

        getChildren().addAll(background, trigger);

        leftProperty().addListener((observable, oldValue, newValue) -> {
            translateAnimation.setToX(oldValue ? 40 - 20 : 0);
            translateAnimation.play();
        });

        setOnMouseClicked(event -> {
            setLeft(!getLeft());
        });

    }


    public boolean getLeft() {
        return left.get();
    }

    public BooleanProperty leftProperty() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left.set(left);
    }
}
