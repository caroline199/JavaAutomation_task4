import service.AccountService;
import service.TransactionService;
import service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static query.executor.PaymentQuery.*;

public class ApplicationDB {
    private static final String SQLCONN =
            "jdbc:sqlite:" + System.getProperty("user.dir") + "//src//main//resources//payment.db";


    public static void main(String[] args) throws SQLException {
        if (isDriverExists()) {
            Connection connection = DriverManager.getConnection(SQLCONN);
            String action;
            do {
                printMenu();
                action = new Scanner(System.in).nextLine();
                switch (action) {
                    case "1":
                        addUser(connection, UserService.inputUser());
                        break;
                    case "2":
                        addAccount(connection, AccountService.inputAccount());
                        break;
                    case "3":
                        System.out.println("Enter accountId of existing account");
                        int idUpdateDeposit = new Scanner(System.in).nextInt();
                        deposit(connection, idUpdateDeposit, TransactionService.inputTransaction());
                        break;
                    case "4":
                        System.out.println("Enter accountId of existing account");
                        int idUpdateWithdraw = new Scanner(System.in).nextInt();
                        withdraw(connection, idUpdateWithdraw, TransactionService.inputTransaction());
                        break;
                    case "5":
                        printAllAccounts(connection);
                        break;
                    case "6":
                        printAllUsers(connection);
                        break;
                }
            } while (!"7".equals(action));
            connection.close();
        }
    }

    public static boolean isDriverExists(){
        try {
            Class.forName("org.sqlite.JDBC");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver was not found");
            return false;
        }
    }

    private static void printMenu() {
        System.out.println("1 - User registration");
        System.out.println("2 - Add account");
        System.out.println("3 - Deposit");
        System.out.println("4 - Withdraw");
        System.out.println("5 - Print all accounts");
        System.out.println("6 - Print all users");
        System.out.println("7 - Exit");
    }
}