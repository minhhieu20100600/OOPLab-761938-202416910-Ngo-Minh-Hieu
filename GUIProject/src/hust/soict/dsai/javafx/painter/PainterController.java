package hust.soict.dsai.javafx.painter;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadioButton;

    @FXML
    private RadioButton eraserRadioButton;

    @FXML
    private void drawingAreaMouseDragged(MouseEvent event) {
        Color color = eraserRadioButton.isSelected() ? Color.WHITE : Color.BLACK;
        drawingAreaPane.getChildren().add(new Circle(event.getX(), event.getY(), 6, color));
    }

    @FXML
    private void clearButtonPressed() {
        drawingAreaPane.getChildren().clear();
    }
}
