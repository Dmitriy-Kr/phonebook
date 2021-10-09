package phonebook.ui.menu;

public interface MenuAction {
    void doAction();
    String getName();
    boolean closeAfter();
}
