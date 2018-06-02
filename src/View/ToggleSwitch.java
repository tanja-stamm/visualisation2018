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

    private BooleanProperty isLeft = new SimpleBooleanProperty(false);
    private TranslateTransition translateAnimation = new TranslateTransition( Duration.seconds(0.02));

    public ToggleSwitch(boolean isLeft) {
        setIsLeft(isLeft);

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

        isLeftProperty().addListener((observable, oldValue, newValue) -> {
            translateAnimation.setToX(oldValue ? 40 - 20 : 0);
            translateAnimation.play();
            System.out.println(newValue);
        });

        setOnMouseClicked(event -> {
            setIsLeft(!isIsLeft());
        });

    }



    public boolean isIsLeft() {
        return isLeft.get();
    }

    public BooleanProperty isLeftProperty() {
        return isLeft;
    }

    public void setIsLeft(boolean isLeft) {
        this.isLeft.set(isLeft);
    }
}
