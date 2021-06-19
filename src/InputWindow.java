import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class InputWindow extends JFrame implements ActionListener{

    guiAlgo process = new guiAlgo();

    // array declarations
    String [] matrixSize = {"3x3","4x4","5x5","6x6"};
    String[] letters = {"a", "b", "c", "d", "e", "f"};

    int [][] givenMatrix;
    int [][] processMatrix;
    int [][] exponents;
    int [][] checker;
    boolean [][] markedPoints;

    int buttonCount = 0;
    int row;
    int column;
    int i = 0;
    int j = 0;

    // Instantiation of objects for GUI
    JFrame frame = new JFrame("Traveling Salesman");
    JComboBox boxSize = new JComboBox(matrixSize);
    JPanel panel = new JPanel();
    JButton right = new JButton(">>");
    JButton enter = new JButton("ENTER");
    JButton left = new JButton("<<");
    JLabel steps = new JLabel("Enter numbers in designated rows and columns:");

    JLabel cost;
    JLabel path;
    JTextField[][] txt;
    JLabel[][] labels;
    GridBagConstraints gbc = new GridBagConstraints();


    //Constructor
    InputWindow()
    {
        // frame design and measurement
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400,100,500,500);
        //panel.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        // Combobox design and simulations
        frame.add(boxSize);
        boxSize.setBounds(200,20,100,25);
        boxSize.addActionListener(this);

        //steps label layout
        steps.setBounds(110,100,400,50);
        steps.setFont(new Font("Arial",Font.BOLD,12));
        frame.add(steps);

        //buttons size and layout
        frame.add(right);
        frame.add(enter);
        frame.add(left);
        right.addActionListener(this);
        enter.addActionListener(this);
        left.addActionListener(this);
        right.setBounds(350,400,80,50);
        enter.setBounds(200,400,80,50);
        left.setBounds(50,400,80,50);

        // Add panel and set frame visible
        frame.add(panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        // box size action
        if (e.getSource() == boxSize) {

            // Get the user's wanted size form the comboBox
            String size = (String) boxSize.getSelectedItem();

            String[] s = size.split("x");
            row = Integer.parseInt(s[0]);
            column = Integer.parseInt(s[1]);
            gridLayout(row, column);
        }
        //enter button action
        if(e.getSource() == enter){

            buttonCount -= buttonCount;

            givenMatrix = new int[row][column];
            processMatrix = new int[row][column];
            exponents = new int[row][column];
            markedPoints = new boolean[row][column];
            checker = new int[row][column];


            // Get all value on each textFields
            for (i = 0; i < row; i++) {
                for (j = 0; j < column; j++) {
                    if (i != j) {
                        givenMatrix[i][j] = Integer.parseInt(txt[i][j].getText());
                        processMatrix[i][j] = givenMatrix[i][j];
                        exponents[i][j] = 0;
                        markedPoints[i][j] = false;
                        checker[i][j] = -1;
                    } else {
                        givenMatrix[i][j] = -1;
                        processMatrix[i][j] = givenMatrix[i][j];
                        exponents[i][j] = 0;
                        markedPoints[i][j] = false;
                        checker[i][j] = -1;
                    }
                }
            }
            process.deleteResultsFile();
            while (!Arrays.deepEquals(processMatrix,checker)) {
                System.out.println("Row Minimization");
                process.rowMinimization(processMatrix);
                process.storeResultsToFile("Row Minimization",processMatrix);

                System.out.println("Column Minimization");
                process.columnMinimization(processMatrix);
                process.storeResultsToFile("Column Minimization", processMatrix);

                System.out.println("Reduce Matrix");
                process.calculatePenalty(processMatrix, exponents);
                process.reduceMatrix(exponents,markedPoints, processMatrix);
                process.storeResultsToFile("Reduce Matrix", processMatrix);
            }
        }

        //right button action
        if (e.getSource() == right) {


        }

        //left button action
        if (e.getSource() == left) {



            }
        }

    // layout for combo box options
    public void gridLayout(int row, int column){
        txt = new JTextField[row][column];
        panel.removeAll();
        panel.revalidate();
        frame.repaint();

        for(i=0; i<row; i++) {
            for (j = 0; j < column; j++) {
                if (i != j) {
                    txt[i][j] = new JTextField();
                    gbc.gridx = i;
                    gbc.gridy = j;
                    txt[i][j].setFont(new Font("Arial",Font.PLAIN,15));
                    panel.add(txt[i][j], gbc);
                    txt[i][j].setHorizontalAlignment(JTextField.CENTER);
                } else {
                    txt[i][j] = new JTextField("-",3);
                    gbc.gridx = i;
                    gbc.gridy = j;
                    txt[i][j].setFont(new Font("Arial",Font.PLAIN,15));
                    panel.add(txt[i][j], gbc);
                    txt[i][j].setHorizontalAlignment(JTextField.CENTER);
                    txt[i][j].setEditable(false);
                }
            }
        }
    }

    // next or right button action
    public void nextButton(int[][] processMatrix)
    {
        panel.removeAll();
        panel.revalidate();
        frame.repaint();

        labels = new JLabel[row][column];
        for(i=0;i<row;i++){
            for(j=0;j<column;j++){
                if(i != j)
                {
                    labels[i][j] = new JLabel(Integer.toString(processMatrix[i][j]));
                    gbc.gridx = i;
                    gbc.gridy = j;
                    labels[i][j].setFont(new Font("Arial",Font.BOLD,25));
                    panel.add(labels[i][j],gbc);
                    labels[i][j].setHorizontalAlignment(JLabel.CENTER);
                }else
                {
                    labels[i][j] = new JLabel("-");
                    gbc.gridx = i;
                    gbc.gridy = j;
                    labels[i][j].setFont(new Font("Arial",Font.PLAIN,25));
                    panel.add(labels[i][j],gbc);
                }
            }
        }

    }

    public void displayCost(){
        panel.removeAll();
        panel.revalidate();
        frame.repaint();

        cost = new JLabel(process.calculateTotalCost(markedPoints, givenMatrix));
        path = new JLabel(process.calculatePath(letters, markedPoints));

        path.setFont(new Font("Arial",Font.BOLD,25));
        cost.setFont(new Font("Arial",Font.BOLD,25));

        gbc.gridx =0;
        gbc.gridy =0;
        panel.add(cost,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(path,gbc);

    }
    public void readFile(){
    try{
        File text = new File("results.txt");
        Scanner read = new Scanner(text);
        while(read.hasNextInt()){
            nextButton();
        }
        read.close();
    }catch(FileNotFoundException e){
        e.printStackTrace();

        }
    }

}
