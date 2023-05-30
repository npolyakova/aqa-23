package allure.selenide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    WebDriver driver;

    public By divProductItem = By.cssSelector(".inventory_item");

    public By buttonAddToCart = By.id("add-to-cart-sauce-labs-backpack");

    public By buttonRemoveFromCart = By.id("remove-sauce-labs-backpack");

    public By iconCartBadge = By.className("shopping_cart_badge");

    public By linkShoppingCart = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }
}
