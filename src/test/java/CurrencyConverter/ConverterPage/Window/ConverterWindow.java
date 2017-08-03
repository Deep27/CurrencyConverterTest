package CurrencyConverter.ConverterPage.Window;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by RomanSo on 03.08.17.
 */

public class ConverterWindow extends PageObject
{
    private By btnConvertLocator = By.className("rates-button");
    private By inputValue = By.xpath("//input[@placeholder='Сумма']");

    public ConverterWindow(WebDriver driver) {
        super(driver);
    }

    public void sendInputValue(String value) {
        driver.findElement(inputValue).sendKeys(value);
//        getVisibleElement(inputValue).sendKeys(value);
    }

    public ConvertionResultWindow convert()
    {
//        getVisibleElement(btnConvertLocator).click();
        driver.findElement(btnConvertLocator).click();
        return new ConvertionResultWindow(driver);
    }

    public void clearInputValue() {
        getVisibleElement(inputValue).clear();
    }
}




