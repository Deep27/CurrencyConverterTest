package CurrencyConverter;

import CurrencyConverter.Config.Config;
import CurrencyConverter.ConverterPage.ConverterPage;
import CurrencyConverter.ConverterPage.Window.ConverterWindow.Currency;
import CurrencyConverter.ConverterPage.Window.ConvertionResultWindow;
import CurrencyConverter.ConverterPage.Window.QuotesWindow;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by RomanSo on 03.08.17.
 */

@Title("Sberbank's currency converter widget test")
@Description("Test the correctness of currency convertion, input field accepting correct values.")
@RunWith(JUnitParamsRunner.class)
public class CurrencyConverterTest
{
    @Rule
    public Timeout globalTimeout = Timeout.seconds(30);

    private static WebDriver driver;
    private ConverterPage converterPage;

    @BeforeClass
    public static void oneTimeSetUp()
    {
        System.setProperty("webdriver.gecko.driver", Config.PATH_GECKODRIVER);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        driver.get(Config.PAGE_URL);
    }

    @AfterClass
    public static void oneTimeTearDown() {
        driver.quit();
    }

    @Before
    public void setUp() {
        converterPage = new ConverterPage(driver);
    }

    @After
    public void tearDown() {
        converterPage = null;
    }

    @Test
    @Title("Different inputs in 'Sum' input field")
    @Description("Input field should accept only twelve digits[0-9] in whole part," +
            " one comma[,] or dot[.] and two digits in decimal part")
    @FileParameters(Config.PATH_PARAMETER_RESOURCES + "Converter/input_values.csv")
    public void testInputFieldForDifferentValues(String input, String correctInput)
    {
        converterPage.converterWindow().clearInputFieldWithBackspace();
        converterPage.converterWindow().sendKeysToInputField(input);
        String resultInputFieldValue = converterPage.converterWindow().getInputFieldValue();
        assertEquals(correctInput, resultInputFieldValue);
    }

    @Test
    @Title("Convertion correctness")
    @Description("Convertion of some amount of money from one currency to another should be correct")
    @FileParameters(Config.PATH_PARAMETER_RESOURCES + "Converter/currency_combinations.csv")
    public void convertionIsCorrect(String from, String to, String value)
    {
        double buyRate = 1.0;
        double sellRate = 1.0;

        converterPage.converterWindow().clearInputFieldWithBackspace();
        converterPage.converterWindow().sendKeysToInputField(value);
        converterPage.converterWindow().selectCurrencyFrom(from);
        QuotesWindow quotesWindow = converterPage.converterWindow().selectCurrencyTo(to);
        ConvertionResultWindow convertionResultWindow = converterPage.converterWindow().convertByButtonClick();

        double fromCoefficient = LocalConverter.getDouble(quotesWindow.getFromCoefficient());
        double toCoefficient = LocalConverter.getDouble(quotesWindow.getToCoefficient());

        if (from.equals(Currency.RUB))
            sellRate = LocalConverter.getDouble(quotesWindow.getFromSellRate());

        else if (to.equals(Currency.RUB))
            buyRate = LocalConverter.getDouble(quotesWindow.getFromBuyRate());

        else {
            buyRate = LocalConverter.getDouble(quotesWindow.getFromBuyRate());
            sellRate = LocalConverter.getDouble(quotesWindow.getToSellRate());
        }

        assertEquals(
                LocalConverter.convert(Double.valueOf(value), buyRate, sellRate, fromCoefficient, toCoefficient),
                LocalConverter.getDouble(convertionResultWindow.getToValue()),
                0.0
        );
    }

    @Test
    public void DEBUGPrintRatesAndCurrenciesDEBUG()
    {
        converterPage.converterWindow().selectCurrencyFrom(Currency.RUB);
        QuotesWindow quotesWindow = converterPage.converterWindow().selectCurrencyTo(Currency.CHF);
        System.out.println(quotesWindow.getFromBuyRate());
        System.out.println(quotesWindow.getFromSellRate());
    }

    @Test
    @FileParameters(Config.PATH_PARAMETER_RESOURCES + "Converter/currency_combinations.csv")
    public void DEBUGSelectCurrencies(String currencyFrom, String currencyTo, String value)
    {
        converterPage.converterWindow().selectCurrencyFrom(currencyFrom);
        converterPage.converterWindow().selectCurrencyTo(currencyTo);
    }
}
