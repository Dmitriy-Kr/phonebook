package phonebook.services;

import java.io.*;
import java.util.Locale;

public class FileContactsServiceObj implements ContactsService {
    private ContactsList contactsList;

    public FileContactsServiceObj() {
        contactsList = readFile();
    }

    @Override
    public ContactsList getAll() {
        return contactsList;
    }

    @Override
    public void remove(int index) {
        contactsList.remove(index);
        writeFile(contactsList);
    }

    @Override
    public void add(Contact c) {
        contactsList.add(c);
        writeFile(contactsList);
    }

    @Override
    public Contact get(int index) {
        return contactsList.get(index);
    }

    @Override
    public int size() {
        return contactsList.size();
    }

    @Override
    public ContactsList findByName(String nameLetters) {
        return phonebook.utils.ListUtils.filter(contactsList, x -> x.getName().toLowerCase(Locale.ROOT)
                .startsWith(nameLetters.toLowerCase(Locale.ROOT)));
    }

    private ContactsList readFile() {
        File file = new File("phonebook.obj");
        if(!file.exists()){
            return new ContactsList();
        }
        ContactsList contactsList = new ContactsList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Object o = ois.readObject();
                if (o == null) {
                    break;
                }
                    contactsList.add((Contact) o);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contactsList;
    }

    public void writeFile(ContactsList contactsList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("phonebook.obj"))) {
            for (int i = 0; i < size(); i++) {
                oos.writeObject(contactsList.get(i));
            }
            oos.writeObject(null);
            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}