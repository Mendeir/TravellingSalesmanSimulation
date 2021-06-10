import java.awt.*;
import java.sql.SQLOutput;

import javax.swing.*;

public class Main {


    public static void main(String[] args){

        // ComboBox values
        String [] matrixSize = {"3x3","4x4","5x5","6x6"};

        // Instantiation of objects for GUI
        JFrame frame = new JFrame("Traveling Salesman");
        JComboBox boxSize = new JComboBox(matrixSize);



        // frame design and measurement
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new FlowLayout());

        // Combobox design
        frame.add(boxSize);
        frame.pack();
        boxSize.setBounds(100,20,200,25);
        frame.setVisible(true);




    }
}
