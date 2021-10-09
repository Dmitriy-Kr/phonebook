package phonebook.ui.menu.process.menu;

import phonebook.ui.menu.MenuAction;

public class ExitMenuAction implements MenuAction {
    @Override
    public void doAction() {
        System.out.println("Завершение работы программы");
    }

    @Override
    public String getName() {
        return "Выход";
    }

    @Override
    public boolean closeAfter() {
        return true;
    }
}
