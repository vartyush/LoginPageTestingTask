import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.protei.pages.LoginPage;

public class TestBase {
    public WebDriver driver;
    public LoginPage loginPage;
    public WebDriverWait wait;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();    }

    @Before
    public void goToURL() {
      //  ChromeOptions options = new ChromeOptions();
      //   options.addArguments("--remote-debugging-port=9222");
     //   System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-application-cache");
        driver = new ChromeDriver(options);


        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        String htmlFilePath = "file:///" + System.getProperty("user.dir") + "/src/main/resources/qa-test.html";
        driver.get(htmlFilePath);
    }



    @After
    public void tearDown() {
          driver.quit(); // Закрытие браузера
    }

   // @Step("Set value {value} in {element}")
    public void setValue(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
    }
    public void click(WebElement element) {
        element.click();
    }

    public void assertIsDisplayed(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
