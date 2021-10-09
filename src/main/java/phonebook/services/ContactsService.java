package phonebook.services;

import phonebook.pojo.entity.Contact;

public interface ContactsService {
    ContactsList getAll() /*throws NetworkException*/;
    void remove(int index);
    void add(Contact c);
    Contact get(int index);
    int size();
    ContactsList findByName(String s);
    ContactsList findByValue(String valueStart);
}
