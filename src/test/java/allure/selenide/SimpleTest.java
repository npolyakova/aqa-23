package allure.selenide;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.FileInputStream;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class SimpleTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        Configuration.baseUrl = "https://www.saucedemo.com";
        open("/");
        signInPage = new SignInPage();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Cart")
    @Feature("Order")
    @DisplayName("Remove from cart")
    public void shouldTest() {
        step("Авторизоваться", () -> {
            attachment("some_text", "123");

            File file = screenshot(OutputType.FILE);
            assert file != null;
            addAttachment("Screenshot", new FileInputStream(file));

            productsPage = signInPage.login();
        });

        step("Добавить первый товар из списка в корзину", () -> {
            productsPage.buttonAddToCart.click();

            step("Кнопка изменилась на 'Remove'", () -> {
                productsPage.buttonRemoveFromCart.shouldBe(visible);
            });

            step("У корзины в шапке появилась иконка 1", () -> {
                productsPage.buttonRemoveFromCart.shouldBe(visible);
            });
        });

        step("Перейти на страницу корзины", () -> {
            cartPage = productsPage.openCart();

            step("В корзине есть товар", () -> {
                cartPage.blockCartItem.shouldBe(visible);
            });
        });

        step("Удалить товар из корзины", () -> {
            cartPage.buttonRemove.click();

            step("В корзине нет товаров", () -> {
                cartPage.blockRemovedCartItem.should(exist);
            });
        });
    }
}
