package phonebook.services;

public interface ContactsService {
    ContactsList getAll();
    void remove(int index);
    void add(Contact c);
    Contact get(int index);
    int size();
    ContactsList findByName(String s);
}
