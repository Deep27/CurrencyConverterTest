package CurrencyConverter.ConverterPage.Window.ConverterWindow;

import CurrencyConverter.ConverterPage.PageObject;
import CurrencyConverter.ConverterPage.Window.ConvertionResultWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

/**
 * Created by RomanSo on 03.08.17.
 */

public class ConverterWindow extends PageObject
{
    @FindBy(className = "rates-button")
    private WebElement btnConvert;

    @FindBy(xpath = "//input[@placeholder='Сумма']")
    private WebElement inputField;

    @FindBy(className = "select")
    private List<WebElement> selects;

    private By dropdownOptionLocator = By.tagName("span");

    public ConverterWindow(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Input {0} into input field")
    public void sendKeysToInputField(String value) {
        inputField.sendKeys(value);
    }

    @Step("Clear input field by selecting and removing text with backspace")
    public void clearInputFieldWithBackspace() {
        inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    }

    public void clearInputField() {
        inputField.clear();
    }

    @Step("Click 'Convert' button")
    public ConvertionResultWindow convertByButtonClick()
    {
        btnConvert.click();
        return new ConvertionResultWindow(driver);
    }

    @Step("Press 'Enter' button")
    public ConvertionResultWindow convertByPressingEnterKey()
    {
        inputField.sendKeys(Keys.ENTER);
        return new ConvertionResultWindow(driver);
    }

    @Step("See the value in the input field")
    public String getInputFieldValue() {
        return inputField.getAttribute("value");
    }

    @Step("Select currency from: {0}")
    public void selectCurrencyFrom(String currency) {
        clickCurrencyOption(selects.get(0), currency);
    }

    @Step("Select currency to: {0}")
    public void selectCurrencyTo(String currency) {
        clickCurrencyOption(selects.get(1), currency);
    }

    private void clickCurrencyOption(WebElement currenciesSelect, String currency)
    {
        currenciesSelect.click();
        new CurrencyListDropdown(driver, currenciesSelect).clickOption(currency);
    }
}




