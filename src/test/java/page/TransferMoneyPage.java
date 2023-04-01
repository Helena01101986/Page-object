package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {
    private SelenideElement heading = $("h1").shouldHave(text("Пополнение карты"));
    private SelenideElement sumField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement error = $("[data-test-id=notification] .notification__content");

    public TransferMoneyPage() {
        heading.shouldBe(visible);
        sumField.shouldBe(visible);
        fromField.shouldBe(visible);
        transferButton.shouldBe(visible);

    }

    public DashboardPage transfer(int sum, DataHelper.CardInfo cardInfo) {
        sumField.setValue(String.valueOf(sum));
        fromField.setValue(cardInfo.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void Error() {
        error.shouldHave(text("Ошибка!")).shouldBe(visible);
    }

}
