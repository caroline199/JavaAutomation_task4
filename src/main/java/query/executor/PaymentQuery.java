package query.executor;

import entity.Account;
import entity.Transaction;
import entity.User;

import java.sql.*;


public class PaymentQuery {
    public static void addUser(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Users (name, address) VALUES (?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getAddress());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public static void addAccount(Connection connection, Account account) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Accounts (userId, balance, currency) VALUES (?, ?, ?)");
        preparedStatement.setInt(1, account.getUserId());
        preparedStatement.setDouble(2, account.getBalance());
        preparedStatement.setString(3, account.getCurrency());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public static void deposit(Connection connection,int idUpdateDeposit, Transaction transaction) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Accounts SET balance = balance + ? WHERE accountId = ?");
        preparedStatement.setDouble(1, transaction.getAmount());
        preparedStatement.setInt(2, idUpdateDeposit);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static void withdraw (Connection connection, int idUpdateWithdraw, Transaction transaction) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Accounts SET balance = balance - ? WHERE accountId = ?");
        preparedStatement.setDouble(1, transaction.getAmount());
        preparedStatement.setInt(2, idUpdateWithdraw);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static void printAllAccounts(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Accounts;");
        while (resultSet.next()) {
            System.out.println("accountId: " + resultSet.getInt("accountId"));
            System.out.println("userId:" + resultSet.getInt("userId"));
            System.out.println("balance: " + resultSet.getDouble("balance"));
            System.out.println("currency:" + resultSet.getString("currency"));
        }
        statement.close();
        resultSet.close();
    }

    public static void printAllUsers(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Users;");
        while (resultSet.next()) {
            System.out.println("userId:" + resultSet.getInt("userId"));
            System.out.println("name: " + resultSet.getString("name"));
            System.out.println("address:" + resultSet.getString("address"));
        }
        statement.close();
        resultSet.close();
    }
}
