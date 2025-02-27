package ru.protei;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.protei.pages.LoginPage;

import java.time.Duration;

public class TestBase {
    public WebDriver driver;
    public LoginPage loginPage;
    public WebDriverWait wait;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void goToURL() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        String htmlFilePath = "file:///" + System.getProperty("user.dir") + "/src/test/resources/qa-test.html";
        driver.get(htmlFilePath);
    }


    @After
    public void tearDown() {
        driver.quit(); // Закрытие браузера
    }

    @Step("Ввести значение {value} в поле {element}")
    public void setValue(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    @Step("Нажать на {element}")
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
