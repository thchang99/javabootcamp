package calculator;

public class OperatorEvaluator {
    public static boolean isEsign(String str) {
        return str.equals("=");
    }

    public static boolean isNum(String str) {
        return !isEsign(str) && !isOperator(str);
    }

    public static boolean isOperator(String str) {

        switch (str) {
            case "+":
            case "-":
            case "/":
            case "*":
                return true;
        }
        return false;
    }

    public static int priority(String str) {
        switch (str) {
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            case "=":
                return 0;
            default:
                return -1;
        }
    }
}
