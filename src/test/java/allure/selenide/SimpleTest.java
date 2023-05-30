package allure.selenide;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        baseUrl = "https://www.saucedemo.com";
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(8000));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Cart")
    @Feature("Order")
    @DisplayName("Remove from cart")
    public void shouldTest() {
        ProductsPage productsPage = new ProductsPage(driver);

        step("Авторизоваться", () -> {
            driver.get(baseUrl);

            SignInPage signInPage = new SignInPage(driver);

            attachment("some_text", "123");

            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            addAttachment("Screenshot", new FileInputStream(file));

            signInPage.enterUsername("standard_user");
            signInPage.enterPassword("secret_sauce");
            driver.findElement(signInPage.buttonLogin).click();

            step("Проверить, что авторизация прошла успешно", () -> {
                assertThat(driver.findElement(productsPage.headerPage).getText()).isEqualTo("Products");
            });
        });

        step("Добавить первый товар из списка в корзину", () -> {
            driver.findElement(productsPage.divProductItem).findElement(productsPage.buttonAddToCart).click();

            step("Кнопка изменилась на 'Remove'", () -> {
                assertThat(driver.findElement(productsPage.buttonRemoveFromCart).isDisplayed()).isTrue();
            });

            step("У корзины в шапке появилась иконка 1", () -> {
                assertThat(driver.findElement(productsPage.buttonRemoveFromCart).isDisplayed()).isTrue();
            });
        });

        CartPage cartPage = new CartPage();
        step("Перейти на страницу корзины", () -> {
            driver.findElement(productsPage.linkShoppingCart).click();
            assertThat(driver.findElement(productsPage.headerPage).getText()).isEqualTo("Your Cart");

            step("В корзине есть товар", () -> {
                assertThat(driver.findElement(cartPage.blockCartItem).isDisplayed()).isTrue();
            });
        });

        step("Удалить товар из корзины", () -> {
            driver.findElement(cartPage.buttonRemove).click();

            step("В корзине нет товаров", () -> {
                assertThat(driver.findElement(cartPage.blockRemovedCartItem)).isNotNull();
            });
        });
    }
}
