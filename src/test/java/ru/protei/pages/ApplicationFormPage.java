package ru.protei.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.protei.TestBase;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class ApplicationFormPage extends TestBase {
    private WebDriver driver;
    // Конструктор
    public ApplicationFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Элементы страницы
    @FindBy(xpath ="//input[@id = 'dataEmail']")

    public WebElement emailField;

    @FindBy(xpath ="//input[@id = 'dataName']")
    public WebElement nameField;

    @FindBy(id="dataGender")
    public WebElement dropDownGenderMenu;
    @FindBy(xpath = "//div[@id='inputsPage']")
    public WebElement authPage;

    @FindBy(id = "dataAlertsHolder")
    public WebElement errorMessage;
    @FindBy(id="dataSend")
    public WebElement dataSendButton;
    @FindBy(xpath ="//button[text() = 'Ok']")
    public WebElement popUpButton;
    @FindBy(xpath ="//button[text() = 'Ok']/parent::div/preceding-sibling::div")
    public WebElement popUpMessage;
    @FindBy(id="dataTable")
    public WebElement dataTable;


}

