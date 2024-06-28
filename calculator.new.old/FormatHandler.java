package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatHandler extends BigDecimal {
    FormatHandler(String s) {
        super(s);
    }

    public static String format(String in) {

        BigDecimal num = new BigDecimal(in);
        num = num.setScale(num.scale() + 10 - num.precision(), RoundingMode.HALF_UP);
        FormatHandler format = new FormatHandler(num.toString());
        return format.stripTrailingZeros().toString();

        // FormatHandler num = new FormatHandler(in);
        // num = num.setScale(num.scale() + 10 - num.precision(), RoundingMode.HALF_UP);
        // return num.stripTrailingZeros().toString();

    }

    @Override
    public FormatHandler setScale(int newScale, RoundingMode roundingMode) {
        super.setScale(newScale, roundingMode);
        return this;
    }

    @Override
    public BigDecimal stripTrailingZeros() {
        // System.out.println("Scale:" + this.scale());
        // System.out.println("Precision:" + this.precision());
        if (this.scale() >= 0 && //is above 0 when it fits the scaling to precision ratio
                this.subtract(BigDecimal.valueOf(this.longValue())).signum() == 0) { //find if there are fractions
            return BigDecimal.valueOf(this.longValue());
        } else {
            return super.stripTrailingZeros();
        }
    }
}
