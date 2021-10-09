package phonebook.ui.menu.authoring.menu;

import phonebook.services.AuthoringServiceLocal;
import phonebook.services.AuthorizingService;
import phonebook.ui.menu.MenuAction;

import java.util.Scanner;


public class RegistrationMenuAction implements MenuAction {
    Scanner scanner;
    AuthorizingService authorizingService;

    public RegistrationMenuAction(Scanner scanner, AuthorizingService authorizingService) {
        this.scanner = scanner;
        this.authorizingService = authorizingService;
    }

    @Override
    public void doAction() {
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Введите дату рождения (гггг-мм-дд): ");
        String birthDay = scanner.nextLine();
        try {
            if (authorizingService.registration(login, password, birthDay)) {
                System.out.println("Вы успешно зарегистрировались");
            } else {
                System.out.println("Ошибка");
            }
        } catch (RuntimeException e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Регистрация";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
