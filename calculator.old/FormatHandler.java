package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatHandler {
    public static String format(String in)
    {
        BigDecimal num = new BigDecimal(in);
        num = num.setScale(11, RoundingMode.HALF_UP);
        return num.stripTrailingZeros().toString();
    }
}
