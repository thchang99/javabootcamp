package calculator;

import java.util.Stack;

// import java.util.Deque;
// import java.util.LinkedList;

public class NewOperationHandler extends OperatorEvaluator {
    /*
     * 1. String is inputted
     * 2. stack representing all inputed things
     * 2.1 if top is number add to it
     * 2.1.1 restack
     * 2.2 if top is operator or "=" add new
     * 2.2 if top is "="
     * calculate up until then
     * if top is "=" again do previous operation
     * 3. e
     * <Character>
     */
    ExpressionStacker stacker;
    boolean repeat;
    String previous_operand;
    String previous_operator;

    public NewOperationHandler() {
        reset();
        repeat = false;
    }

    public void reset() {
        stacker = new ExpressionStacker();
        System.out.println("StackReset!");
    }

    public String input(String s) {
        try {
            if (s.equals("AC")) { //if AC is inputed reset and return the output as 0
                reset();
                return "0";
            }
            String result = "";
            repetition(s);
            stacker.stack(s);
            Stack<String> st = Postfixify.run(stacker.getSt());
            result = StackedExpressionEvaluator.evaluate(st);

            // if "=" , resets the stack with the result value
            if (isEsign(s)) {
                reset();
                stacker.stack(result);
                repeat = true;
            }
            return result;
        } catch (ArithmeticException ae) {
            reset();
            throw new ArithmeticException();
        }
    }

    public void repetition(String s) {
        // saves for repitition and reenter saved items if repeated
        if (isOperator(s)) {
            previous_operator = s;
        }
        if (isNum(s)) {
            previous_operand = s;
        }
        if (repeat && isEsign(s)) {
            stacker.stack(previous_operator);
            stacker.stack(previous_operand);
        } else if (repeat) {
            repeat = false;
        }
    }

}
