import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    public String name;
    public String phoneNumber;
    public int[] marks;
    public transient long ignoredField;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static Comparator<Contact> compareByName() {
        Comparator<Contact> comparator;

        // create an instance of an anonymous class
        comparator = (contact1, contact2) -> {
            int compare = contact1.getName().compareTo(contact2.getName());
            return compare;
        };
        return comparator;
    }

    public static Comparator<Contact> compareByPhoneNumber() {
        Comparator<Contact> comparator;

        // create an instance of an anonymous class
        comparator = new Comparator<>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                int compare = contact1.getPhoneNumber().compareTo(contact2.getPhoneNumber());
                return compare;
            }
        };
        return comparator;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Contact)) {
            return false;
        }

        Contact person = (Contact) obj;
        return Objects.equals(person.name, this.name) &&
                Objects.equals(person.phoneNumber, this.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

}

