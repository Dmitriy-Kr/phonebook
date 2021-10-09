package phonebook.ui.menu.process.menu;


import phonebook.pojo.entity.Contact;
import phonebook.pojo.entity.ContactType;
import phonebook.services.ContactsService;
import phonebook.ui.menu.MenuAction;
import phonebook.utils.EmailValidator;
import phonebook.utils.PhoneValidator;

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
        System.out.println("Выберете тип: ");
        ContactType type = null;
        while (type == null) {
            int counter = 0;
            int value = 0;
            for (ContactType ct : ContactType.values()) {
                System.out.printf("%d. %s%s", ++counter, ct, System.lineSeparator());
            }
            System.out.print("-> ");

            if (scanner.hasNextInt()) {
                value = scanner.nextInt() - 1;
                scanner.nextLine();
            } else {
                System.out.println("Введите число!!!");
                continue;
            }

            if (value < 0 || value >= ContactType.values().length) {
                System.out.println("Повторите ввод");
                continue;
            }
            type = ContactType.values()[value];
        }

        String value;
        while (true) {
            System.out.printf("Введите %s: ", type.getName());
            value = scanner.nextLine();
            if (type == ContactType.EMAIL && EmailValidator.emailValidate(value)){
                break;
            }
            if (type == ContactType.PHONE && PhoneValidator.phoneValidate(value)){
                break;
            }
            System.out.println("Некорректное значение.");
        }

        phoneBook.add(new Contact(null, name, type, value));
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
