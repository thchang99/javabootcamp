package calculator;

import java.util.Stack;
import java.util.Iterator;

class Postfixify extends OperatorEvaluator{
    public static Stack<String> run(Stack<String> st){
        Iterator<String> i = st.iterator();
        Stack<String> hold = new Stack<>();
        Stack<String> postfix = new Stack<>();
        String ops;
        while (i.hasNext()) {
            ops = i.next();
            if (isNum(ops)) {
                postfix.push(ops);
            } else {
                while (!hold.isEmpty() && priority(ops) <= priority(hold.peek())) {
                    postfix.push(hold.peek());
                    hold.pop();
                }
                hold.push(ops);
            }
        }
        while (!hold.isEmpty()) {
            postfix.push(hold.peek());
            hold.pop();
        }
        System.out.println("postfix" + postfix);
        return postfix;

    }
}