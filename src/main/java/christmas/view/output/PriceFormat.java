package christmas.view.output;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public enum PriceFormat {
    ORDER_PRICE_FORMAT(new DecimalFormat("###,###원")),
    BENEFIT_PRICE_FORMAT(new DecimalFormat("-###,###원"));

    private final NumberFormat numberFormat;

    PriceFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }

    public String format(double number) {
        return numberFormat.format(number);
    }
    public String format(long number) {
        return numberFormat.format(number);
    }
}
