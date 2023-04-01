package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int cardBalance0001 = dashboardPage.getCardBalance(DataHelper.getCardInfo0001());
        int cardBalance0002 = dashboardPage.getCardBalance(DataHelper.getCardInfo0002());
        var transferMoneyPage = dashboardPage.transfer(DataHelper.getCardInfo0001());
        int sum = 1500;
        transferMoneyPage.transfer(sum, DataHelper.getCardInfo0002());
        Assertions.assertEquals(cardBalance0002 - sum, dashboardPage.getCardBalance(DataHelper.getCardInfo0002()));
        Assertions.assertEquals(cardBalance0001 + sum, dashboardPage.getCardBalance(DataHelper.getCardInfo0001()));
    }

    @Test
    void shouldTransferMoneyFromCardToSameCard() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int cardBalance0001 = dashboardPage.getCardBalance(DataHelper.getCardInfo0001());
        int cardBalance0002 = dashboardPage.getCardBalance(DataHelper.getCardInfo0002());
        var transferMoneyPage = dashboardPage.transfer(DataHelper.getCardInfo0001());
        int sum = 500;
        transferMoneyPage.transfer(sum, DataHelper.getCardInfo0001());
        Assertions.assertEquals(cardBalance0002, dashboardPage.getCardBalance(DataHelper.getCardInfo0002()));
        Assertions.assertEquals(cardBalance0001, dashboardPage.getCardBalance(DataHelper.getCardInfo0001()));
    }

    /*@Test
    void shouldTransferSumOverLimit() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int cardBalance0001 = dashboardPage.getCardBalance(DataHelper.getCardInfo0001());
        int cardBalance0002 = dashboardPage.getCardBalance(DataHelper.getCardInfo0002());
        var transferMoneyPage = dashboardPage.transfer(DataHelper.getCardInfo0001());
        int sum = cardBalance0001 + 10000;
        transferMoneyPage.transfer(sum, DataHelper.getCardInfo0002());
        transferMoneyPage.Error();
        Assertions.assertEquals(cardBalance0002, dashboardPage.getCardBalance(DataHelper.getCardInfo0002()));
        Assertions.assertEquals(cardBalance0001, dashboardPage.getCardBalance(DataHelper.getCardInfo0001()));
    }*/

}




