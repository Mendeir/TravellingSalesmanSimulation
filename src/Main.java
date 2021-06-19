

public class Main {

    public static void main(String[] args){

        //Calling the InputWindow class for Graphical User Interface
        InputWindow window = new InputWindow();

        GuiAlgo travellingSalesmanProblem = new GuiAlgo();
        travellingSalesmanProblem.deleteResultsFile();

        while (!travellingSalesmanProblem.displayMatrix()) {
            System.out.println("Row Minimization");
            travellingSalesmanProblem.rowMinimization(window.processMatrix);
            travellingSalesmanProblem.displayMatrix();
            travellingSalesmanProblem.storeResultsToFile("Row Minimization");

            System.out.println("Column Minimization");
            travellingSalesmanProblem.columnMinimization(window.processMatrix);
            travellingSalesmanProblem.displayMatrix();
            travellingSalesmanProblem.storeResultsToFile("Column Minimization");

            System.out.println("Reduce Matrix");
            travellingSalesmanProblem.calculatePenalty(window.processMatrix, window.exponents);
            travellingSalesmanProblem.reduceMatrix(window.exponents, window.markedPoints, window.processMatrix);
            travellingSalesmanProblem.storeResultsToFile("Reduce Matrix");
        }

    }
}

