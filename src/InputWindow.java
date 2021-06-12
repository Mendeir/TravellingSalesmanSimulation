import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow extends JFrame implements ActionListener{

    // array declarations
    String [] matrixSize = {"3x3","4x4","5x5","6x6"};
    int [][] values;
    int row;
    int column;
    int i = 0;
    int j = 0;

    // Instantiation of objects for GUI
    JFrame frame = new JFrame("Traveling Salesman");
    JComboBox boxSize = new JComboBox(matrixSize);
    JPanel panel = new JPanel();
    JButton right = new JButton(">>");
    JButton left = new JButton("<<");
    JTextField[][] txt;
    JButton [][] box;
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gbl = new GridBagLayout();


    //Constructor
    InputWindow()
    {
        // frame design and measurement
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400,100,500,500);
        //panel.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Combobox design and simulations
        frame.add(boxSize);
        boxSize.setBounds(200,20,100,25);
        boxSize.addActionListener(this);

        //buttons size and layout
        frame.add(right);
        frame.add(left);
        right.addActionListener(this);
        left.addActionListener(this);
        right.setBounds(350,400,80,50);
        left.setBounds(50,400,80,50);

        // Add panel and set frame visible
        frame.add(panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // box size action
        if(e.getSource() == boxSize)
        {

            // Get the user's wanted size form the comboBox
            String size  = (String) boxSize.getSelectedItem();

            String [] s = size.split("x");
            row = Integer.parseInt(s[0]);
            column = Integer.parseInt(s[1]);
            gridLayout(row,column);
        }

        //right button action
        if(e.getSource() == right)
        {

            panel.removeAll();
            panel.revalidate();
            frame.repaint();
            values = new int[row][column];
            // Get all value on each textFields
            for(i = 0;i < row;i++){
                for(j = 0;j < column;j++){
                    if(i != j)
                    {
                        values[i][j] = Integer.parseInt(txt[i][j].getText());
                    }else
                    {
                        values[i][j] = -1;
                    }
                }
            }
            //nextButton(row,column);
        }

        //left button action
        if(e.getSource() == left)
        {
            panel.removeAll();
            panel.revalidate();
            frame.repaint();
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
                    panel.add(txt[i][j], gbc);
                    txt[i][j].setHorizontalAlignment(JTextField.CENTER);
                } else {
                    txt[i][j] = new JTextField("-",3);
                    gbc.gridx = i;
                    gbc.gridy = j;
                    panel.add(txt[i][j], gbc);
                    txt[i][j].setHorizontalAlignment(JTextField.CENTER);
                    txt[i][j].setEditable(false);
                }
            }
        }
    }

    public void nextButton(int row,int column)
    {

        /*
        for(i=0;i<row;i++){
            for(j=0;j<column;j++){
                if(i != j)
                {

                }
            }
        }
        */
    }
}
