package phonebook.services;

import phonebook.pojo.entity.Contact;
import phonebook.pojo.entity.ContactType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FileContactsServiceCSV implements ContactsService {
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
        return phonebook.utils.ListUtils.filter(readFile(),
                x -> x.getName()
                        .toLowerCase(Locale.ROOT)
                        .startsWith(nameLetters.
                                toLowerCase(Locale.ROOT)));
    }

    @Override
    public ContactsList findByValue(String valueStart) {
        return phonebook.utils.ListUtils.filter(readFile(),
                x -> x.getValue()
                        .toLowerCase(Locale.ROOT)
                        .startsWith(valueStart.
                                toLowerCase(Locale.ROOT)));
    }

    private ContactsList readFile() {
        File file = new File("phonebook.csv");
        if (!file.exists()) {
            return new ContactsList();
        }
        ContactsList contactsList = new ContactsList();
        try (FileReader fr = new FileReader(file, StandardCharsets.UTF_8);) {
            BufferedReader br = new BufferedReader(fr);
            List<String[]> list = br.lines().map(str -> str.split(",")).collect(Collectors.toList());
            for (String[] s : list) {
                contactsList.add(new Contact(s[0], s[1], ContactType.valueOf(s[2]), s[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactsList;
    }

    public void writeFile(ContactsList contactsList) {
        try (FileWriter fw = new FileWriter("phonebook.csv", StandardCharsets.UTF_8)) {
            for (int i = 0; i < contactsList.size(); i++) {
                fw.write(contactsList.get(i).getId()
                        + ","
                        + contactsList.get(i).getName()
                        + ","
                        + contactsList.get(i).getContactType()
                        + ","
                        + contactsList.get(i).getValue()
                        + System.lineSeparator());
            }
            fw.flush();
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