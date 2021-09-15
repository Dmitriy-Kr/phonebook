package phonebook.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class FileContactsServiceJSON implements ContactsService {
    private ContactsList contactsList;

    public FileContactsServiceJSON() {
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
}