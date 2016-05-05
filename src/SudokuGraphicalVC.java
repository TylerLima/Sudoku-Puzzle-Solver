import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by tyler on 5/4/2016.
 *
 */
public class SudokuGraphicalVC extends Application implements Observer {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Entire display of the puzzle and buttons (solve button, reset button, possibly number pad)
        //To start will probably use a click on grid button (button val changes until desired value appears)
        BorderPane mainPane = new BorderPane();

        primaryStage.setMinHeight(900);
        primaryStage.setMinWidth(900);

        primaryStage.setTitle("Sudoku Solver");


        //Sudoku puzzle grid
        GridPane puzzleGrid = new GridPane();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                Button gridTile = new Button();

            }

        }

        mainPane.setCenter(puzzleGrid);

        //MainAction Buttons (solve button, reset button, etc)
        VBox actionButtons = new VBox();

        Button solve = new Button("Solve");
        Button reset = new Button("Reset");

        actionButtons.getChildren().addAll(solve, reset);

        mainPane.setRight(actionButtons);


    }



    @Override
    public void update(Observable o, Object arg) {

    }
}
