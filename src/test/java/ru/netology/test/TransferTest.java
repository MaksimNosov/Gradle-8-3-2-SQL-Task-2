package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.API;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;

import static org.testng.Assert.assertEquals;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class TransferTest {

    @AfterAll
    static void teardown() {
        cleanDatabase();
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
        API.shouldTransfer(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage), DataHelper.getCardNumberOfFirstUser(toCardNumberOnPage), amount, API.shouldReturnToken(DataHelper.getValidAuthInfo()));
        var actualBalanceFromCardAfter = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage));
        var actualBalanceToCardAfter = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(toCardNumberOnPage));
        assertEquals(expectedBalanceFromCard, actualBalanceFromCardAfter);
        assertEquals(expectedBalanceToCard, actualBalanceToCardAfter);
    }

    @Test
    void shouldTransferBetweenFirstCardOfFirstUserAndCardOfAnotherUser() {
        var fromCardNumberOnPage = 1;
        var toCardNumber = DataHelper.getCardNumberOfAnotherUser();
        var balanceFromCardBefore = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage));
        var balanceToCardBefore = SQLHelper.getBalanceAnotherCardFromDB(toCardNumber);
        var amount = DataHelper.getValidAmount(balanceFromCardBefore);
        var expectedBalanceFromCard = balanceFromCardBefore - (amount * 100);
        var expectedBalanceToCard =  balanceToCardBefore + amount * 100;
        API.shouldTransfer(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage), toCardNumber, amount, API.shouldReturnToken(DataHelper.getValidAuthInfo()));
        var actualBalanceFromCardAfter = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage));
        var actualBalanceToCardAfter = SQLHelper.getBalanceAnotherCardFromDB(toCardNumber);
        assertEquals(expectedBalanceFromCard, actualBalanceFromCardAfter);
        assertEquals(expectedBalanceToCard, actualBalanceToCardAfter);
    }

    @Test
    void amountForTransferShouldNotMoreBalance() {
        var fromCardNumberOnPage = 2;
        var toCardNumberOnPage = 1;
        var balanceFromCardBefore = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage));
        var balanceToCardBefore = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(toCardNumberOnPage));
        var amount = balanceFromCardBefore * 2;
        int expectedBalanceFromCard = balanceFromCardBefore;
        int expectedBalanceToCard = balanceToCardBefore;
        API.shouldTransfer(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage), DataHelper.getCardNumberOfFirstUser(toCardNumberOnPage), amount, API.shouldReturnToken(DataHelper.getValidAuthInfo()));
        var actualBalanceFromCardAfter = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(fromCardNumberOnPage));
        var actualBalanceToCardAfter = SQLHelper.getBalanceCardFromDB(DataHelper.getCardNumberOfFirstUser(toCardNumberOnPage));
        assertEquals(actualBalanceFromCardAfter, expectedBalanceFromCard);
        assertEquals(expectedBalanceToCard, actualBalanceToCardAfter);
    }
}

