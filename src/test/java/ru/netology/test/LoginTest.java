package ru.netology.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.API;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.asynchttpclient.util.HttpConstants.Methods.POST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertEquals;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class LoginTest {

//    @Test
//    void shouldSuccess() {
//        POST http://localhost:9999/api/auth
//        Content-Type: application/json
//
//        {
//            "login": "vasya",
//                "password": "qwerty123"
//        }
//
//        String status = POST http://localhost:9999/api/auth // ваш обычный запрос
//                .then()
//                .statusCode(200)
//                .extract()
//                .path("status");
//
//        // используются matcher'ы Hamcrest
//        assertThat(status, equalTo("ok"));
//    }

//    @AfterAll
//    static void teardown() {
//        cleanDatabase();
//    }
//


    @Test
    void getBalanceCardFromDB() {
        System.out.println(SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(1)));
    }

    @Test
    void getVerificationCodeFromDB() {
        System.out.println(SQLHelper.getUsersPasswordFromDB("vasya"));
    }

    @Test
    void shouldReturnToken() {
        System.out.println("Токен = //" + API.shouldReturnToken(DataHelper.getValidAuthInfo()) + "//");
    }

    @Test
    void shouldGetCardNumber() {
        System.out.println(DataHelper.getCardNumberOfFirstUser(1));
    }

    @Test
    void shouldTransferBetweenFirstAndSecondCardOfFirstUser() {
        var fromCardNumberOnPage = 1;
        var toCardNumberOnPage = 2;
        var balanceFromCardBefore = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage));
        var balanceToCardBefore = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(toCardNumberOnPage));
        var amount = DataHelper.getValidAmount(balanceFromCardBefore);
        var expectedBalanceFromCard = balanceFromCardBefore - (amount * 100);
        var expectedBalanceToCard = balanceToCardBefore + (amount * 100);
        System.out.println("Начальный баланс карты №: " + fromCardNumberOnPage + " = " + balanceFromCardBefore);
        System.out.println("Начальный баланс карты №: " + toCardNumberOnPage + " = " + balanceToCardBefore);
        API.shouldTransfer(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage), DataHelper.getCardNumberOfFirstUser(toCardNumberOnPage), amount, API.shouldReturnToken(DataHelper.getValidAuthInfo()));
        var actualBalanceFromCardAfter = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage));
        var actualBalanceToCardAfter = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(toCardNumberOnPage));
        assertEquals(expectedBalanceFromCard, actualBalanceFromCardAfter);
        assertEquals(expectedBalanceToCard, actualBalanceToCardAfter);
        System.out.println("Конечный баланс карты №: " + fromCardNumberOnPage + " = " + actualBalanceFromCardAfter);
        System.out.println("Конечный баланс карты №: " + toCardNumberOnPage + " = " + actualBalanceToCardAfter);
    }

    @Test
    void shouldTransferBetweenFirstCardOfFirstUserAndCardOfSecondUser() {
        var fromCardNumberOnPage = 1;
        var toCardNumber = "5559 0000 0000 0008";
        var balanceFromCardBefore = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage));
//        var balanceToCardBefore = SQLHelper.getBalanceCardFromDB(toCardNumber);
        var amount = DataHelper.getValidAmount(balanceFromCardBefore);
        var expectedBalanceFromCard = balanceFromCardBefore - (amount * 100);
        var expectedBalanceToCard =  amount * 100;
        System.out.println("Начальный баланс карты №: " + fromCardNumberOnPage + " = " + balanceFromCardBefore);
//        System.out.println("Начальный баланс карты №: " + toCardNumber + " = " + balanceToCardBefore);
        API.shouldTransfer(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage), toCardNumber, amount, API.shouldReturnToken(DataHelper.getValidAuthInfo()));
        var actualBalanceFromCardAfter = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage));
        var actualBalanceToCardAfter = SQLHelper.getBalanceCardFromDB(toCardNumber);
        assertEquals(expectedBalanceFromCard, actualBalanceFromCardAfter);
        assertEquals(expectedBalanceToCard, actualBalanceToCardAfter);
        System.out.println("Конечный баланс карты №: " + fromCardNumberOnPage + " = " + actualBalanceFromCardAfter);
        System.out.println("Конечный баланс карты №: " + toCardNumber + " = " + actualBalanceToCardAfter);
    }

    @Test
    void getBalanceCardFromDB2() {
        System.out.println(SQLHelper.getBalanceAnythingCardFromDB("5559 0000 0000 0008"));
    }

    @Test
    void getBalanceCardFromDB3() {
        System.out.println(SQLHelper.getBalanceCardFromDB("5559 0000 0000 0008"));
    }





}

