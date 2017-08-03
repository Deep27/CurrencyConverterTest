package CurrencyConverter.ConverterPage.Window;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by RomanSo on 03.08.17.
 */

public class ConverterWindow extends PageObject
{
    private By btnConvertLocator = By.className("rates-button");
    private By inputFieldLocator = By.xpath("//input[@placeholder='Сумма']");

    public ConverterWindow(WebDriver driver) {
        super(driver);
    }

    @Step("Input '{0}' into input field")
    public void sendKeysToInputField(String value) {
        getVisibleElement(inputFieldLocator).sendKeys(value);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Clear input field")
    public void clearInputField() {
        getVisibleElement(inputFieldLocator).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
//        getVisibleElement(inputFieldLocator).clear();
    }

    @Step("Click 'Convert' button")
    public ConvertionResultWindow convert()
    {
        getVisibleElement(btnConvertLocator).click();
        return new ConvertionResultWindow(driver);
    }

    @Step("See the value in the input field")
    public String getInputFieldValue() {
        return getVisibleElement(inputFieldLocator).getAttribute("value");
    }
}




