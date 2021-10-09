package phonebook.services;

import phonebook.pojo.entity.Contact;

import java.util.Locale;

public class InMemoryContactsService implements ContactsService {
    private ContactsList contactsList;

    public InMemoryContactsService() {
        contactsList = new ContactsList();
    }

    @Override
    public ContactsList getAll() {
        return contactsList;
    }

    public Contact get(int index) {
        return contactsList.get(index);
    }

    @Override
    public void remove(int index) {
        contactsList.remove(index);
    }

    @Override
    public void add(Contact c) {
        c.setId(String.valueOf(getNextId()));
        contactsList.add(c);
    }

    public int size() {
        return contactsList.size();
    }

    @Override
    public ContactsList findByName(String nameLetters) {
        return phonebook.utils.ListUtils.filter(contactsList, x -> x.getName().toLowerCase(Locale.ROOT)
                .startsWith(nameLetters.toLowerCase(Locale.ROOT)));
    }

    @Override
    public ContactsList findByValue(String valueStart) {
        return phonebook.utils.ListUtils.filter(contactsList, x -> x.getValue().toLowerCase(Locale.ROOT)
                .startsWith(valueStart.toLowerCase(Locale.ROOT)));
    }

    private int getNextId() {
        int nextId = 0;
        for (int i = 0; i < contactsList.size(); i++) {
            int id;
            if((id = Integer.parseInt(contactsList.get(i).getId())) > nextId){
                nextId = id;
            }

        }
        return ++nextId;
    }
}