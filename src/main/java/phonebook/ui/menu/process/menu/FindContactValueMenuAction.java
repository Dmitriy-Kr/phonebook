package phonebook.ui.menu.process.menu;

import phonebook.services.ContactsService;
import phonebook.ui.menu.MenuAction;
import phonebook.ui.views.ContactsView;

import java.util.Scanner;

public class FindContactValueMenuAction implements MenuAction {
    Scanner scanner;
    private ContactsService phoneBook;
    private ContactsView contactsView;

    public FindContactValueMenuAction(Scanner scanner, ContactsService phoneBook, ContactsView contactsView) {
        this.scanner = scanner;
        this.phoneBook = phoneBook;
        this.contactsView = contactsView;
    }

    @Override
    public void doAction() {
        System.out.print("Введите значение: ");
        String valueParts = scanner.nextLine();
        contactsView.showAllContacts(phoneBook.findByValue(valueParts));
        System.out.println("----------------------------------------------------");
    }

    @Override
    public String getName() {
        return "Найти контакт по значению";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
