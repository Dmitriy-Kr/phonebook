package phonebook.menu;

public interface MenuAction {
    void doAction();
    String getName();
    boolean closeAfter();
}
