package service;

import entity.Account;
import java.util.Scanner;

public class AccountService {

    public static Account inputAccount() {
        Account account = new Account();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter userId: ");
        account.setUserId(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter balance: ");
        account.setBalance(Double.parseDouble(scanner.nextLine()));
        System.out.println("Enter currency: ");
        account.setCurrency(scanner.nextLine());
        return account;
    }
}