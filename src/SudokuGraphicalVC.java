/**
 * Created by tyler on 5/4/2016.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;


public class SudokuGraphicalVC extends Application implements Observer {

    /**variable determining top padding of tiles*/
    private int top_padding = 0;
    /**variable determining bottom padding of tiles*/
    private int bottom_padding = 0;
    /**variable determining left padding of tiles*/
    private int left_padding = 0;
    /**variable determining right padding of tiles*/
    private int right_padding = 0;

    private int currentVal;
    private int gridRow;
    private int gridCol;

    private SudokuSolverModel model;

    /**constructs initial model and make this view the observer*/
    public SudokuGraphicalVC(){
        this.model = new SudokuSolverModel();
        this.model.addObserver(this);
    }

    /**
     * @param primaryStage window to display the UI
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        /**Entire display of the puzzle and buttons (solve button, reset button, possibly number pad)
        To start will probably use a click on grid button (button val changes until desired value appears)*/
        BorderPane mainPane = new BorderPane();

        //Parameters of stage size
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(550);

        //Title
        primaryStage.setTitle("Sudoku Solver");

        //Sudoku puzzle grid
        GridPane puzzleGrid = new GridPane();
        puzzleGrid.setAlignment(Pos.CENTER);
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {

                Button gridTile = new Button();
                gridTile.setPrefSize(45, 45);

                //Sets the action event handler to place the current number variable on sudoku board
                int finalColumn = column;
                int finalRow = row;
                gridTile.setOnAction(event -> model.chooseValue(finalRow, finalColumn));

                //Adds each button made for the puzzle to the the grid pane (called puzzleGrid)
                puzzleGrid.add(gridTile, finalRow, finalColumn);
            }

        }

        mainPane.setCenter(puzzleGrid);


        /**
         * MainAction Buttons (solve button, reset button, etc)
         */
        VBox actionButtons = new VBox();

        Button solvebttn = new Button("Solve");
        solvebttn.setOnAction(e-> model.solvePuzzle);//TODO: figure out how to call the solvePuzzle method from model
        Button reset = new Button("Reset");

        actionButtons.getChildren().addAll(solvebttn, reset);

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
        button1.setOnAction(event -> model.setCurrentNumber(gridRow, gridCol));

        Button button2 = new Button("2");
        button2.setPrefSize(45, 30);
        button2.setOnAction(event -> model.setCurrentNumber(gridRow, gridCol));

        Button button3 = new Button("3");
        button3.setPrefSize(45, 30);
        button3.setOnAction(event -> model.setCurrentNumber(gridRow, gridCol));

        Button button4 = new Button("4");
        button4.setPrefSize(45, 30);
        button4.setOnAction(event -> model.setCurrentNumber(gridRow, gridCol));

        Button button5 = new Button("5");
        button5.setPrefSize(45, 30);
        button5.setOnAction(event -> model.setCurrentNumber(gridRow, gridCol));

        Button button6 = new Button("6");
        button6.setPrefSize(45, 30);
        button6.setOnAction(event -> model.setCurrentNumber(gridRow, gridCol));

        Button button7 = new Button("7");
        button7.setPrefSize(45, 30);
        button7.setOnAction(event -> model.setCurrentNumber(gridRow, gridCol));

        Button button8 = new Button("8");
        button8.setPrefSize(45, 30);
        button8.setOnAction(event -> model.setCurrentNumber(gridRow, gridCol));

        Button button9 = new Button("9");
        button9.setPrefSize(45, 30);
        button9.setOnAction(event -> model.setCurrentNumber(gridRow, gridCol));

        Button eraserButton = new Button("Erase");
        eraserButton.setPrefSize(45, 30);
        eraserButton.setOnAction(event -> model.erase(gridRow, gridCol));

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
        boolean victory = model.isSolved;
    }


    public static void main(String[] args) {
        Application.launch(args);}
}
