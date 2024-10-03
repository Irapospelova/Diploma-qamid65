package ru.iteco.fmhandroid.test;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LogIn {

        private AppiumDriver driver;

        @Before
        public void setUp() throws MalformedURLException {
            // Установка параметров для драйвера Appium
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", "emulator-5554"); // Имя устройства
            caps.setCapability("appPackage", "ru.iteco.fmhandroid");
            caps.setCapability("appActivity", "ru.iteco.fmhandroid.ui.AppActivity"); //

            // драйвер
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);


        }

        @Test
        public void loginTest() {
            // Проверка, что открыта страница Авторизации
            WebElement authorizationTitle = driver.findElement(By.xpath("//android.widget.TextView[@text='Authorization']"));
            assertTrue(authorizationTitle.isDisplayed());

            // Поиск поля логина
            WebElement loginField = driver.findElement(By.id("login_text_input_layout"));
            loginField.sendKeys("login2");

            // Поиск поля пароля
            WebElement passwordField = driver.findElement(By.id("password_text_input_layout"));
            passwordField.sendKeys("password2");

            // Поиск кнопки входа
            WebElement loginButton = driver.findElement(By.id("enter_button"));
            loginButton.click();

            // Проверка успешного входа
            WebElement dashboardElement = driver.findElement(By.id("main_menu_image_button"));
            assertTrue(dashboardElement.isDisplayed());
        }

        @After
        public void tearDown() {
            if (driver != null) {
                driver.quit(); // Закрытие сессии драйвера после завершения теста
            }
        }
    }


