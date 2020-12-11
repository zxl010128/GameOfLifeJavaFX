package GameOfLife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Xinlei Zhang
 *
 */
public class GameOfLifeController {

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
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
                newGame.tick();
            }
        }));
    }

    @FXML
    public void initialize() {

        for (int x = 0; x < 20; x++) {

            for (int y = 0; y < 20; y++) {

                CheckBox checkBox = new CheckBox();
                checkBox.setStyle("selected-box-color: lime; box-color: red; mark-color: blue;");
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

