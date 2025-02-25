import org.junit.Assert;
import org.junit.Test;

public class LoginPageTest extends TestBase {

    @Test
    public void testLoginWithValidCredentials() {
        setValue(loginPage.emailField,"valid@example.com");
        setValue(loginPage.passwordField, "validPassword");
        click(loginPage.loginButton);

    }

    @Test
    public void testLoginWithInvalidEmail() {
        setValue(loginPage.emailField,"validexample.com");
        setValue(loginPage.passwordField, "validPassword");
        click(loginPage.loginButton);

        // Ожидание отображения сообщения об ошибке
        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assert.assertEquals("Неверный адрес электронной почты", loginPage.errorMessage.getText());
    }

    @Test
    public void testLoginWithEmptyFields() {
        setValue(loginPage.emailField,"");
        setValue(loginPage.passwordField, "");
        click(loginPage.loginButton);

        waitUntilVisible(loginPage.errorMessage);
        // Проверка сообщения об ошибке
        Assert.assertEquals("Пожалуйста, введите адрес электронной почты и пароль", loginPage.errorMessage.getText());
    }

}
