package allure.selenide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {

    private WebDriver driver;

    By inputUsername = new By.ById("user-name");

    By inputPassword = new By.ById("password");

    public By buttonLogin = new By.ById("login-button");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(inputUsername).sendKeys(username);
    }

    public void enterPassword(String pass) {
        driver.findElement(inputPassword).sendKeys(pass);
    }
}
