package phonebook.menu;

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
