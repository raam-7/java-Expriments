import java.util.ArrayList;
public class ArraylistEx {
public static void main(String[] args) {
    ArrayList<Book> inventory = new ArrayList<Book>();

    try {
        inventory.add(new Book());
        inventory.add(new Book("Clean Code", "Robert C. Martin", "Software"));
        inventory.add(new Book("Effective Java", "45.50", "978-0134685991", "Joshua Bloch", "Programming"));
        inventory.add(new Book("Head First Java", "39.99", "978-0596009205", "Kathy Sierra", "Programming"));
    } catch (IllegalArgumentException ex) {
        System.out.println("Error adding book: " + ex.getMessage());
    }

    System.out.println("All books in inventory:");
    for (Book b : inventory) {
        System.out.println(b);
    }

    String searchIsbn = "978-0134685991";
    Book found = findByIsbn(inventory, searchIsbn);
    if (found != null) {
        System.out.println("Found book: " + found);
        try {
            found.updatePrice("49.99");
            System.out.println("Updated price: " + found);
        } catch (IllegalArgumentException ex) {
            System.out.println("Price update failed: " + ex.getMessage());
        }
    } else {
        System.out.println("Book with ISBN " + searchIsbn + " not found.");
    }

    boolean removed = removeByIsbn(inventory, "NA");
    System.out.println("Removed default book (ISBN NA): " + removed);
    System.out.println("Inventory size: " + inventory.size());
} 

private static Book findByIsbn(ArrayList<Book> inventory, String isbn) {
    for (Book b : inventory) {
        if (b.ISBN.equals(isbn)) {
            return b;
        }
    }
    return null;
}

private static boolean removeByIsbn(ArrayList<Book> inventory, String isbn) {
    for (int i = 0; i < inventory.size(); i++) {
        if (inventory.get(i).ISBN.equals(isbn)) {
            inventory.remove(i);
            return true;
        }
    }
    return false;
}
}
