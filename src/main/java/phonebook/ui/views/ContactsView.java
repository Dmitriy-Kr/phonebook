package phonebook.ui.views;

import phonebook.pojo.entity.Contact;
import phonebook.services.ContactsList;

public class ContactsView {
    public void showContact(Contact contact) {
        System.out.printf("%-3s %-10s %-10s %-15s",
                contact.getId(),
                contact.getName(),
                contact.getContactType().getName(),
                contact.getValue());
    }

    public void showAllContacts(ContactsList contactsList){
        if(contactsList.size() == 0) {
            System.out.println("Записи отсутствуют");
            return;
        }
        System.out.printf("%-3s %-10s %-10s %-15s%s", "id", "Имя", "Тип",  "Значение", System.lineSeparator());
        for (int i = 0; i < contactsList.size(); i++) {
            showContact(contactsList.get(i));
            System.out.println();
        }
    }
}
