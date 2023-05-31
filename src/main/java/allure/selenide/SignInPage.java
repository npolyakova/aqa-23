package allure.selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static io.qameta.allure.Allure.step;

public class SignInPage extends BasePage {

    SelenideElement inputUsername = $("#user-name");

    SelenideElement inputPassword = $("#password");

    public SelenideElement buttonLogin = $("#login-button");

    public void enterUsername(String username) {
        inputUsername.sendKeys(username);
    }

    public void enterPassword(String pass) {
        inputPassword.sendKeys(pass);
    }

    public ProductsPage login() {
        enterUsername("standard_user");
        enterPassword("secret_sauce");
        buttonLogin.click();

        ProductsPage pp = page(ProductsPage.class);

        step("Проверить, что авторизация прошла успешно", () -> {
            pp.headerPage.shouldHave(text("Products"));
        });

        return pp;
    }
}
