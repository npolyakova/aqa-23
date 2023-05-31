package allure.selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ProductsPage extends BasePage {
    public SelenideElement divProductItem = $(".inventory_item");

    public SelenideElement buttonAddToCart = divProductItem.$("#add-to-cart-sauce-labs-backpack");

    public SelenideElement buttonRemoveFromCart = $("#remove-sauce-labs-backpack");

    public SelenideElement iconCartBadge = $(".shopping_cart_badge");

    public SelenideElement linkShoppingCart = $(".shopping_cart_link");

//    CartPage clickToCartButton() {
//        linkShoppingCart.click();
//        return page(CartPage.class);
//    }
//
    public CartPage openCart() {
        linkShoppingCart.click();
        CartPage cr = page(CartPage.class);
        cr.headerPage.shouldHave(text("Your Cart"));
        return page(CartPage.class);
    }
}
