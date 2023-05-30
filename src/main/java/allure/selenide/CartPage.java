package allure.selenide;

import org.openqa.selenium.By;

public class CartPage {

    public By buttonRemove = By.id("remove-sauce-labs-backpack");

    public By blockCartItem = By.className("cart_item");

    public By blockRemovedCartItem = By.className("removed_cart_item");
}
