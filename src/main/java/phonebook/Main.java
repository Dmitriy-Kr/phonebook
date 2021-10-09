package phonebook;

import phonebook.services.*;
import phonebook.ui.menu.Menu;
import phonebook.ui.menu.authoring.menu.LoginMenuAction;
import phonebook.ui.menu.authoring.menu.RegistrationMenuAction;
import phonebook.ui.menu.choicerepository.menu.ChoiceRepositoryMenuAction;
import phonebook.ui.menu.process.menu.*;
import phonebook.ui.views.ContactsView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactsView contactsView = new ContactsView();
        AuthorizingService authorizingService;
        CreateContactServise createContactServise = new CreateContactServise();
        ContactsService phoneBook;

        System.out.println("Приветствую Вас." + System.lineSeparator());

        Menu choiceRepositoryMenu = new Menu(scanner);
        for (ContactServiseType type : ContactServiseType.values()) {
            choiceRepositoryMenu.addAction(new ChoiceRepositoryMenuAction(type, createContactServise));
        }
        choiceRepositoryMenu.addAction(new ExitMenuAction());
        choiceRepositoryMenu.run();

        //exiting a program if no implementation is selected
        if (createContactServise.getContactServiseType() == null) {
            return;
        }

        System.out.println("Выбранное хранилище данных: "
                + createContactServise.getContactServiseType().getRepositoryType());

        if (createContactServise.getContactServiseType() == ContactServiseType.NETWORK) {
            authorizingService = new AuthoringServiceNetwork();
        } else {
            authorizingService = new AuthoringServiceLocal("admin", "admin");
        }

        Menu authoringMenu = new Menu(scanner);
        authoringMenu.addAction(new LoginMenuAction(scanner, authorizingService));
        authoringMenu.addAction(new RegistrationMenuAction(scanner, authorizingService));
        authoringMenu.addAction(new ExitMenuAction());
        authoringMenu.run();

        if (authorizingService.isAuthoring()) {

            phoneBook = createContactServise.createContactServise();

            Menu menu = new Menu(scanner);

            menu.addAction(new ReadAllContactsMenuAction(phoneBook, contactsView));
            menu.addAction(new AddContactMenuAction(scanner, phoneBook));
            menu.addAction(new RemoveContactMenuAction(scanner, phoneBook));
            menu.addAction(new FindContactMenuAction(scanner, phoneBook, contactsView));
            menu.addAction(new FindContactValueMenuAction(scanner, phoneBook, contactsView));
            menu.addAction(new ExitMenuAction());

            menu.run();
        }
    }
}
