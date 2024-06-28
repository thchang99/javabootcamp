package calculator;

import java.util.Stack;

public class StringExpressionStacker extends OperatorEvaluator {
    Stack<NumberString> st;
    NumberString num;
    public StringExpressionStacker() {
        st = new Stack<>();
    }
    public StringExpressionStacker(NumberString num) {
        st = new Stack<>();
        st.push(num);
    }
    public void stack(String s) {
        if (st.isEmpty()) { // if stack is empty only allow number pushes
            if (isNum(s)) {
                st.push(new NumberString(s));
            }
        } else {
            if (isNum(s)) { // if the entered value is a number
                if (st.peek().isOperator()) { // if previous value is operator
                    st.push(new NumberString(s));
                } else {
                    st.peek().add(s); // else add number
                }
            } else if (isModifier(s) && !st.peek().isFinal()) { // if it modifies a number
                // if (st.peek().isOperator()) { //can modify even after it is pressed
                //     NumberString n = st.peek();
                //     st.pop();
                //     st.peek().modify(s);
                //     st.push(n);
                // } else
                    System.out.println("modify!");
                    st.peek().modify(s);
            } else if (isOperator(s)) { // if its a operator
                if (!st.peek().isOperator()) {
                    st.push(new NumberString(s)); // if the top is not a numberstring
                }
                st.peek().setOperator(s); 
            }
            else if(isEsign(s))
            {
                st.push(new NumberString(s));
            }

        }
        System.out.println(st);
    }

    public Stack<NumberString> getSt() {
        return st;
    }

    public boolean getFinal()
    {
        return !st.isEmpty() && st.peek().isFinal();
    }
}