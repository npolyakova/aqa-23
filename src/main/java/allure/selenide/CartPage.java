package allure.selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartPage extends BasePage {

    public SelenideElement buttonRemove = $("#remove-sauce-labs-backpack");

    public SelenideElement blockCartItem = $(".cart_item");

    public SelenideElement blockRemovedCartItem = $(".removed_cart_item");
}
