package allure.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.have;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class FirstSelenideTest {

    @Test
    public void shouldOpenYandex() {
        Faker faker = new Faker(); // TODO (new Locale("rus"));
        String query = faker.artist().name();

        Configuration.baseUrl = "https://dzen.ru";
        open("/"); // https://dzen.ru + /

        Selenide.webdriver().driver().switchTo().frame(0);
        $("[aria-label='Запрос']").sendKeys(query, Keys.ENTER);

        Selenide.webdriver().driver().switchTo().window(1);
// TODO        $(shadowCss( "div", "[role=\"combobox\"]")).shouldHave(text("Иннополис"));

        $("#uniq16784632278721").shouldHave(attribute("value", query));

//        $$("#search-result li").get(0).shouldHave(text("Иннополис"));
        for (SelenideElement el : $$("#search-result li.serp-item")
                .filter(not(have(attribute("data-fast-name", "multiusurveys"))))) {
        }

        sleep(2000);
    }
}