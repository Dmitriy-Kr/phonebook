package phonebook.menu;


import phonebook.services.Contact;
import phonebook.services.ContactsService;

import java.util.Scanner;

public class AddContactMenuAction implements MenuAction {
    Scanner scanner;
    private ContactsService phoneBook;

    public AddContactMenuAction(Scanner scanner, ContactsService phoneBook) {
        this.scanner = scanner;
        this.phoneBook = phoneBook;
    }

    @Override
    public void doAction() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите номер телефона: ");
        String number = scanner.nextLine();
        phoneBook.add(new Contact(name, number));
    }

    @Override
    public String getName() {
        return "Добавить контакт";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
