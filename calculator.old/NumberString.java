package calculator;

public class NumberString {
    String number;
    Boolean modifiable;

    NumberString()
    {
        number = "";
        modifiable = true;
    }
    public void appendTo(String s){

    }
    public void append(String s)
    {

    }
    public void add()
    {

    }
    public void setFinal()
    {
        modifiable = false;
    }

    public Boolean isFinal() {
        return  modifiable;
    }
    @Override
    public String toString()
    {
        return number;
    }
}
