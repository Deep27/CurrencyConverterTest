package CurrencyConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by RomanSo on 04.08.17.
 */

public class LocalConverter
{
    public static double convert(double value, double buyRate, double sellRate,
                                 double fromCoefficient, double toCoefficient)
    {
        double result = value * (buyRate / sellRate) * (toCoefficient / fromCoefficient);
        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double getDouble(String s)
    {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number;
        try {
            number = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1.0;
        }
        return number.doubleValue();
    }
}
