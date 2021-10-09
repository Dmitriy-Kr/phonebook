package phonebook.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import phonebook.pojo.entity.Contact;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class FileContactsServiceJSON implements ContactsService {
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
        File file = new File("phonebook.json");
        if (!file.exists()) {
            return new ContactsList();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ContactsList contactsList = null;
        try (FileReader fr = new FileReader(file, StandardCharsets.UTF_8);) {
            BufferedReader br = new BufferedReader(fr);
            contactsList = objectMapper.readValue(br, ContactsList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactsList == null ? new ContactsList() : contactsList;
    }

    public void writeFile(ContactsList contactsList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter fw = new FileWriter("phonebook.json", StandardCharsets.UTF_8)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fw, contactsList);
        } catch (IOException e) {
            e.printStackTrace();
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