import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        // write binary data in a file
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("data.bin"))) {
            out.writeInt(2123);
            out.writeFloat(123.456f);
            out.writeUTF("hello и привет");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // read binary data from the file
        try (DataInputStream in = new DataInputStream(new FileInputStream("data.bin"))) {
            System.out.println(in.readInt());
            System.out.println(in.readFloat());
            System.out.println(in.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }

        Collection<String> stringCollection = new ArrayList<>();
        stringCollection.add("Hello");
        stringCollection.add("world");
        stringCollection.add("and");
        stringCollection.add("all!");

        // write binary data in a file
        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream("data.bin1"))) {
            stream.writeInt(stringCollection.size());
            for (String s : stringCollection) {
                stream.writeUTF(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read binary data from the file
        Collection<String> stringCollection1 = new ArrayList<>();
        try (DataInputStream stream = new DataInputStream(new FileInputStream("data.bin1"))) {
            int count = stream.readInt();
            int count1 = 0;
            while (count1 < count) {
                stringCollection1.add(stream.readUTF());
                count1++;
            }
            System.out.println();
            for (String s : stringCollection1) {
                System.out.print(s + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collection<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Serhii", "+380967629700"));
        contacts.add(new Contact("Andrii", "+111111111111"));
        contacts.add(new Contact("Alex", "+222222222222"));
        contacts.add(new Contact("Ihor", "+333333333333"));
        contacts.add(new Contact("Ben", "+444444444444"));
        contacts.add(new Contact("Alex", "+555555555555"));

        // write Collection's data in a file
        try (DataOutputStream outPut = new DataOutputStream(new FileOutputStream("data.collection.bin"))) {
            outPut.writeInt(contacts.size());
            for (Contact c : contacts) {
                outPut.writeUTF(c.getName());
                outPut.writeUTF(c.getPhoneNumber());
            }
        }

        // read Collection's data from the file
        Collection<Contact> contacts1 = new ArrayList<>();
        try (DataInputStream inPut = new DataInputStream(new FileInputStream("data.collection.bin"))) {

            int count = inPut.readInt();
            int count1 = 0;
            while (count1 < count) {
                String name = inPut.readUTF();
                String phoneNumber = inPut.readUTF();
                Contact contact = new Contact(name, phoneNumber);
                contacts1.add(contact);
                count1++;
            }
            System.out.println();
            System.out.println();
            for (Contact c : contacts1) {
                System.out.println(c.getName() + " " + c.getPhoneNumber());
            }
        }

        Collection<Contact> contacts2 = new ArrayList<>();
        contacts2.add(new Contact("Serhii", "+380967629700"));
        contacts2.add(new Contact("Andrii", "+111111111111"));
        contacts2.add(new Contact("Alex", "+222222222222"));
        contacts2.add(new Contact("Ihor", "+333333333333"));
        contacts2.add(new Contact("Ben", "+444444444444"));
        contacts2.add(new Contact("Alex", "+555555555555"));

        // Serialization data
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Serializable"))) {
            out.writeObject(contacts2);
        }

        // Deserialization data
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Serializable"))) {
            Collection<Contact> contacts3 = (Collection<Contact>) in.readObject();
            System.out.println();
            for (Contact c : contacts3) {
                System.out.println(c.getName() + " " + c.getPhoneNumber());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Print and counting all files in directories
        System.out.println();
        String path = "C:\\Users\\User\\Downloads";
        try (Stream<Path> filePath = Files.walk(Paths.get(path))) {
            long fileCount = filePath
                    .filter(Files::isRegularFile)
                    .peek(System.out::println)
                    .count();
            System.out.println("\n" + "File count is: " + fileCount);
        }
    }
}
