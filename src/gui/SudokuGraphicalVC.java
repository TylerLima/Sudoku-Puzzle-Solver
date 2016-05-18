package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javafx.util.Pair;
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
                gridTile.setPrefSize(40, 40);
                //TODO: fix setting image background of buttons, use different method from Lasers2 via Strout's code
                BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/testing/background.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);

                Button button = new Button( "Click me!");
                button.setBackground(background);


            }

        }

        mainPane.setCenter(puzzleGrid);

        //MainAction Buttons (solve button, reset button, etc)
        VBox actionButtons = new VBox();

        Button solve = new Button("Solve");
        Button reset = new Button("Reset");

        actionButtons.getChildren().addAll(solve, reset);

        mainPane.setRight(actionButtons);

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    @Override
    public void update(Observable o, Object arg) {
    }


    public static void main(String[] args) {Application.launch(args);}
}
