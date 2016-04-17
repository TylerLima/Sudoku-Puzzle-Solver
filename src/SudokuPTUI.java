import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

/**
 * The Sudoku plain text user interface
 * @author Tyler 4/16/2016.
 * @author Michael 4/16/2016
 */
public class SudokuPTUI {

    /** The puzzle grid that will store of the numbers */
    char[][] puzzleGrid;
    /** The cursor that keeps track of the position within the grid */
    int[] cursor;

    /**
     * The constructor which is created from an input file
     * @param filename the name of the file with the layout for the SudokuPTUI
     * @throws FileNotFoundException
     */
    public SudokuPTUI(String filename) throws FileNotFoundException {
        Scanner in= new Scanner(new File(filename));

        // Create a new 9x9 grid to be filled in
        puzzleGrid= new char[9][9];

        // Fill out the puzzle grid with input from a file
        for(int row= 0; row < puzzleGrid.length; row++){
            for(int col= 0; col < puzzleGrid[0].length; col++){
                puzzleGrid[row][col]= in.next().charAt(0);
            }
        }

        // Initialize other variables
        cursor= new int[]{0,0};
    }

    /**
     * The constructor for backtracking
     * @param parent the parent SudokuPTUI layout
     */
    public SudokuPTUI(SudokuPTUI parent){
        // Copy over the data in the parent SudokuPTUI
        System.arraycopy(parent.puzzleGrid, 0, this.puzzleGrid, 0, parent.puzzleGrid.length);
        System.arraycopy(parent.cursor, 0, this.cursor, 0, parent.cursor.length);
    }

    /**
     * Creates the successors of the current SudokuPTUI
     * @return a collection of the next successors
     */
    public Collection<SudokuPTUI> getSuccessors(){
        return null;
    }

    /**
     * Validates whether the current SudokuPTUI follows the rules of Sudoku or not
     * @return boolean value depending on if the SudokuPTUI is follows the rules of Sudoku
     */
    public boolean validate(){
        // Get the number that was just placed
        char mostRecent= puzzleGrid[cursor[0]][cursor[1]];

        // Check that the row does not already have the number just placed
        for(int col= 0; col < puzzleGrid[0].length; col++){
            if(col != cursor[1] && puzzleGrid[cursor[0]][col] == mostRecent)
                return false;
        }

        // Check that the col does not already have the number just placed
        for(int row= 0; row < puzzleGrid.length; row++){
            if(row != cursor[0] && puzzleGrid[row][cursor[1]] == mostRecent)
                return false;
        }

        /** Check that the 3x3 grid does not already have the number just placed */

        // Find out the row the 3x3 grid starts on
        int minRow= cursor[0];
        while(minRow % 3 != 0)
            minRow--;

        // Find out the column the 3x3 grid starts on
        int minCol= cursor[1];
        while(minCol % 3 != 0)
            minCol--;

        // Check each number in the 3x3 grid for the number just placed
        for(int row= minRow; row <= minRow+ 2; row++){
            for(int col= minCol; col <= minCol+ 2; col++){
                // If the row/col combo isn't on the current cursor position and the number was found
                if(!(row == cursor[0] && col == cursor[1]) && puzzleGrid[row][col] == mostRecent)
                    return false;
            }
        }

        return true;
    }

    /**
     * Finds if the current SudokuPTUI is a solution to the puzzle
     * @return boolean value depending on if the SudokuPTUI is a solution or not
     */
    public boolean isGoal(){
        return true;
    }

    /**
     * Converts the puzzleGrid into a string for the user to visualize
     * @return A string representation of the SudokuPTUI
     */
    @Override
    public String toString(){
        // The string representing the puzzle
        String grid= "";
        // The divide that appears on both sides of the middle 3x3 grids
        String horizLine= "-------|-------|-------\n";

        for(int row= 0; row < puzzleGrid.length; row++){
            // Add a horizontal line after the 3rd and 6th row to separate the 3x3 grids
            if(row != 0 && row % 3 == 0)
                grid+= horizLine;

            // Add a space to the front of each line
            grid+= " ";

            for(int col= 0; col < puzzleGrid[0].length; col++){
                // Add a space after each vertical divide between 3x3 grids
                if(col != 0 && col % 3 == 0)
                    grid+= "| ";

                // Copy over the character in each cell and add a space after it
                grid+= puzzleGrid[row][col]+ " ";

            }

            // Add a newline character after each line
            grid+= "\n";
        }

        return grid;
    }

    /**
     * Main function for the file
     * Creates a puzzle from an input file
     * @param args user provided file
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        SudokuPTUI puzzle= new SudokuPTUI(args[0]);
        System.out.println(puzzle.toString());
    }
}
