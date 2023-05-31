package allure.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class BankTest {

    Integer amount;

    @BeforeEach
    public void setUp() {
        Configuration.baseUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#";
    }

    @Test
    @DisplayName("Пополнение счета")
    public void shouldCreateDeposit() {
        step("Авторизоваться", () -> {
            open("/login");
            $(byText("Customer Login")).click();

            $("#userSelect").shouldBe(visible);
            $("#userSelect").selectOption(5);

            $(byText("Login")).click();
            $(".borderM strong").shouldHave(text("Welcome Neville Longbottom !!"));
        });

        final Integer balance = Integer.valueOf($$("[ng-hide='noAccount'] strong").get(1).getText());

        step("Указать сумму для пополнения счета и нажать кнопку 'Deposit'", () -> {
            amount = new Random().nextInt(1000);
            $("[ng-class='btnClass2']").click();
            $(byText("Amount to be Deposited :")).shouldBe(visible);
            $("[ng-model='amount']").sendKeys(String.valueOf(amount));
            $("[name='myForm']").$(byText("Deposit")).click();

            return amount;
        });

        step("Проверить, что счет пополнен", () -> {
            $("[ng-show='message']").shouldHave(text("Deposit Successful"));
            final Integer newBalance = Integer.valueOf($$("[ng-hide='noAccount'] strong").get(1).getText());
            assertThat(newBalance - balance).isEqualTo(amount);
        });
    }
}
