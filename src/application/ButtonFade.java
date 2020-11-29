package application;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ButtonFade extends ButtonSkin {
	public ButtonFade(Button control) {
    super(control);

    final FadeTransition fadeIn = new FadeTransition(Duration.millis(100));
    fadeIn.setNode(control);
    fadeIn.setToValue(1);
    control.setOnMouseEntered(e -> fadeIn.playFromStart());
    
//    final FillTransition ft = new FillTransition(Duration.millis(100));
//    ft.setShape(control.getShape());
//    ft.setToValue(Color.RED);
//    control.setOnMouseEntered(e -> ft.playFromStart());

    final FadeTransition fadeOut = new FadeTransition(Duration.millis(100));
    fadeOut.setNode(control);
    fadeOut.setToValue(0.5);
    control.setOnMouseExited(e -> fadeOut.playFromStart());

    control.setOpacity(0.5);
    }
}