package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JTextField jtf;
    private double result;
    private NewOperationHandler op;
    // private OperationHandler op;
    GUI() {
        op = new NewOperationHandler();
        // op = new OperationHandler();
        setTitle("Calculator");
        setSize(400, 600);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        result = 0;
        jtf = new JTextField("0");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(jtf, gbc);

        GridLayout Layout = new GridLayout(5, 4, 0, 0);
        JPanel main = new JPanel(Layout);
        String[] l = {"AC", "+", "-", "*", "/", "±", "=", ".", "0", "1", "2", "3", "4", "5","6","7","8","9" };
        for (String s : l) {
            JButton btn = new JButton(s);
            btn.setActionCommand(s);
            btn.addActionListener(this);
            main.add(btn);
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(main, gbc);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String result;
        String command = e.getActionCommand();
        try{
            result = FormatHandler.format(op.input(command));
        }
        catch (ArithmeticException ae)
        {
            result = "DIV/0";
            op.reset();
        }
        catch (Exception ee)
        {
            ee.printStackTrace();
            op.reset();
            result = "Error";
        }
        jtf.setText(result);
    }
    public static void main(String[] args) {
        new GUI();
    }
}
