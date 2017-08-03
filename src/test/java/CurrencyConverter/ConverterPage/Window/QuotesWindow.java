package CurrencyConverter.ConverterPage.Window;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by RomanSo on 03.08.17.
 */

public class QuotesWindow extends PageObject
{
    public QuotesWindow(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
}
