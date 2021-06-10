import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class InputWindow extends JFrame implements ActionListener{

    // ComboBox values
    String [] matrixSize = {"3x3","4x4","5x5","6x6"};
    int row = 0;
    int column = 0;

    // Instantiation of objects for GUI
    JFrame frame = new JFrame("Traveling Salesman");
    JComboBox boxSize = new JComboBox(matrixSize);
    JTextField input = new JTextField();
    JPanel panel = new JPanel();

    //Constructor
    InputWindow()
    {
        // frame design and measurement
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new FlowLayout());

        // Combobox design and simulations
        frame.add(boxSize);
        frame.pack();
        boxSize.setBounds(100,20,200,25);
        boxSize.addActionListener(this);


        //textfield size and design
        input.setSize(50,50);

        // Add panel and set frame visible
        frame.add(panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == boxSize)
        {
            // Get the user's wanted size form the comboBox
            String size  = (String) boxSize.getSelectedItem();

            // Split the strings of size to be the rows and column
            String s [] = size.split("x");
            row = Integer.parseInt(s[0]);
            column = Integer.parseInt(s[1]);

            // Table by rows and column for user's input
            for(int i = 0;i < row;i++)
            {
                for(int j = 0;j < column;j++)
                {

                }
            }

        }
    }
}
