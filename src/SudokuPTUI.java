import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The Sudoku plain text user interface
 * @author Tyler 4/16/2016.
 * @author Michael 4/16/2016
 */
public class SudokuPTUI implements Observer {

    /** The puzzle grid that will store of the numbers */
    private char[][] puzzleGrid;
    /** The cursor that keeps track of the position within the grid */
    private int[] cursor;

    /**
     * The constructor which is created from an input file
     * @param filename the name of the file with the layout for the SudokuPTUI
     * @throws FileNotFoundException
     */
    public SudokuPTUI(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));

        // Create a new 9x9 grid to be filled in
        puzzleGrid = new char[9][9];

        // Fill out the puzzle grid with input from a file
        for(int row = 0; row < puzzleGrid.length; row++){
            for(int col = 0; col < puzzleGrid[0].length; col++){
                puzzleGrid[row][col] = in.next().charAt(0);
            }
        }

        // Initialize other variables
        cursor= new int[]{0,-1};
    }

    /**
     * The constructor for backtracking (deep copy creator)
     * @param parent the parent SudokuPTUI layout
     */
    public SudokuPTUI(SudokuPTUI parent){
        // Initialize the puzzleGrid and cursor
        puzzleGrid = new char[9][9];
        cursor= new int[2];

        // Copy over the data in the parent SudokuPTUI
        for(int row= 0; row < this.puzzleGrid.length; row++){
            System.arraycopy(parent.puzzleGrid[row], 0, this.puzzleGrid[row], 0, parent.puzzleGrid[row].length);
        }
    }

    public int[] cursor_helper(int[] cursor){
        int[] new_cursor = new int[2];
        System.arraycopy(cursor, 0, new_cursor, 0, 2);
        new_cursor[1] += 1;
        if(new_cursor[1] == 9) {
            new_cursor[1] = 0;
            new_cursor[0] += 1;
        }
        return new_cursor;
    }

    /**
     * Creates the successors of the current SudokuPTUI
     * @return a collection of the next successors
     */
    public Collection<SudokuPTUI> getSuccessors(){
        Collection<SudokuPTUI> possibleSuccessors = new ArrayList<>();

        // Get the new position to place a number at
        int[] newCursor= cursor_helper(this.cursor);

        if(puzzleGrid[newCursor[0]][newCursor[1]] != '-'){
            SudokuPTUI child = new SudokuPTUI(this);
            child.puzzleGrid[newCursor[0]][newCursor[1]] = this.puzzleGrid[newCursor[0]][newCursor[1]];
            child.cursor = newCursor;
            possibleSuccessors.add(child);
        }
        else{
            for(int i = 1; i <= 9; i++){
                SudokuPTUI child = new SudokuPTUI(this);
                child.puzzleGrid[newCursor[0]][newCursor[1]] = Character.forDigit(i, 10);
                child.cursor = newCursor;
                possibleSuccessors.add(child);
            }
        }
        return possibleSuccessors;
    }

    /**
     * Validates whether the current SudokuPTUI follows the rules of Sudoku or not
     * @return boolean value depending on if the SudokuPTUI is follows the rules of Sudoku
     */
    public boolean validate(){
        // Get the number that was just placed
        char mostRecent = puzzleGrid[cursor[0]][cursor[1]];

        // Check that the row does not already have the number just placed
        for(int col = 0; col < puzzleGrid[0].length; col++){
            if(col != cursor[1] && puzzleGrid[cursor[0]][col] == mostRecent)
                return false;
        }

        // Check that the col does not already have the number just placed
        for(int row = 0; row < puzzleGrid.length; row++){
            if(row != cursor[0] && puzzleGrid[row][cursor[1]] == mostRecent)
                return false;
        }

        /** Check that the 3x3 grid does not already have the number just placed */

        // Find out the row the 3x3 grid starts on
        int minRow = cursor[0];
        while(minRow % 3 != 0)
            minRow--;

        // Find out the column the 3x3 grid starts on
        int minCol = cursor[1];
        while(minCol % 3 != 0)
            minCol--;

        // Check each number in the 3x3 grid for the number just placed
        for(int row = minRow; row <= minRow+ 2; row++){
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
    public boolean isGoal() {
        // Check each square in the grid for a blank space
        for(int row= 0; row < puzzleGrid.length; row++){
            for(int col= 0; col < puzzleGrid[0].length; col++){
                // If a blank space is found, then the puzzle is incomplete
                if(puzzleGrid[row][col] == '-')
                    return false;
            }
        }

        // If no blank space was found, the puzzle was filled out correctly
        return true;
    }

    /**
     * Converts the puzzleGrid into a string for the user to visualize
     * @return A string representation of the SudokuPTUI
     */
    @Override
    public String toString(){
        // The string representing the puzzle
        String grid = "";
        // The divide that appears on both sides of the middle 3x3 grids
        String horizLine = "-------|-------|-------\n";

        for(int row = 0; row < puzzleGrid.length; row++){
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
        SudokuPTUI puzzle = new SudokuPTUI(args[0]);
        System.out.println(puzzle.toString());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
