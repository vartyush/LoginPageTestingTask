package ru.protei.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

public class LoginPage  {
    private WebDriver driver;
    // Конструктор
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Инициализация элементов
    }
    // Элементы страницы
    @FindBy(id = "loginEmail")
    public WebElement emailField;

    @FindBy(id = "loginPassword")
    public WebElement passwordField;

    @FindBy(id = "authButton")
    public WebElement loginButton;

    @FindBy(id = "authAlertsHolder")
    public WebElement errorMessage;

    // Методы для взаимодействия с элементами
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }




}

