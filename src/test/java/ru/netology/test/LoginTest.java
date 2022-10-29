package ru.netology.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.asynchttpclient.util.HttpConstants.Methods.POST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
//    //    @SneakyThrows
//    @Test
//    void shouldSuccessfulLogin() {
////        Thread.sleep(300000);
//        var loginPage = open("http://localhost:9999/", LoginPage.class);
//        var authInfo = DataHelper.getValidAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//
//        verificationPage.verifyVerificationPageVisibility();
//        var verificationCode = SQLHelper.getVerificationCode();
//        verificationPage.validVerify(verificationCode);
//    }
//
//    @Test
//    void shouldErrorMessageWhenInvalidUser() {
//        var loginPage = open("http://localhost:9999/", LoginPage.class);
//        var authInfo = DataHelper.generateRandomUser();
//        loginPage.validLogin(authInfo);
//        loginPage.verifyErrorNotificationVisibility();
//    }
//
//    @Test
//    void shouldErrorWhenInvalidVerificationCode() {
//        var loginPage = open("http://localhost:9999/", LoginPage.class);
//        var authInfo = DataHelper.getValidAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        verificationPage.verifyVerificationPageVisibility();
//        var verificationCode = DataHelper.generateRandomVerificationCode();
//        verificationPage.verify(verificationCode.getCode());
//        verificationPage.verifyErrorNotificationVisibility();
//    }
//
//    @Test
//    void shouldErrorNotificationThenMore3EntryValidLoginAndInvalidPassword() { //issue
//        var loginPage = open("http://localhost:9999/", LoginPage.class);
//        var authInfo = DataHelper.getAuthInfoValidLoginInvalidPassword();
//        loginPage.invalidLogin(authInfo);
//        loginPage.invalidLogin(authInfo);
//        loginPage.invalidLogin(authInfo);
//        loginPage.errorNotificationWhenMore3EntryValidLoginAndInvalidPassword();
//    }
//
//    @Test
//    void shouldErrorNotificationWhenMore3EntryValidUser() {
//        var loginPage = open("http://localhost:9999/", LoginPage.class);
//        var authInfo = DataHelper.getValidAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        verificationPage.verifyVerificationPageVisibility();
//        var verificationCode = SQLHelper.getVerificationCode();
//        verificationPage.validVerifyMore3Times(verificationCode);
//
//        var loginPage2 = open("http://localhost:9999/", LoginPage.class);
//        var authInfo2 = DataHelper.getValidAuthInfo();
//        var verificationPage2 = loginPage2.validLogin(authInfo2);
//        verificationPage2.verifyVerificationPageVisibility();
//        var verificationCode2 = SQLHelper.getVerificationCode();
//        verificationPage.validVerifyMore3Times(verificationCode2);
//
//        var loginPage3 = open("http://localhost:9999/", LoginPage.class);
//        var authInfo3 = DataHelper.getValidAuthInfo();
//        var verificationPage3 = loginPage3.validLogin(authInfo3);
//        verificationPage3.verifyVerificationPageVisibility();
//        var verificationCode3 = SQLHelper.getVerificationCode();
//        verificationPage.validVerifyMore3Times(verificationCode3);
//
//        var loginPage4 = open("http://localhost:9999/", LoginPage.class);
//        var authInfo4 = DataHelper.getValidAuthInfo();
//        var verificationPage4 = loginPage4.validLogin(authInfo4);
//        verificationPage4.verifyVerificationPageVisibility();
//        var verificationCode4 = SQLHelper.getVerificationCode();
//        verificationPage.validVerifyMore3Times(verificationCode4);
//
//        verificationPage.errorNotificationWhenMore3EntryValidUser();
//    }

    @Test
    void task2() {
        System.out.println(SQLHelper.getBalanceCardFromDB("5559 0000 0000 0002"));
    }
}
