package CurrencyConverter;

import CurrencyConverter.Config.Config;
import CurrencyConverter.ConverterPage.ConverterPage;
import CurrencyConverter.ConverterPage.Window.ConvertionResultWindow;
import org.junit.*;
import org.junit.rules.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by RomanSo on 03.08.17.
 */

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
    public void checkConvertionOfFiftyRoublesToDollarsIsCorrect()
    {
        converterPage.converterWindow().clearInputValue();
        converterPage.converterWindow().sendInputValue("50");
        ConvertionResultWindow result = converterPage.convert();
        assertEquals("50,00", result.getFrom());
        assertEquals("0,81", result.getTo());
    }
}
