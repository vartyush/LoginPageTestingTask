package ru.protei;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.protei.pages.ApplicationFormPage;
import ru.protei.pages.LoginPage;

import java.time.Duration;
import java.util.List;

public class TestBase {
    public WebDriver driver;
    public LoginPage loginPage;
    public ApplicationFormPage applicationFormPage;

    public WebDriverWait wait;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().clearDriverCache().setup();    }

    @BeforeEach
    public void goToURL() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        applicationFormPage = new ApplicationFormPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        String htmlFilePath = "file:///" + System.getProperty("user.dir") + "/src/test/resources/qa-test.html";
        driver.get(htmlFilePath);
    }


    @AfterEach
    public void tearDown() {
        driver.quit(); // Закрытие браузера
    }

    @Step("Ввести значение {value} в поле")
    public void setValue(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    @Step("Нажать на элемент")
    public void click(WebElement element) {
        element.click();
    }

    public void assertIsDisplayed(WebElement element) {
        Assertions.assertTrue(element.isDisplayed());
    }

    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Выбрать значение из раскрывающегося списка: {value} ")
    public void selectFromDropDown(WebElement element, String value) {
        element.click();
        driver.findElement(By.xpath("//select/option[contains(text(),\"" + value + "\")]"))
                .click();
    }

    @Step("Выбрать флажок: {value}")
    public void selectCheckbox(String value) {
        driver.findElement(By.xpath("//input[@type='checkbox' and following-sibling::text()[contains(.,\"" + value + "\")]]"))
                .click();
    }

    @Step("Выбрать кнопку: {value}")
    public void selectRadioButton(String value) {
        driver.findElement(By.xpath("//input[@type='radio' and following-sibling::text()[contains(.,\"" + value + "\")]]"))
                .click();
    }

    @Step("Успешная авторизация на форму")
    public void loginSuccsessToTheForm() {
        setValue(loginPage.emailField, "test@protei.ru");
        setValue(loginPage.passwordField, "test");
        click(loginPage.loginButton);
        // Ожидание отображения сообщения об ошибке
        waitUntilVisible(applicationFormPage.authPage);
    }

    @Step("Проверка записи в таблицу в колонке: {column}")
    public void checkTableRecord(String column, String expected) {
        String index = "";
        String columnName;
        int count = 1;

        List<WebElement> columns = driver.findElements(By.xpath("//table[@id='dataTable']/thead/tr/th"));

        for (int i = 0; i < columns.size(); i++) {
            columnName = columns.get(i).getText();
            if (columnName.contains(column)) {
                index = Integer.toString(i + 1);
                break;
            }
        }

        driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr[" + count + "]/td[" + index + "][text()=\"" + expected + "\"]"));
    }
}


