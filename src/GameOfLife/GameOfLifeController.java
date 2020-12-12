package GameOfLife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.Bindings;
/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Xinlei Zhang
 *
 */
public class GameOfLifeController {

    @FXML
    private Pane pane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button Tick;

    @FXML
    private Button Play;

    @FXML
    private Button Clear;

    private GameOfLife newGame;

    private Timeline timeline;

    public GameOfLifeController() {
        newGame = new GameOfLife();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e -> { newGame.tick(); }));
    }

    @FXML
    public void initialize() {
        // keep gridPane at original size    

        pane.setStyle("-fx-background-color: #FEFFF2;");
        Tick.setStyle("-fx-background-color: #0DB02B;");
        Play.setStyle("-fx-background-color: #E4002B;");
        Clear.setStyle("-fx-background-color: #009CDE;");
        for (int x = 0; x < 20; x++) {

            for (int y = 0; y < 20; y++) {

                CheckBox checkBox = new CheckBox();
                //checkBox.setStyle("-fx-body-color: #C4D600;");
                GridPane.setColumnIndex(checkBox, x);
                GridPane.setRowIndex(checkBox, y);

                newGame.cellProperty(x, y).bindBidirectional(checkBox.selectedProperty());
                this.gridPane.add(checkBox, x, y);
            }
        }
    }

    @FXML
    public void clickTickButton() {
        newGame.tick();
    }

    @FXML
    public void clickPlayButton() {

        // if it is playing, stop it and change the button to "Play"
        if (Play.getText().equals("Stop")) {
            timeline.stop();
            Play.setText("Play");
            return;
        }

        // start playing it and change the button to "Stop"
        timeline.play();
        Play.setText("Stop");
    }

    @FXML
    public void clickClearButton() {

        initialize();
    }
}

