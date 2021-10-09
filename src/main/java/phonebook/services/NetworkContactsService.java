package phonebook.services;

import phonebook.pojo.entity.Contact;
import phonebook.pojo.transactions.*;
import phonebook.services.networkservices.NetworkService;

import java.util.Map;

public class NetworkContactsService implements ContactsService {
    NetworkService networkService = new NetworkService();
    private final String URL = "https://mag-contacts-api.herokuapp.com";

    @Override
    public ContactsList getAll() {
        String allContactsURL = URL + "/contacts";
        ContactsList contactsList = new ContactsList();
        try {

            GetAllResponse getAllResponse =
                    networkService.getRequest(allContactsURL,
                            Map.of("Accept", "application/json",
                                    "Authorization", "Bearer " + AuthoringServiceNetwork.token,
                                    "Content-Type", "application/json"),
                            GetAllResponse.class);

            for (Contact contact : getAllResponse.getContacts()) {
                contactsList.add(new Contact(contact.getId(),
                        contact.getName(),
                        contact.getContactType(),
                        contact.getValue()));
            }

        } catch (RuntimeException e) {
            throw e;
        }
        return contactsList;
    }

    @Override
    public void remove(int index) {
        throw new RuntimeException("Данная операция не поддерживается сервером");
    }

    @Override
    public void add(Contact c) {
        String addContactURL = URL + "/contacts/add";

        try {

            networkService.postRequest(addContactURL,
                    Map.of("Accept", "application/json",
                            "Authorization", "Bearer " + AuthoringServiceNetwork.token,
                            "Content-Type", "application/json"),
                    c,
                    StatusResponse.class);

        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public Contact get(int index) {
        return null;
    }

    @Override
    public int size() {
        return getAll().size();
    }

    @Override
    public ContactsList findByName(String s) {
        String findContactURL = URL + "/contacts/find";
        ContactsList contactsList = new ContactsList();
        FindRequest findRequest = new FindRequest().setName(s);

        try {

            FindResponse findResponse =
                    networkService.postRequest(findContactURL,
                            Map.of("Accept", "application/json",
                                    "Authorization", "Bearer " + AuthoringServiceNetwork.token,
                                    "Content-Type", "application/json"),
                            findRequest,
                            FindResponse.class);

            for (Contact contact : findResponse.getContacts()) {
                contactsList.add(new Contact(contact.getId(),
                        contact.getName(),
                        contact.getContactType(),
                        contact.getValue()));
            }

        } catch (RuntimeException e) {
            throw e;
        }
        return contactsList;
    }

    @Override
    public ContactsList findByValue(String valueStart) {
        String findContactURL = URL + "/contacts/find";
        ContactsList contactsList = new ContactsList();
        FindRequest findRequest = new FindRequest().setValue(valueStart);

        try {

            FindResponse findResponse =
                    networkService.postRequest(findContactURL,
                            Map.of("Accept", "application/json",
                                    "Authorization", "Bearer " + AuthoringServiceNetwork.token,
                                    "Content-Type", "application/json"),
                            findRequest,
                            FindResponse.class);

            for (Contact contact : findResponse.getContacts()) {
                contactsList.add(new Contact(contact.getId(),
                        contact.getName(),
                        contact.getContactType(),
                        contact.getValue()));
            }

        } catch (RuntimeException e) {
            throw e;
        }
        return contactsList;
    }
}
