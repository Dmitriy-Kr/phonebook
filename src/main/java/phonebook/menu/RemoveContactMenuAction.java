package phonebook.menu;


import phonebook.services.ContactsService;

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
        System.out.print("Введите номер позиции контакта: ");
        if(!scanner.hasNextInt()) {
            System.out.println("Введённое значение не является числом");
            return;
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        phoneBook.remove(choice - 1);
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
