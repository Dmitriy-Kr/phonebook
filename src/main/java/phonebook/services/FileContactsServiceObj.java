package phonebook.services;

import phonebook.pojo.entity.Contact;

import java.io.*;
import java.util.Locale;

public class FileContactsServiceObj implements ContactsService {
    private ContactsList contactsList;

    @Override
    public ContactsList getAll() {
        return readFile();
    }

    @Override
    public void remove(int index) {
        contactsList = readFile();
        contactsList.remove(index);
        writeFile(contactsList);
    }

    @Override
    public void add(Contact c) {
        contactsList = readFile();
        c.setId(String.valueOf(getNextId(contactsList)));
        contactsList.add(c);
        writeFile(contactsList);
    }

    @Override
    public Contact get(int index) {
        return readFile().get(index);
    }

    @Override
    public int size() {
        return readFile().size();
    }

    @Override
    public ContactsList findByName(String nameLetters) {
        return phonebook.utils.ListUtils.filter(readFile(), x -> x.getName().toLowerCase(Locale.ROOT)
                .startsWith(nameLetters.toLowerCase(Locale.ROOT)));
    }

    @Override
    public ContactsList findByValue(String valueStart) {
        return phonebook.utils.ListUtils.filter(readFile(), x -> x.getValue().toLowerCase(Locale.ROOT)
                .startsWith(valueStart.toLowerCase(Locale.ROOT)));
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
            for (int i = 0; i < contactsList.size(); i++) {
                oos.writeObject(contactsList.get(i));
            }
            oos.writeObject(null);
            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int getNextId(ContactsList contactsList) {
        contactsList = readFile();
        int nextId = 0;
        for (int i = 0; i < contactsList.size(); i++) {
            int id;
            if ((id = Integer.parseInt(contactsList.get(i).getId())) > nextId) {
                nextId = id;
            }

        }
        return ++nextId;
    }
}