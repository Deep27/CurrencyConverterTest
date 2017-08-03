package CurrencyConverter;

import CurrencyConverter.Config.Config;
import CurrencyConverter.ConverterPage.ConverterPage;
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
    public void DEBUGPrintRatesAndCurrenciesDEBUG()
    {
        System.out.println(converterPage.quotesWindow().getCurrencyFrom());
        System.out.println(converterPage.quotesWindow().getCurrencyTo());
        System.out.println(converterPage.quotesWindow().getBuyRate());
        System.out.println(converterPage.quotesWindow().getSellRate());
    }
}
