import java.io.FileNotFoundException;

/**
 * The backtracking algorithm for solving the puzzle
 * @author Tyler on 4/22/2016
 * @author Michael on 4/22/2016.
 */
public class BackTracker {

    /**
     * Constructor for a new BackTracker object
     */
    public BackTracker(){}

    /**
     * Finds all the outcomes from the current grid
     * @param grid SudokuPTUI object to get the next move from
     */
    public SudokuPTUI solvePuzzle(SudokuPTUI grid){
        // If the Sudoku puzzle is solved return the grid
        if(grid.isGoal()){
            return grid;
        }else{ // If the puzzle has yet to be solved
            for(SudokuPTUI newGrid : grid.getSuccessors()){
                // If the grid does not break the rules of Sudoku
                if(newGrid.validate()){
                    SudokuPTUI solution= solvePuzzle(newGrid);
                    // If a solution was found down this recursive path
                    if(solution != null)
                        return solution;
                }
            }

        }

        // If a solution was not found
        return null;
    }

    /**
     * Main function for the file
     * Creates a new grid and finds all of its outcomes
     * @param args program arguments defined by the user
     */
    public static void main(String[] args) throws FileNotFoundException{
        SudokuPTUI grid= new SudokuPTUI(args[0]);
        System.out.println("Puzzle at the start:\n");
        System.out.println(grid+ "\n");
        BackTracker backtracker= new BackTracker();

        // Time at which the puzzle started
        double start= System.currentTimeMillis();

        System.out.println("Completed Puzzle:\n");
        System.out.println(backtracker.solvePuzzle(grid));

        // Total time it took the puzzle to complete
        System.out.println("Elapsed time: "+ (System.currentTimeMillis() -start)/1000.0+ " seconds.");
    }
}
