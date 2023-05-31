package allure.selenide;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    SignInPage signInPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @BeforeAll
    public static void setProperty() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }

    @AfterEach
    public void closeAfterEach() {
        WebDriverRunner.closeWebDriver();
    }

}
