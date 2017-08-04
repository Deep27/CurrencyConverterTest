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

public class QuotesWindow extends PageObject
{
    @FindBy(xpath = "//table[@class='rates-current__table']/tbody/tr[2]/td[1]")
    private WebElement currencies;
    @FindBy(xpath = "//table[@class='rates-current__table']/tbody/tr[2]/td[2]")
    private WebElement buyRate;
    @FindBy(xpath = "//table[@class='rates-current__table']/tbody/tr[2]/td[3]")
    private WebElement sellRate;

    public QuotesWindow(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCurrencyFrom() {
        return currencies.getText().split(" ")[0];
    }

    public String getCurrencyTo() {
        return currencies.getText().split(" ")[2];
    }

    public String getBuyRate() {
        String rate = buyRate.getText();
        return rate.substring(0, rate.indexOf(',') + 3);
    }

    public String getSellRate() {
        String rate = sellRate.getText();
        return rate.substring(0, rate.indexOf(',') + 3);
    }
}
