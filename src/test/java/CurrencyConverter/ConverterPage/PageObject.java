package CurrencyConverter.ConverterPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by RomanSo on 03.08.17.
 */

public abstract class PageObject
{
    protected WebDriver driver;
    protected PageObject(WebDriver driver) { this.driver = driver; }

    protected WebElement getPresentElement(By locator) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement getVisibleElement(By locator) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForPageToLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
}
