package CurrencyConverter.ConverterPage.Window;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by RomanSo on 03.08.17.
 */

public class ConverterWindow extends PageObject
{
    By convertButtonLocator = By.className("rates-button");

    public ConverterWindow(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public RatesConverterResultWindow convert()
    {
        return null;
    }
}
