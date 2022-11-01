package ru.netology.data;

import lombok.Value;

import java.util.List;
import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static AuthInfo getValidAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    private static List<String> listCardsNumbersOfFirstUsers() {
        return List.of("5559 0000 0000 0001", "5559 0000 0000 0002");
    }

    public static String getCardNumberOfFirstUser(int numberOnPage) {
        return listCardsNumbersOfFirstUsers().get((numberOnPage - 1));
    }

    private static List<String> listCardsNumbersOfAnotherUsers() {
        return List.of("5559 0000 0000 0008");
    }

    public static String getCardNumberOfAnotherUser() {
        return listCardsNumbersOfAnotherUsers().get((0));
    }

    public static Integer getValidAmount(int balance) {
        var validAmountInKopecks = new Random().nextInt(balance) + 1;
        int validAmountInRubles = validAmountInKopecks / 100;
        return validAmountInRubles;
    }
}
