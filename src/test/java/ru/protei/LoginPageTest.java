package ru.protei;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LoginPageTest extends TestBase {
    @Test
    @Description("Успешная авторизация.")
    public void loginSuccsessToTheForm()  {
        setValue(loginPage.emailField, "test@protei.ru");
        setValue(loginPage.passwordField, "test");
        click(loginPage.loginButton);
        // Ожидание отображения сообщения об ошибке
        assertIsDisplayed(applicationFormPage.authPage);
    }

    @Test
    @Description("Неуспешная авторизация. Неверный E-Mail и пароль.")
    public void loginWithIncorrectCredentialsTest() {
        setValue(loginPage.emailField, "testWrong@example.com");
        setValue(loginPage.passwordField, "testWrongPassword");
        click(loginPage.loginButton);
        // Ожидание отображения сообщения об ошибке
        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assertions.assertEquals("Неверный E-Mail или пароль", loginPage.errorMessage.getText());
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
        Assertions.assertEquals("Неверный формат E-Mail", loginPage.errorMessage.getText());
    }

    @Test
    @Description("Неуспешная авторизация. Оба поля пусты.")
    public void loginWithEmptyFieldsTest() {
        setValue(loginPage.emailField, "");
        setValue(loginPage.passwordField, "");
        click(loginPage.loginButton);

        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assertions.assertEquals("Неверный формат E-Mail", loginPage.errorMessage.getText());
    }

    @Test
    @Description("Неуспешная авторизация. Поле E-mail пустое.")
    public void loginWithEmptyEmailFieldTest() {
        setValue(loginPage.emailField, "");
        setValue(loginPage.passwordField, "validPassword");
        click(loginPage.loginButton);

        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assertions.assertEquals("Неверный формат E-Mail", loginPage.errorMessage.getText());
    }

    @Test
    @Description("Неуспешная авторизация. Поле Пароль пустое.")
    public void loginWithEmptyPasswordFieldTest() {
        setValue(loginPage.emailField, "valid@example.com");
        setValue(loginPage.passwordField, "");
        click(loginPage.loginButton);

        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assertions.assertEquals("Неверный E-Mail или пароль", loginPage.errorMessage.getText());
    }

}
