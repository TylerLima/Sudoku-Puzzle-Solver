import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Observable;

/**
 * Created by tyler on 6/15/2016.
 */


public class SudokuSolverModel extends Observable{

    private char[][] puzzleGrid;
    public boolean isSolved;
    private char currentNumber;

    /**
     * records and displays the current coordinate in the sudoku game as the value currently chosen from the
     * setCurrentNumber method
     * @param row number of row of button clicked in the sudoku grid
     * @param col number of column of the button clicked in the sudoku grid
     */
    public void chooseValue(int row, int col){
        puzzleGrid[row][col] = currentNumber;
    }

    /**
     * Given the row and col of the button clicked on the bottom of the gui displaying possible numbers to choose from,
     * the method stores this value as a single integer.
     * This value will be used for assigning a value 0-9 to a particular grid tile.
     * Default is set to zero which will be ignored or treated as still a blank grid tile
     *
     * @param row number of row of button clicked in the bottom 2 by 5 grid
     * @param col number of column of the button clicked in the bottom of hte 2 by 5 grid
     */
    public void setCurrentNumber(int row, int col){
        if(row==0){
            if(col==0){
                currentNumber=1;
            }if(col==1){
                currentNumber=2;
            }if(col==2){
                currentNumber=3;
            } if(col==3){
                currentNumber=4;
            }if(col==4){
                currentNumber=5;
            }

        }else if(row==1){
            if(col==0){
                currentNumber=6;
            }if(col==1){
                currentNumber=7;
            }if(col==2){
                currentNumber=8;
            } if(col==3){
                currentNumber=9;
            }
        }else{
            currentNumber=0;
        }


    }
    /**
     * method to communicate a change has occurred, notifying the observers
     */
    private void notifyChange() {
        setChanged();
        notifyObservers();
    }

    public void erase(int gridRow, int gridCol){
        puzzleGrid[gridRow][gridCol] = 0; //reset the space at these grid coordinates to be blank
    }

    public SudokuSolverModel(){
        isSolved = false;
    }

    /**
     * Creates a text file in the proper form for the BackTracker to solve
     */
    public void RenderTextFile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("puzzleTextFile.txt", "UTF-8");

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                writer.append(puzzleGrid[r][c]) ;
            }
        }
    }

    /**
     * Sends the newly rendered text file of sudoku grid in proper format to the BackTracker as a Sudoku puzzle.
     * That information is sent back as a 2D char array which is then interpreted and used to update the gui
     * for the user to see.
     */
    public void solvePuzzle() throws FileNotFoundException, UnsupportedEncodingException {
        RenderTextFile();
    }
}
