package service;

import entity.User;
import java.util.Scanner;

public class UserService {
    public static User inputUser() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name: ");
        user.setName(scanner.nextLine());
        System.out.println("Enter address: ");
        user.setAddress(scanner.nextLine());
        return user;
    }
}