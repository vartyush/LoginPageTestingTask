package ru.protei.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {

    public BasePage(WebDriver driver) {
    }
    public final WebDriver driver = new ChromeDriver();

}
