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

    public SudokuPTUI(String filename) throws FileNotFoundException {
        Scanner in= new Scanner(new File(filename));

        puzzleGrid= new char[9][9];

        for(int row= 0; row < puzzleGrid.length; row++){
            for(int col= 0; col < puzzleGrid[0].length; col++){
                puzzleGrid[row][col]= in.next().charAt(0);
            }
        }
    }

    public SudokuPTUI(SudokuPTUI parent){
        System.arraycopy(parent.puzzleGrid, 0, this.puzzleGrid, 0, parent.puzzleGrid.length);
    }

    /**
     * Converts the puzzleGrid into a string for the user to visualize
     * @return A string representation of the SudokuPTUI
     */
    @Override
    public String toString(){
        String grid= "";
        String horizLine= "-------|-------|-------\n";

        for(int row= 0; row < puzzleGrid.length; row++){
            if(row != 0 && row % 3 == 0)
                grid+= horizLine;

            grid+= " ";

            for(int col= 0; col < puzzleGrid[0].length; col++){
                if(col != 0 && col % 3 == 0)
                    grid+= "| ";

                grid+= puzzleGrid[row][col]+ " ";

            }

            grid+= "\n";
        }

        return grid;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SudokuPTUI puzzle= new SudokuPTUI(args[0]);
        System.out.println(puzzle.toString());
    }
}
