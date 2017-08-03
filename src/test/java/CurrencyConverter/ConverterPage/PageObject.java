package CurrencyConverter.ConverterPage;

import org.openqa.selenium.WebDriver;

/**
 * Created by RomanSo on 03.08.17.
 */

public abstract class PageObject
{
    protected WebDriver driver;
    protected PageObject(WebDriver driver) { this.driver = driver; }
}
