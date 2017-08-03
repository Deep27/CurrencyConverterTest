package CurrencyConverter.ConverterPage.Window;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by RomanSo on 03.08.17.
 */

public class RatesConverterResultWindow extends PageObject
{
    @FindBy(className = "rates-converter-result__total-from")
    WebElement resultFrom;

    @FindBy(className = "rates-converter-result__total-to")
    WebElement resultTo;

    public RatesConverterResultWindow(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
}
