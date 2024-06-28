package calculator;

public class NumberString {
    String number;
    Boolean modifiable;
    Boolean negative;
    Boolean operator;

    NumberString() {
        this.number = "0";
        modifiable = true;
        negative = false;
        operator = false;
    }
    NumberString(String s) {
        this();
        this.number = s;
    }

    public void del() {
        if (this.number.length() == 1)
            this.number = "0";
        else
            this.number = this.number.substring(0, this.number.length() - 1 - 1);
    }

    public void add(String s) {
        if(this.number == "0")
        {
            this.number = s;
        }
        this.number = this.number + s;
    }
    public void setOperator(String s) {
        this.number = s;
        operator = true;
    }
    public boolean isOperator(){
        return operator;
    }
    public void modify(String s) {
        switch (s) {
            case ".":
                decpoint();
                break;
            case "±":
                negate();
                break;
        }
    }

    public void decpoint() {
        if (!this.number.contains(".")) {
            this.number = this.number + ".";
        } else if (this.number.endsWith(".")) {
            this.number = this.number.substring(0, this.number.length() - 2);
        }

    }

    public void negate() {
        negative = !negative;
    }

    public void setFinal() {
        modifiable = false;
    }

    public boolean isFinal() {
        return !modifiable;
    }

    @Override
    public String toString() {
        if (negative)
            return "-" + number;
        return number;
    }
}
