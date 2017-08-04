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
        converterPage.converterWindow().clearInputFieldWithBackspace();
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
        converterPage.converterWindow().sendKeysToInputField(input);
        String resultInputFieldValue = converterPage.converterWindow().getInputFieldValue();
        assertEquals(correctInput, resultInputFieldValue);
    }

    @Test
    @Title("Convertion correctness")
    @Description("Convertion of some amount of money from one currency to another should be correct")
    @FileParameters(Config.PATH_PARAMETER_RESOURCES + "Converter/currency_combinations.csv")
    public void convertionIsCorrect(String from, String to)
    {
        double valueToConvert = 100.0;
        double buyRate = 1.0;
        double sellRate = 1.0;

        converterPage.converterWindow().sendKeysToInputField(String.valueOf(valueToConvert));
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
                LocalConverter.convert(
                        Double.valueOf(valueToConvert), buyRate, sellRate, fromCoefficient, toCoefficient),
                LocalConverter.getDouble(convertionResultWindow.getToValue()),
                0.0
        );
    }

    @Test
    @Title("Minimum value for convertion")
    @Description("The minimum value that can be converted should be one")
    public void testMinimumValueThatCanBeConvertedIsOne()
    {
        double firstResult = getResultAfterConvertion("500,0");
        converterPage.converterWindow().clearInputFieldWithBackspace();
        double secondResult = getResultAfterConvertion("0,99");
        converterPage.converterWindow().clearInputFieldWithBackspace();
        double thirdResult = getResultAfterConvertion("1,0");

        assertEquals(firstResult, secondResult, 0.0);
        assertNotEquals(secondResult, thirdResult);
    }

    @Test
    @Title("Empty input")
    @Description("Convertion should not be executed if the input field is empty")
    public void testEmptyInputValueDoesntConvertAnything()
    {
        double firstResult = getResultAfterConvertion("1000.00");
        converterPage.converterWindow().clearInputFieldWithBackspace();
        double secondResult = getResultAfterConvertion("");

        assertEquals(firstResult, secondResult, 0.0);
    }

    @Test
    @Title("The ways of executing convertion")
    @Description("Convertion result should be the same after clicking" +
            " \"Convert\" button and pressing \"Enter\" key")
    public void testEnterKeyAndShowButtonConvertTheSame()
    {
        double firstResult = getResultAfterConvertion("1000.00");

        converterPage.converterWindow().clearInputFieldWithBackspace();
        converterPage.converterWindow().sendKeysToInputField("1050.00");
        double secondResult
                = LocalConverter.getDouble(converterPage.converterWindow().convertByButtonClick().getToValue());

        assertNotEquals(firstResult, secondResult);
    }

    @Test
    @Title("Input dot without fractional part")
    @Description("Test that number which was set in input value with dot but without decimal part" +
            " is correctly converted")
    public void userInputWholePartWithDotWithoutFractionalPart()
    {
        double firstResult = getResultAfterConvertion("10.00");
        converterPage.converterWindow().clearInputFieldWithBackspace();
        double secondResult = getResultAfterConvertion("1050.");

        assertNotEquals(firstResult, secondResult);
    }

    @Test
    @Title("Input comma without fractional part")
    @Description("Test that number which was set in input value with comma but without decimal part" +
            " is correctly converted")
    public void userInputWholePartWithCommaWithoutFractionalPart()
    {
        double firstResult = getResultAfterConvertion("10.00");
        converterPage.converterWindow().clearInputFieldWithBackspace();
        double secondResult = getResultAfterConvertion("1034,");

        assertNotEquals(firstResult, secondResult);
    }

    private double getResultAfterConvertion(String valueToConvert)
    {
        converterPage.converterWindow().sendKeysToInputField(valueToConvert);
        return LocalConverter.getDouble(converterPage.converterWindow().convertByPressingEnterKey().getToValue());
    }
}
