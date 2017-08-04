package CurrencyConverter.ConverterPage;

import CurrencyConverter.ConverterPage.Window.ConverterWindow.ConverterWindow;
import CurrencyConverter.ConverterPage.Window.QuotesWindow;
import org.openqa.selenium.WebDriver;

/**
 * Created by RomanSo on 03.08.17.
 */

public class ConverterPage extends PageObject
{
    private ConverterWindow converterWindow;
    private QuotesWindow quotesWindow;

    public ConverterPage(WebDriver driver)
    {
        super(driver);
        if (!"«Сбербанк» - Калькулятор иностранных валют".equals(driver.getTitle()))
            throw new IllegalStateException("This is not converter page!");

        converterWindow = new ConverterWindow(this.driver);
        quotesWindow = new QuotesWindow(this.driver);
    }

    public ConverterWindow converterWindow() {
        return converterWindow;
    }

    public QuotesWindow quotesWindow() {
        return quotesWindow;
    }
}
