import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class InputWindow extends JFrame implements ActionListener{

    // ComboBox values
    String [] matrixSize = {"3x3","4x4","5x5","6x6"};

    // Instantiation of objects for GUI
    JFrame frame = new JFrame("Traveling Salesman");
    JComboBox boxSize = new JComboBox(matrixSize);
    JPanel panel = new JPanel();
    JTextField[][] txt;
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

        // Combobox design and simulations
        frame.add(boxSize);
        boxSize.setBounds(100,20,200,25);
        boxSize.addActionListener(this);

        // Add panel and set frame visible
        frame.add(panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == boxSize)
        {
            int row;
            int column;

            // Get the user's wanted size form the comboBox
            String size  = (String) boxSize.getSelectedItem();

            String [] s = size.split("x");
            row = Integer.parseInt(s[0]);
            column = Integer.parseInt(s[1]);
            gridLayout(row,column);
        }
    }
    public void gridLayout(int row, int column){
        txt = new JTextField[row][column];
        panel.removeAll();
        panel.revalidate();
        frame.repaint();

        for(int i=0; i<row; i++) {
            for (int j = 0; j < column; j++) {
                if (i != j) {
                    txt[i][j] = new JTextField(3);
                    gbc.gridx = i;
                    gbc.gridy = j;
                    panel.add(txt[i][j], gbc);
                    txt[i][j].setHorizontalAlignment(JTextField.CENTER);
                } else {
                    txt[i][j] = new JTextField("-", 3);
                    gbc.gridx = i;
                    gbc.gridy = j;
                    panel.add(txt[i][j], gbc);
                    txt[i][j].setHorizontalAlignment(JTextField.CENTER);
                    txt[i][j].setEditable(false);
                }
            }
        }
    }
}
