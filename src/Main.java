

public class Main {

    public static void main(String[] args){

        //Calling the InputWindow class for Graphical User Interface
        InputWindow window = new InputWindow();

        guiAlgo travellingSalesmanProblem = new guiAlgo();
        travellingSalesmanProblem.deleteResultsFile();

        while (!travellingSalesmanProblem.displayMatrix()) {
            System.out.println("Row Minimization");
            travellingSalesmanProblem.rowMinimization();
            travellingSalesmanProblem.displayMatrix();
            travellingSalesmanProblem.storeResultsToFile("Row Minimization");

            System.out.println("Column Minimization");
            travellingSalesmanProblem.columnMinimization();
            travellingSalesmanProblem.displayMatrix();
            travellingSalesmanProblem.storeResultsToFile("Column Minimization");

            System.out.println("Reduce Matrix");
            travellingSalesmanProblem.calculatePenalty();
            travellingSalesmanProblem.reduceMatrix();
            travellingSalesmanProblem.storeResultsToFile("Reduce Matrix");
        }

        System.out.println(travellingSalesmanProblem.calculatePath());
        System.out.println();
        System.out.println("Total Cost: " + travellingSalesmanProblem.calculateTotalCost());

    }
    }
}
