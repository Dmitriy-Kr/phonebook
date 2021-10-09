package phonebook.ui.menu.authoring.menu;

import phonebook.services.AuthorizingService;
import phonebook.ui.menu.MenuAction;

import java.util.Scanner;

public class LoginMenuAction implements MenuAction {
    Scanner scanner;
    AuthorizingService authorizingService;

    public LoginMenuAction(Scanner scanner, AuthorizingService authorizingService) {
        this.scanner = scanner;
        this.authorizingService = authorizingService;
    }

    @Override
    public void doAction() {
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        try{
        if (authorizingService.login(login, password)) {
            System.out.println("Вы успешно авторизировались");
        } else {
            System.out.println("Ошибка");
        }} catch (RuntimeException e){
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Авторизация";
    }

    @Override
    public boolean closeAfter() {
        return authorizingService.isAuthoring();
    }
}
