package calculator;

import java.util.Stack;

public class ExpressionStacker extends OperatorEvaluator {
    Stack<String> st;

    public ExpressionStacker() {
        st = new Stack<>();
    }

    public void stack(String s) {
        if (modifier(s))
            return;
        if ((st.isEmpty() || isOperator(st.peek())) && isNum(s)) {
            st.push(s);
        } else if (!isOperator(st.peek()) && isNum(s)) {
            String num = st.peek() + s;
            st.pop();
            st.push(num);
        } else if (isOperator(s) || isEsign(s)) { // unnecssary but for readabilty
            if (isOperator(st.peek())) {
                st.pop();
            }
            st.push(s);
        }

    }

    private boolean modifier(String s) {
        String operator = null;
        boolean modded = false;

        if(st.isEmpty() && s.equals("."))
        {
            st.push("0.");
            return true;
        }
        if (!st.isEmpty() && isOperator(st.peek())) // takes out operator if its on top;
        {
            operator = st.peek();
            st.pop();
        }
        if (!st.isEmpty() && s.equals("±")) {
            modded = true;
            if (st.peek().charAt(0) == '-') {
                String num = st.peek().substring(1);
                st.pop();
                st.push(num);
            } else {
                if (!st.isEmpty()) {
                    String num = "-" + st.peek();
                    st.pop();
                    st.push(num);
                }
            }
        }

        else if (!st.isEmpty() && s.equals(".")) {
            modded = true;
            if (st.peek().charAt(st.peek().length() - 1) == '.') {
                String num = st.peek().substring(0, st.peek().length() - 1 - 1);
                st.pop();
                st.push(num);
            } else if (!st.peek().contains(".")) {
                String num = st.peek() + ".";
                st.pop();
                st.push(num);
            }
        }

        if (operator != null) {

            st.push(operator);
        }
        return modded;
    }

    public Stack<String> getSt() {
        return st;
    }
}
