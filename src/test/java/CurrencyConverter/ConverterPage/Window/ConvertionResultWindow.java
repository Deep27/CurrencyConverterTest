package CurrencyConverter.ConverterPage.Window;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by RomanSo on 03.08.17.
 */

public class ConvertionResultWindow extends PageObject
{
    private By resultFromLocator = By.className("rates-converter-result__total-from");
    private By resultToLocator = By.className("rates-converter-result__total-to");

    public ConvertionResultWindow(WebDriver driver) {
        super(driver);
    }

    public String getFrom() {
        return getFirstNumber(getVisibleElement(resultFromLocator).getText());
    }

    public String getTo() {
        return getFirstNumber(getVisibleElement(resultToLocator).getText());
    }

    private String getFirstNumber(String text) {
        int commaIndex = text.indexOf(',');
        return text.substring(0, commaIndex + 3);
    }
}
