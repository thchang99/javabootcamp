package calculator;

import java.util.Stack;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

public class StackedExpressionEvaluator extends OperatorEvaluator {
    public static String evaluate(Stack<String> st) {
        String op = st.peek();
        if (isEsign(op)) {
            return calculate(st);
        } else if (isOperator(op)) {
            return calculate(st);
        } else {
            return st.peek();
        }

    }

    public static String calculate(Stack<String> st) {
        st.pop(); // removes "=" or operators;
        Iterator<String> i = st.iterator();
        Stack<String> se = new Stack<>();
        String op;
        while (i.hasNext()) {
            op = i.next();
            if (isNum(op)) {
                se.push(op);
            } else if (isOperator(op)) {
                String num2 = se.peek();
                se.pop();
                String num1 = se.peek();
                se.pop();
                se.push(calculate(num1, num2, op));
            }
        }

        return se.peek();

    }

    public static String calculate(String num1, String num2, String operator) throws ArithmeticException {
        BigDecimal bnum1 = new BigDecimal(num1);
        BigDecimal bnum2 = new BigDecimal(num2);
        BigDecimal result;
        switch (operator) {
            case "+":
                result = bnum1.add(bnum2);
                break;
            case "-":
                result = bnum1.subtract(bnum2);
                break;
            case "*":
                result = bnum1.multiply(bnum2);
                break;
            case "/":
                result = bnum1.divide(bnum2, 99, RoundingMode.HALF_UP);
                break;
            default:
                return "ERROR";
        }
        return result.toString();

    }

    // public static double StringToDouble(String num) {
    // boolean dot = false;
    // boolean dotfinal = false;
    // boolean neg = false;
    // double decpoint = 1;
    // double result = 0;
    // for (char c : num.toCharArray()) {
    // if (c == '±' || c == '-') {
    // neg = !(neg);
    // } else if (c == '.') {
    // dot = !(dot);
    // } else {
    // if (dot) {
    // dotfinal = true;
    // }
    // if (dotfinal) {
    // decpoint *= 10;
    // result += (c - '0') / decpoint;
    // } else
    // result = result * 10 + c - '0';
    // }
    // }
    // if (neg == true) {
    // result *= -1;
    // }

    // return result;
    // }
}
