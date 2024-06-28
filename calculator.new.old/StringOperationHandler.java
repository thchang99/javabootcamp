package calculator;

import java.util.Stack;

import java.util.Iterator;

// import java.util.Deque;
// import java.util.LinkedList;

public class StringOperationHandler extends OperatorEvaluator {
    StringExpressionStacker stacker;
    boolean repeat;
    String previous_operand;
    String previous_operator;

    public StringOperationHandler() {
        reset();
    }

    public void reset() {
        stacker = new StringExpressionStacker();
        System.out.println("StackReset!");
    }

    public String input(String s) {
        try {
            if(stacker.getSt().isEmpty() && isModifier(s))
            {
                input("0");
            }
            if (s.equals("AC")) { // if AC is inputed reset and return the output as 0
                reset();
                return "0";
            }
            String result = "";
            repetition(s);
            stacker.stack(s);
            previous_saver(s);
            Iterator<NumberString> i = stacker.getSt().iterator();
            Stack<String> st = new Stack<>();
            while (i.hasNext()) {
                st.push(i.next().toString());
            }
            Stack<String> post = Postfixify.run(st);
            result = StackedExpressionEvaluator.evaluate(post);

            System.out.println(result);
            // if "=" , resets the stack with the result value
            if (isEsign(s)) {
                reset();
                NumberString n = new NumberString(result);
                n.setFinal();
                stacker.getSt().push(n);
            }
            return result;

        } catch (ArithmeticException ae) {
            reset();
            throw new ArithmeticException();
        }
    }

    public void repetition(String s) {
        // saves for repitition and reenter saved items if repeated

        if (stacker.getFinal()) {
            if (isEsign(s)) {
                stacker.stack(previous_operator);
                stacker.stack(previous_operand);
            } else if (isNum(s)) {
                reset();
            }
        }
    }

    public void previous_saver(String s)
    {
        if (!stacker.getSt().isEmpty() && !isEsign(s)) {
            if (isOperator(stacker.getSt().peek().toString())) {
                previous_operator = stacker.getSt().peek().toString();
            }
            if (isNum(stacker.getSt().peek().toString())) {
                previous_operand = stacker.getSt().peek().toString();
            }
        }
    }

}
