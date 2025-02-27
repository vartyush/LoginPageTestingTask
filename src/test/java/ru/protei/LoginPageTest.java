package ru.protei;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;

public class LoginPageTest extends TestBase {

    @Test
    @Description("Успешная авторизация")
    public void loginWithIncorrectCredentialsTest() {
        setValue(loginPage.emailField, "testWrong@example.com");
        setValue(loginPage.passwordField, "testWrongPassword");
        click(loginPage.loginButton);
        // Ожидание отображения сообщения об ошибке
        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assert.assertEquals("Неверный E-Mail или пароль", loginPage.errorMessage.getText());
    }

    @Test
    @Description("Неуспешная авторизация. Неверный формат e-mail.")
    public void loginWithWrongFormatEmailTest() {
        setValue(loginPage.emailField, "validexample.com");
        setValue(loginPage.passwordField, "validPassword");
        click(loginPage.loginButton);

        // Ожидание отображения сообщения об ошибке
        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assert.assertEquals("Неверный формат E-Mail", loginPage.errorMessage.getText());
    }

    @Test
    @Description("Неуспешная авторизация. Оба поля пусты.")
    public void loginWithEmptyFieldsTest() {
        setValue(loginPage.emailField, "");
        setValue(loginPage.passwordField, "");
        click(loginPage.loginButton);

        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assert.assertEquals("Неверный формат E-Mail", loginPage.errorMessage.getText());
    }

    @Test
    @Description("Неуспешная авторизация. Поле  e-mail пустое.")
    public void loginWithEmptyEmailFieldTest() {
        setValue(loginPage.emailField, "");
        setValue(loginPage.passwordField, "validPassword");
        click(loginPage.loginButton);

        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assert.assertEquals("Неверный формат E-Mail", loginPage.errorMessage.getText());
    }

    @Test
    @Description("Неуспешная авторизация. Поле  e-mail пустое.")
    public void loginWithEmptyPasswordFieldTest() {
        setValue(loginPage.emailField, "valid@example.com");
        setValue(loginPage.passwordField, "");
        click(loginPage.loginButton);

        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assert.assertEquals("Неверный E-Mail или пароль", loginPage.errorMessage.getText());
    }

}
