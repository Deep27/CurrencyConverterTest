package CurrencyConverter.ConverterPage;

import CurrencyConverter.ConverterPage.Window.ConverterWindow.ConverterWindow;
import org.openqa.selenium.WebDriver;

/**
 * Created by RomanSo on 03.08.17.
 */

public class ConverterPage extends PageObject
{
    private ConverterWindow converterWindow;

    public ConverterPage(WebDriver driver)
    {
        super(driver);
        if (!"«Сбербанк» - Калькулятор иностранных валют".equals(driver.getTitle()))
            throw new IllegalStateException("This is not converter page!");

        converterWindow = new ConverterWindow(this.driver);
    }

    public ConverterWindow converterWindow() {
        return converterWindow;
    }
}
