package phonebook.ui.menu.process.menu;


import phonebook.services.ContactsService;
import phonebook.ui.menu.MenuAction;

import java.util.Scanner;

public class RemoveContactMenuAction implements MenuAction {
    private ContactsService phoneBook;
    Scanner scanner;

    public RemoveContactMenuAction(Scanner scanner, ContactsService phoneBook) {
        this.phoneBook = phoneBook;
        this.scanner = scanner;
    }

    @Override
    public void doAction() {
        System.out.print("Введите id контакта: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Введённое значение не является числом");
            return;
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        try {
            phoneBook.remove(choice - 1);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Удалить контакт";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
