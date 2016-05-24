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

    /**variable determining top padding of tiles*/
    private int top_padding = 0;
    /**variable determining bottom padding of tiles*/
    private int bottom_padding = 0;
    /**variable determining left padding of tiles*/
    private int left_padding = 0;
    /**variable determining right padding of tiles*/
    private int right_padding = 0;

    private int val;

    /**
     * @param primaryStage
     * @throws Exception
     */
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
        puzzleGrid.setAlignment(Pos.CENTER);
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {

                //need final ints for action event handlers (eg. clicking the square tile to place a number)
                final int final_row = row;
                final int final_col = column;


                Button gridTile = new Button();
                gridTile.setPrefSize(45, 45);


                //TODO: fix setting image background of buttons, use different method from Lasers2 via Strout's code
                //BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/testing/background.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                //Background background = new Background(backgroundImage);

                //Button button = new Button( "Click me!");
                //button.setBackground(background);

                //sets the action event handler to place the current number variable on sudoku board
                //gridTile.setOnAction(event -> {model.add(final_row, final_col);

                //Adds each button made for the puzzle to the the grid pane (called puzzleGrid)
                puzzleGrid.add(gridTile, final_row, final_col);


            }

        }

        mainPane.setCenter(puzzleGrid);


        /**
         * MainAction Buttons (solve button, reset button, etc)
         */
        VBox actionButtons = new VBox();

        Button solve = new Button("Solve");
        Button reset = new Button("Reset");

        actionButtons.getChildren().addAll(solve, reset);

        mainPane.setRight(actionButtons);


        /**
         * Set the bottom of the borderPane to be numbers 1-9 and and eraser button for assigning grid tile values
         */
        VBox numberButtons = new VBox();
        numberButtons.setAlignment(Pos.CENTER);

        HBox numbers1_5 = new HBox();
        numbers1_5.setAlignment(Pos.CENTER);

        HBox numbers6_9 = new HBox();
        numbers6_9.setAlignment(Pos.CENTER);

        Button button1 = new Button("1");
        button1.setPrefSize(45, 30);
        Button button2 = new Button("2");
        button2.setPrefSize(45, 30);
        Button button3 = new Button("3");
        button3.setPrefSize(45, 30);
        Button button4 = new Button("4");
        button4.setPrefSize(45, 30);
        Button button5 = new Button("5");
        button5.setPrefSize(45, 30);
        Button button6 = new Button("6");
        button6.setPrefSize(45, 30);
        Button button7 = new Button("7");
        button7.setPrefSize(45, 30);
        Button button8 = new Button("8");
        button8.setPrefSize(45, 30);
        Button button9 = new Button("9");
        button9.setPrefSize(45, 30);
        Button eraserButton = new Button("Erase");
        eraserButton.setPrefSize(45, 30);

        numbers1_5.getChildren().addAll(button1, button2, button3, button4, button5);
        numbers6_9.getChildren().addAll(button6, button7, button8, button9, eraserButton);

        numberButtons.getChildren().addAll(numbers1_5, numbers6_9);

        mainPane.setBottom(numberButtons);


        /**
         * Puts all panes together for first look at grid ( ie. displays our GUI for interaction with user)
         */
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    @Override
    public void update(Observable o, Object arg) {
    }


    public static void main(String[] args) {Application.launch(args);}
}
