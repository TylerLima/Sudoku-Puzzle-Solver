import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Sudoku plain text user interface
 * @author Tyler 4/16/2016.
 * @author Michael 4/16/2016
 */
public class SudokuPTUI {

    /** The puzzle grid that will store of the numbers */
    char[][] puzzleGrid;

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
    }

    /**
     * The constructor for backtracking
     * @param parent the parent SudokuPTUI layout
     */
    public SudokuPTUI(SudokuPTUI parent){
        // Copy over the data in the parent SudokuPTUI
        System.arraycopy(parent.puzzleGrid, 0, this.puzzleGrid, 0, parent.puzzleGrid.length);
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
