package CurrencyConverter.ConverterPage.Window.ConverterWindow;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by RomanSo on 04.08.17.
 */

public class CurrencyListDropdown extends PageObject
{
    private HashMap<String, Integer> dropdownCurrenciesIndices = new HashMap<>();
    public static final String RUB = "RUB";
    public static final String CHF = "CHF";
    public static final String EUR = "EUR";
    public static final String GBP = "GBP";
    public static final String JPY = "JPY";
    public static final String USD = "USD";

    private WebElement currenciesSelect;
    private By optionLocator = By.tagName("span");

    public CurrencyListDropdown(WebDriver driver, WebElement currenciesSelect) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.currenciesSelect = currenciesSelect;
        dropdownCurrenciesIndices.put(RUB, 0);
        dropdownCurrenciesIndices.put(CHF, 1);
        dropdownCurrenciesIndices.put(EUR, 2);
        dropdownCurrenciesIndices.put(GBP, 3);
        dropdownCurrenciesIndices.put(JPY, 4);
        dropdownCurrenciesIndices.put(USD, 5);
    }

    public void clickOption(String currency) {
        List<WebElement> options = currenciesSelect.findElements(optionLocator);
        options.get(dropdownCurrenciesIndices.get(currency)).click();
    }
}
