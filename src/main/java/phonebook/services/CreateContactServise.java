package phonebook.services;

public class CreateContactServise {
    private ContactServiseType contactServiseType;

    public void setContactServiseType(ContactServiseType contactServiseType) {
        this.contactServiseType = contactServiseType;
    }

    public ContactServiseType getContactServiseType() {
        return contactServiseType;
    }

    public ContactsService createContactServise (){
        switch (contactServiseType){
            case MEMORY:
                return new InMemoryContactsService();
            case CSV_FILE:
                return new FileContactsServiceCSV();
            case XML_FILE:
                return new FileContactsServiceXML();
            case JSON_FILE:
                return new FileContactsServiceJSON();
            case OBJECT_FILE:
                return new FileContactsServiceObj();
            case NETWORK:
                return new NetworkContactsService();
        }
        return new InMemoryContactsService();
    }
}
