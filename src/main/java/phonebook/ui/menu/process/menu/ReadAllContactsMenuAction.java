package phonebook.ui.menu.process.menu;


import phonebook.services.ContactsService;
import phonebook.ui.menu.MenuAction;
import phonebook.ui.views.ContactsView;

public class ReadAllContactsMenuAction implements MenuAction {
    private ContactsService phoneBook;
    private ContactsView contactsView;

    public ReadAllContactsMenuAction(ContactsService phoneBook, ContactsView contactsView) {
        this.phoneBook = phoneBook;
        this.contactsView = contactsView;
    }

    @Override
    public void doAction() {
        System.out.println("--------------------------------------------------");
        try {
            contactsView.showAllContacts(phoneBook.getAll());
        } catch ( RuntimeException e){
            System.out.println(e.getMessage());
        }
        System.out.println("--------------------------------------------------");
    }

    @Override
    public String getName() {
        return "Показать все контакты";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
