import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GuiAlgo {

    public boolean displayMatrix() {
        boolean isMatrixNull = true;
        for (int[] rowValues : processMatrix) {
            isMatrixNull = true;
            for (int colValues : rowValues) {
                if (colValues == -1) {
                    System.out.print("-" + " ");
                }
                else {
                    System.out.print(colValues + " ");
                    isMatrixNull = false;
                }
            }
            System.out.println();
        }

        return isMatrixNull;
    }

    public void displayExponents() {
        for (int[] rowValues : exponents) {
            for (int colValues : rowValues) {
                if (colValues == -1)
                    System.out.print("-" + " ");
                else
                    System.out.print(colValues + " ");
            }
            System.out.println();
        }
    }

    public void displayMarkedPoints() {
        for (boolean[] rowValues : markedPoints) {
            for (boolean colValues : rowValues) {
                System.out.print(colValues + " ");
            }
            System.out.println();
        }
    }

    public void rowMinimization(int[][] processMatrix) {
        for (int rowCounter = 0; rowCounter < processMatrix.length; ++rowCounter) {
            int min = 100000000;
            for (int colCounter = 0; colCounter < processMatrix[rowCounter].length; ++colCounter) {
                //Check if the row is null
                if (processMatrix[rowCounter][colCounter] == -1)
                    continue;

                //If minimized break
                if (processMatrix[rowCounter][colCounter] == 0) {
                    min = 0;
                    break;
                }

                //If not minimized find the min number
                if (min > processMatrix[rowCounter][colCounter])
                    min = processMatrix[rowCounter][colCounter];
            }
            if (min != 0)
                reduceRow(rowCounter, min, processMatrix);
        }
    }

    public void reduceRow(int rowNumber, int minNumber, int[][] processMatrix) {
        for (int colCounter = 0; colCounter < processMatrix[rowNumber].length; ++colCounter) {
            //If not null reduce
            if (processMatrix[rowNumber][colCounter] != -1)
                processMatrix[rowNumber][colCounter] -= minNumber;
        }
    }

    public void columnMinimization(int[][] processMatrix) {
        for (int colCounter = 0; colCounter < processMatrix.length; ++colCounter) {
            int min = 100000000;
            for (int rowCounter = 0; rowCounter < processMatrix[colCounter].length; ++rowCounter) {
                //Check if the row is null
                if (processMatrix[rowCounter][colCounter] == -1)
                    continue;

                //If minimized break
                if (processMatrix[rowCounter][colCounter] == 0) {
                    min = 0;
                    break;
                }

                //If not minimized find the min number
                if (min > processMatrix[rowCounter][colCounter])
                    min = processMatrix[rowCounter][colCounter];
            }
            if (min != 0)
                reduceColumn(colCounter, min, processMatrix);
        }
    }

    public void reduceColumn(int colNumber, int minNumber, int[][] processMatrix) {
        for (int rowCounter = 0; rowCounter < processMatrix.length; ++rowCounter) {
            //If not null reduce
            if (processMatrix[rowCounter][colNumber] != -1)
                processMatrix[rowCounter][colNumber] -= minNumber;
        }
    }

    public void calculatePenalty(int[][] processMatrix, int[][] exponents) {
        int penalty = 0;

        for (int rowCounter = 0; rowCounter < processMatrix.length; ++rowCounter) {
            for (int colCounter = 0; colCounter < processMatrix[rowCounter].length; ++colCounter) {
                if (processMatrix[rowCounter][colCounter] == 0) {
                    penalty = getMinRow(rowCounter, rowCounter, colCounter, processMatrix) +
                            getMinColumn(colCounter, rowCounter, colCounter, processMatrix);
                    exponents[rowCounter][colCounter] = penalty;
                }
            }
        }


    }

    public int getMinRow(int rowNumber, int excludeRowNumber, int excludeColNumber, int[][] processMatrix) {
        int min = 100000000;

        for (int colCounter = 0; colCounter < processMatrix[rowNumber].length; ++colCounter) {
            //If the number is null skip
            if (processMatrix[rowNumber][colCounter] == -1) {
                continue;
            }

            //If the number is the zero we are evaluating, skip
            if (excludeRowNumber == rowNumber && excludeColNumber == colCounter) {
                continue;
            }

            //Find the minimum number
            if (min > processMatrix[rowNumber][colCounter]) {
                min = processMatrix[rowNumber][colCounter];
            }
        }

        return min;
    }

    public int getMinColumn(int colNumber, int excludeRowNumber, int excludeColNumber, int[][] processMatrix) {
        int min = 100000000;

        for (int rowCounter = 0; rowCounter < processMatrix.length; ++rowCounter) {
            ///If the number is null skip
            if (processMatrix[rowCounter][colNumber] == -1) {
                continue;
            }

            //If the number is the zero we are evaluating, skip
            if (excludeRowNumber == rowCounter && excludeColNumber == colNumber) {
                continue;
            }

            //Find the minimum number
            if (min > processMatrix[rowCounter][colNumber]) {
                min = processMatrix[rowCounter][colNumber];
            }
        }

        return min;
    }

    public void reduceMatrix(int[][] exponents, boolean[][] markedPoints, int[][] processMatrix) {
        int max = 0;
        int highRowNumber = 0;
        int highColNumber = 0;

        for (int rowCounter = 0; rowCounter < exponents.length; ++rowCounter) {
            for (int colCounter = 0; colCounter < exponents[rowCounter].length; ++colCounter) {
                if (exponents[rowCounter][colCounter] > max) {
                    max = exponents[rowCounter][colCounter];
                    highColNumber = colCounter;
                    highRowNumber = rowCounter;
                }
            }
        }
        //Mark the coordinate chosen
        markedPoints[highRowNumber][highColNumber] = true;

        //Make the opposite coordinate null
        processMatrix[highColNumber][highRowNumber] = -1;
        exponents[highColNumber][highRowNumber] = -1;

        //Make the row and column null
        nullColumn(highColNumber, processMatrix, exponents);
        nullRow(highRowNumber, processMatrix, exponents);
    }

    public void nullRow(int rowNumber, int[][] processMatrix, int[][] exponents) {
        for (int colCounter = 0; colCounter < processMatrix[rowNumber].length; ++colCounter) {
            processMatrix[rowNumber][colCounter] = -1;
            exponents[rowNumber][colCounter] = -1;
        }
    }

    public void nullColumn(int colNumber, int[][] processMatrix, int[][] exponents) {
        for (int rowCounter = 0; rowCounter < processMatrix.length; ++rowCounter) {
            processMatrix[rowCounter][colNumber] = -1;
            exponents[rowCounter][colNumber] = -1;
        }
    }


    public String calculateTotalCost(boolean[][] markedPoints, int[][] givenMatrix) {
        int totalCost = 0;

        for (int rowCounter = 0; rowCounter < markedPoints.length; ++rowCounter) {
            for (int colCounter = 0; colCounter < markedPoints[rowCounter].length; ++colCounter) {
                if (markedPoints[rowCounter][colCounter]) {
                    totalCost += givenMatrix[rowCounter][colCounter];
                }
            }
        }
        return "Total Cost: " + String.valueOf(totalCost);
    }

    public String calculatePath(String[] letters, boolean[][] markedPoints) {
        String path;
        int currentRow = 0;
        int colCounter = 0;
        boolean isDisplaying = true;

        path = "Path: " + letters[currentRow] + "->";

        while (isDisplaying) {
            if (markedPoints[currentRow][colCounter]) {
                path += letters[colCounter];

                currentRow = colCounter;
                colCounter = 0;

                if (currentRow == 0)
                    isDisplaying = false;
                if (isDisplaying)
                    path += "->";

                continue;
            }
            ++colCounter;
        }
        return path;
    }
    public void deleteResultsFile() {
        File resultFile = new File("results.txt");
        if (!resultFile.delete())
            System.out.println("Failed to delete file please try again");
    }

    public void storeResultsToFile(String processName) {
        try {
            FileWriter fileWriter = new FileWriter("results.txt", true);
            PrintWriter matrixWriter = new PrintWriter(fileWriter);
            matrixWriter.println(processName);

            for (int[] rowValues : processMatrix) {
                for (int colValues : rowValues) {
                    matrixWriter.print(colValues + " ");
                }
                matrixWriter.println();
            }

            fileWriter.close();
            matrixWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
