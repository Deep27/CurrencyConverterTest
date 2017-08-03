package CurrencyConverter.ConverterPage;

import CurrencyConverter.ConverterPage.Window.ConverterWindow;
import CurrencyConverter.ConverterPage.Window.QuotesWindow;
import CurrencyConverter.ConverterPage.Window.RatesConverterResultWindow;
import org.openqa.selenium.WebDriver;

/**
 * Created by RomanSo on 03.08.17.
 */

public class ConverterPage extends PageObject
{
    private ConverterWindow converterWindow;
    private QuotesWindow quotesWindow;
    private RatesConverterResultWindow ratesConverterResultWindow;

    public ConverterPage(WebDriver driver)
    {
        super(driver);
        if (!"«Сбербанк» - калькулятор иностранных валют".equals(driver.getTitle()))
            throw new IllegalStateException("This is not converter page!");
    }

    public RatesConverterResultWindow convert()
    {
        return converterWindow.convert();
    }
}
