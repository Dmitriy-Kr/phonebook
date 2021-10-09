package phonebook.ui.menu;

import java.util.Scanner;

public class Menu {
    MenuAction[] actions;
    Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addAction(MenuAction action) {
        if (actions == null) {
            actions = new MenuAction[1];
            actions[0] = action;
        } else {
            MenuAction[] actionsNew = new MenuAction[actions.length + 1];
            for (int i = 0; i < actions.length; i++) {
                actionsNew[i] = actions[i];
            }
            actions = actionsNew;
            actions[actions.length - 1] = action;
        }
    }

    private void printMenu() {
        for (int i = 0; i < actions.length; i++) {
            System.out.println(i + 1 + ". " + actions[i].getName());
        }
    }

    private int getChoice() {
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return --choice;
        }
        scanner.nextLine();
        return -1;
    }

    public void run() {
        while (true) {
            System.out.println("Пункты меню:");
            printMenu();
            System.out.print("Сделайте свой выбор: ");
            int choice = getChoice();
            if (choice < 0 || choice > actions.length - 1) {
                System.out.println("Выберите пункт меню");
                continue;
            }
            actions[choice].doAction();
            if(actions[choice].closeAfter()) {
                break;
            }
        }

    }
}
