package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JTextField jtf;
    private StringOperationHandler op;
    private JLabel expression;
    final private String[] l = { "AC", "±", "<", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0",
            ".", "=" };

    // private NewOperationHandler op;
    // private OperationHandler op;
    GUI() {
        op = new StringOperationHandler();
        // op = new NewOperationHandler();
        // op = new OperationHandler();
        setTitle("Calculator");
        setSize(400, 600);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 40));
        // design
        add(new JPanel(), BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.SOUTH);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.WEST);
        // main calculator panel
        JPanel mainpanel = new JPanel(new GridLayout(2, 0));
        add(mainpanel);
        JPanel subpanel = new JPanel(new BorderLayout(10, 10));
        mainpanel.add(subpanel);
        jtf = new JTextField("0");
        Font font = new Font("Agency FB", Font.PLAIN, 35);
        jtf.setFont(font);
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        subpanel.add(jtf);
        expression = new JLabel();
        expression.setHorizontalAlignment(JTextField.RIGHT);
        subpanel.add(expression, BorderLayout.NORTH);
        subpanel.add(new JPanel(), BorderLayout.SOUTH);
        GridLayout Layout = new GridLayout(5, 4, 0, 0);
        JPanel main = new JPanel(Layout);
        for (String s : this.l) {
            if (s.equals("0")) {
                JPanel dummy = new JPanel();
                main.add(dummy);
            }
            JButton btn = new JButton(s);
            btn.setActionCommand(s);
            btn.addActionListener(this);
            main.add(btn);
        }

        mainpanel.add(main);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String result;
        String command = e.getActionCommand();
        try {
            String calculation = op.input(command);
            result = FormatHandler.format(calculation, 10);
            if (calculation.endsWith("."))
                result += ".";
        } catch (ArithmeticException ae) {
            result = "DIV/0";
            op.reset();
        } catch (Exception ee) {
            ee.printStackTrace();
            op.reset();
            result = "Error";
        }
        jtf.setText(result);
        expression.setText(op.getExpression());
    }

    public static void main(String[] args) {
        new GUI();
    }
}
