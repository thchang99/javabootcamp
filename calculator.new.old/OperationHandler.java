package calculator;

// import java.util.Deque;
// import java.util.LinkedList;
import java.util.Stack;
import java.util.Iterator;

public class OperationHandler {
    char operator;
    boolean equals;
    Stack<Character> st;
    double operand;

    OperationHandler() {
        reset();
    }

    public void reset() {
        st = new Stack<>();
        this.operator = 0;
        this.equals = false;
        this.operand = 0;
    }

    /*
     * 1. use queue
     * if queue is empty, dont take anything else than numbers
     * 
     * if number is pressed add to the queue;
     * 
     * if there is a operator in the queue, if last is not operator
     * operator is pressed do calculation
     * put operator at the end of new queue
     * 
     * when = is pressed
     * do calculation
     * set operator to false
     * 
     * 
     * calcuation : after calculation the result must go in queue in correct order
     * (use stack);
     * 
     * 
     * 
     * 1. command s is received as operators or operands
     * 1.1 if both pre and this is num, make bigger number
     * return appended result
     * 1.2 if pre is num and this is op
     * 1.2.1 when preresult is 0 and prezero is false
     * op needs to be saved and be in pre
     * 1.2.2 when preresult is not 0
     * do calculator operation on result
     * change operator to op
     * return result
     * 
     * 1.3 if pre is num and this is equals
     * if preresult is not zero
     * result needs to be calculated
     * return result
     * 
     * 1.3 if pre is operator and this is num
     * preresult for operation later
     * reset result
     * return result
     * 
     * 1.4 if pre is operator and this is operator
     * set pre to this
     * return result
     * 
     * 1.5 if pre is operator and this is equals, preresult is result
     */
    public double input(String s) throws Exception {
        if (s.equals("AC")) {
            reset();
            return 0;
        }
        char op = s.charAt(0);
        double result = 0;
        if (isNum(op)) {
            if (equals)
                reset();
            st.addLast(op);
            return toNum();
        } else if (isOperator(op) && !st.isEmpty()) { // operator is only considered when stack is not empty
            equals = false;
            if (operator == 0 || isOperator(st.peek())) { // no operator in the stack or top is operator
                operator = op;
                st.addLast(op);
            } else if (operator != 0) {
                if (!postIsPrio(operator, op)) {
                    operator = op;
                    result = calculate();
                    st.addLast(op);
                } else {
                    operator = op;
                    st.addLast(op);
                }
            }
        } else if (op == '=') {
            if (equals) {
                st.addLast(operator);
                restack(operand); // adds the last operator and operand to stack
            } else if (operator == 0) // occurs when theres no operator to operate on
            {
                return toNum();
            }
            equals = true;
            result = calculate();
        }
        return result;

    }

    private boolean postIsPrio(char preop, char postop) {
        if (postop == '*' || postop == '/')
            return (preop == '+' || preop == '-');

        return false;
    }

    private double calculate() {
        double num1, num2;
        double result;
        char operator;
        num2 = toNum();
        operand = num2; // gets the previous operand for entering = multiple times
        while (isNum(st.peek())) {
            st.pop();
        }
        operator = st.peek();
        while (!isNum(st.peek())) {
            st.pop();
        }
        num1 = toNum();
        while (!st.isEmpty() && isNum(st.peek())) {
            st.pop();
        }
        result = operate(num1, num2, operator);
        restack(result);

        if (st.contains('+') || st.contains('-')) {
            result = calculate();
        }
        return result;
    }

    private void restack(double result) {
        String str = String.format("%.0f", result);
        for (char c : str.toCharArray()) {
            st.push(c);
        }
        System.out.println(st);
    }

    private double operate(double num1, double num2, char operator) throws ArithmeticException {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) throw new ArithmeticException("DIV 0");
                return num1 / num2;
            default:
                return -2;
        }
    }

    private double toNum() { // convert stack into number
        Iterator<Character> i = st.iterator();
        char c;
        double result = 0;
        boolean dot = false;
        boolean neg = false;
        while (i.hasNext()) {
            c = i.next();
            if (!isNum(c))
                result = 0;
            if (isDigit(c)) {
                result = result * 10 + (c - '0');
            }
            if (!dot) {

            }
        }
        return result;
    }

    private boolean isNum(char c) {
        return (isDigit(c) || c == '.' || c == '±');
    }

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    private boolean isOperator(char c) {
        // allow for adding of more operators easily
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
        }
        return false;
    }
}
