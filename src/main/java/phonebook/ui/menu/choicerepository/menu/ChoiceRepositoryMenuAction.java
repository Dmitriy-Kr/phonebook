package phonebook.ui.menu.choicerepository.menu;

import phonebook.services.ContactServiseType;
import phonebook.services.CreateContactServise;
import phonebook.ui.menu.MenuAction;

public class ChoiceRepositoryMenuAction implements MenuAction {
    ContactServiseType contactServiseType;
    CreateContactServise createContactServise;

    public ChoiceRepositoryMenuAction(ContactServiseType contactServiseType, CreateContactServise createContactServise) {
        this.contactServiseType = contactServiseType;
        this.createContactServise = createContactServise;
    }

    @Override
    public void doAction() {
        createContactServise.setContactServiseType(contactServiseType);
    }

    @Override
    public String getName() {
        return "Выбирете в качестве хранилища данных " + contactServiseType.getRepositoryType();
    }

    @Override
    public boolean closeAfter() {
        return true;
    }
}
