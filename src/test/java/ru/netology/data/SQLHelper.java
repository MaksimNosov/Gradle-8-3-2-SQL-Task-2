package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static DataHelper.VerificationCode getVerificationCodeFromDB() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var result = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return new DataHelper.VerificationCode(result);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static Integer getBalanceCardFromDB(String cardNumber) {
        var codeSQL = "SELECT balance_in_kopecks FROM cards WHERE number = ?;";
        try (var conn = getConn()) {
            var balance = runner.query(conn, codeSQL, new ScalarHandler<Integer>(), cardNumber);
            return balance;
        }
    }

    @SneakyThrows
    public static Integer getBalanceAnythingCardFromDB(String cardNumber) {
        var codeSQL = "SELECT SUM(amount_in_kopecks) FROM card_transactions WHERE target = ?;";
        try (var conn = getConn()) {

            var balance = runner.query(conn, codeSQL, new ScalarHandler<Integer>(), cardNumber);
//            ((BigInteger) result[1]).intValue();
            return balance + getBalanceCardFromDB(cardNumber);
        }
    }

    @SneakyThrows
    public static int setBalanceCardToDB(String cardNumber, int sumTransact) {
        var codeSQL = "UPDATE cards SET balance_in_kopecks = balance_in_kopecks + ? WHERE number = ?;";
        try (var conn = getConn()) {
            var balance = runner.update(conn, codeSQL, sumTransact, cardNumber);
            return balance;
        }
    }

    @SneakyThrows
    public static String getUsersPasswordFromDB(String login) {
        var codeSQL = "SELECT password FROM users WHERE login = ?;";
        try (var conn = getConn()) {
            var password = runner.query(conn, codeSQL, new ScalarHandler<String>(), login);
            return password;
        }
    }



    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }

}
