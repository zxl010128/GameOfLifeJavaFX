package GameOfLife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

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
        Tick.setStyle("-fx-background-color: #d8bdde;");
        Play.setStyle("-fx-background-color: #c9d1a9;");
        Clear.setStyle("-fx-background-color: #bdcfde;");

        if (Play.getText().equals("Stop")) {
            timeline.stop();
            Play.setText("Play");
        }

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
            Play.setStyle("-fx-background-color: #c9d1a9;");
            return;
        }

        // start playing it and change the button to "Stop"
        timeline.play();
        Play.setText("Stop");
        Play.setStyle("-fx-background-color: #e0b8b8;");
    }

    @FXML
    public void clickClearButton() {

        initialize();
    }
}

