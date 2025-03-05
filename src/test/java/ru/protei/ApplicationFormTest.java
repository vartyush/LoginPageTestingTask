package ru.protei;

import jdk.jfr.Description;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationFormTest extends TestBase {

    @ParameterizedTest
    @CsvSource({
            "email1@test.ru, Вика, Женский, 1.1, 2.1",
            "email2@test.ru, Женя, Мужской, 1.2, 2.1",
            "testexample.com, Люба, Женский, '', 2.1",
            "'', Люба, Женский, 1.2, 2.1",
            "email1@test.ru, '', Женский, 1.1, 2.1",

    })
    @Description("Заполнение формы. Негативные и положительные сценарии.")
    public void dataSendSuccessfulTest(String email, String name, String gender, String checkbox, String radioButton) {

        loginSuccsessToTheForm();
        setValue(applicationFormPage.emailField, email);
        setValue(applicationFormPage.nameField, name);
        selectFromDropDown(applicationFormPage.dropDownGenderMenu, gender);
        selectCheckbox(checkbox);
        selectRadioButton(radioButton);
        click(applicationFormPage.dataSendButton);
        if (email.equals("testexample.com") || email.isEmpty()) {
            waitUntilVisible(applicationFormPage.errorMessage);

            Assertions.assertEquals("Неверный формат E-Mail", applicationFormPage.errorMessage.getText());
        } else if (name.isEmpty()) {
            waitUntilVisible(applicationFormPage.errorMessage);

            Assertions.assertEquals("Поле имя не может быть пустым", applicationFormPage.errorMessage.getText());
        } else {
            waitUntilVisible(applicationFormPage.popUpMessage);
            Assertions.assertEquals("Данные добавлены.", applicationFormPage.popUpMessage.getText());
            assertIsDisplayed(applicationFormPage.popUpButton);
            checkTableRecord("E-Mail", email);
            checkTableRecord("Имя", name);
            checkTableRecord("Пол", gender);
            checkTableRecord("Выбор 1", checkbox);
            checkTableRecord("Выбор 2", radioButton);
        }
    }

}
