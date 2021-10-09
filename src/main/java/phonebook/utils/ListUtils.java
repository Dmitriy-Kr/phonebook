package phonebook.utils;


import phonebook.pojo.entity.Contact;
import phonebook.pojo.entity.ContactType;
import phonebook.services.ContactsList;

import java.util.Locale;
import java.util.function.*;

public class ListUtils {
    public static void main(String[] args) {
        ContactsList phoneBook = new ContactsList();

        phoneBook.add(new Contact("1",  "Иван", ContactType.PHONE,"+380975882032"));
        phoneBook.add(new Contact("2", "Артём", ContactType.PHONE, "+380965882032"));
        phoneBook.add(new Contact("3", "Игорь", ContactType.PHONE, "+380955582032"));
        phoneBook.add(new Contact("4", "Максим", ContactType.PHONE, "+380955882932"));
        phoneBook.add(new Contact("5", "Денис", ContactType.PHONE, "+380958112932"));

        System.out.println("------------------------reduce----------------------------");

        System.out.println(reduce(phoneBook, new StringBuilder("Список имён: "), (x, y)->x.append(y.getName()).append(" ")));

        System.out.println("------------------------forEach----------------------------");

        forEach(phoneBook, x-> System.out.println("Работник ".concat(x.getName())));

        System.out.println("------------------------anyMatch---------------------------");

        System.out.println(anyMatch(phoneBook, x->"Максим".equals(x.getName())));

        System.out.println("------------------------allMatch----------------------------");

        System.out.println(allMatch(phoneBook, x->x.getName().length() != 0));

        System.out.println("------------------------map---------------------------------");

        forEach(map(phoneBook, x->new Contact(x.getId(), x.getName().toUpperCase(Locale.ROOT), x.getContactType(), "__________")),
                x-> System.out.println(x.getId() + " " + x.getName() + " " + x.getContactType() + " " + x.getValue()));
    }

    public static ContactsList filter(ContactsList list, Predicate<Contact> predicate) {
        ContactsList result = new ContactsList();
        for (int i = 0; i < list.size(); i++) {
            if (predicate.test(list.get(i))) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public static <T> T reduce(ContactsList list, T initValue, BiFunction<T, Contact, T> biFunction) {
        for (int i = 0; i < list.size(); i++) {
            initValue = biFunction.apply(initValue, list.get(i));
        }
        return initValue;
    }

    public static void forEach(ContactsList list, Consumer<Contact> consumer) {
        for (int i = 0; i < list.size(); i++) {
            consumer.accept(list.get(i));
        }
    }

    public static boolean anyMatch(ContactsList list, Predicate<Contact> predicate) {
        for (int i = 0; i < list.size(); i++) {
            if (predicate.test(list.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean allMatch(ContactsList list, Predicate<Contact> predicate) {
        for (int i = 0; i < list.size(); i++) {
            if (!predicate.test(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static ContactsList map(ContactsList list, Function <Contact, Contact> function) {
        ContactsList result = new ContactsList();
        for (int i = 0; i < list.size(); i++) {
            result.add(function.apply(list.get(i)));
        }
        return result;
    }
}
