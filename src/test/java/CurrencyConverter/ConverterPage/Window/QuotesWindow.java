package CurrencyConverter.ConverterPage.Window;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by RomanSo on 03.08.17.
 */

public class QuotesWindow extends PageObject
{
    private final String currentRatesTableXpath = "//table[@class='rates-current__table']/tbody/";

    private By currenciesLocator = By.xpath(currentRatesTableXpath + "tr[2]/td[1]");
    private By buyRate = By.xpath(currentRatesTableXpath + "tr[2]/td[2]");
    private By sellRate = By.xpath(currentRatesTableXpath + "tr[2]/td[3]");

    public QuotesWindow(WebDriver driver) {
        super(driver);
    }

    public String getCurrencyFrom() {
        return getVisibleElement(currenciesLocator).getText().split(" ")[0];
    }

    public String getCurrencyTo() {
        return getVisibleElement(currenciesLocator).getText().split(" ")[2];
    }

    public String getBuyRate() {
        String rate = getVisibleElement(buyRate).getText();
        return rate.substring(0, rate.indexOf(',') + 3);
    }

    public String getSellRate() {
        String rate = getVisibleElement(sellRate).getText();
        return rate.substring(0, rate.indexOf(',') + 3);
    }
}
