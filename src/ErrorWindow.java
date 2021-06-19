import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorWindow extends JFrame{
    JFrame errorInput = new JFrame();
    JLabel labelInput = new JLabel("Error, Input number, Please Try again");

    ErrorWindow(){
        InputError();
    }

    // Window for NumberFormat exception
    public void InputError()
    {
        errorInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        errorInput.setBounds(500,300, 400,100);
        labelInput.setSize(50,40);
        labelInput.setHorizontalAlignment((int)JFrame.CENTER_ALIGNMENT);
        errorInput.add(labelInput);
        errorInput.setVisible(true);
    }

}
