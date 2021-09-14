package phonebook.services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FileContactsServiceCSV implements ContactsService {
    private ContactsList contactsList;

    public FileContactsServiceCSV() {
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
        File file = new File("phonebook.csv");
        if(!file.exists()){
            return new ContactsList();
        }
        ContactsList contactsList = new ContactsList();
        try (FileReader fr = new FileReader(file, StandardCharsets.UTF_8);) {
            BufferedReader br = new BufferedReader(fr);
            List<String[]> list = br.lines().map(str -> str.split(",")).collect(Collectors.toList());
            for (String[] s : list) {
                contactsList.add(new Contact(s[0], s[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactsList;
    }

    public void writeFile(ContactsList contactsList) {
        try (FileWriter fw = new FileWriter("phonebook.csv", StandardCharsets.UTF_8)) {
            for (int i = 0; i < size(); i++) {
                fw.write(contactsList.get(i).getName() + "," + contactsList.get(i).getNumber() + System.lineSeparator());
            }
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}