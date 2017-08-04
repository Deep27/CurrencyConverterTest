package CurrencyConverter.ConverterPage.Window;

import CurrencyConverter.ConverterPage.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RomanSo on 03.08.17.
 */

public class QuotesWindow extends PageObject
{
    @FindBy(xpath = "//table[@class='rates-current__table']/tbody/tr")
    private List<WebElement> ratesTableRows;
    private By ratesTableCellLocator = By.xpath(".//td");
    private List<List<WebElement>> ratesTableCells;

    public QuotesWindow(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
        initRatesTable();
    }

    public String getFromBuyRate() {
        return getCellValue(1, 1);
    }

    public String getFromSellRate() {
        return getCellValue(1, 2);
    }

    public String getToBuyRate() {
        return getCellValue(2, 1);
    }

    public String getToSellRate() {
        return getCellValue(2, 2);
    }

    public String getFromCoefficient()
    {
        String returnValue = "1,0";
        if (ratesTableCells.get(0).size() == 4)
            returnValue = getCoefficient(1, 1);
        return returnValue;
    }

    public String getToCoefficient()
    {
        String returnValue = "1,0";
        if (ratesTableCells.get(0).size() == 4)
            returnValue = getCoefficient(2, 1);
        return returnValue;
    }

    private void initRatesTable()
    {
        ratesTableCells = new ArrayList<>(ratesTableRows.size());

        for (WebElement tableRow : ratesTableRows) {
            ratesTableCells.add(tableRow.findElements(ratesTableCellLocator));
        }
    }

    private String getCoefficient(int row, int column) {
        return ratesTableCells.get(row).get(column).getText();
    }

    private String getCellValue(int row, int column)
    {
        String rate;
        // without coefficient
        if (ratesTableCells.get(0).size() == 3)
            rate = ratesTableCells.get(row).get(column).getText();

        // with coefficient
        else
            rate = ratesTableCells.get(row).get(column + 1).getText();

        return rate.substring(0, rate.indexOf(',') + 3);
    }
}
