package CurrencyConverter.ConverterPage.Window;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by RomanSo on 03.08.17.
 */

public class ConvertionResultWindow extends PageObject
{
    private By resultFromLocator = By.className("rates-converter-result__total-from");
    private By resultToLocator = By.className("rates-converter-result__total-to");

    @FindBy(className = "rates-converter-result__total-from")
    private WebElement resultFrom;

    @FindBy(className = "rates-converter-result__total-to")
    private WebElement resultTo;

    public ConvertionResultWindow(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getFromValue() {
        return getNumber(getVisibleElement(resultFromLocator).getText());
//        return getNumber(resultFrom.getText());
    }

    public String getToValue() {
        return getNumber(getVisibleElement(resultToLocator).getText());
//        return getNumber(resultTo.getText());
    }

    private String getNumber(String text) {
        int commaIndex = text.indexOf(',');
        return text.substring(0, commaIndex + 3);
    }
}
