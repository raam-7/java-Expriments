import java.util.ArrayList;

public class ArrayListOfBooks {

    public static void main(String[] args) {

        ArrayList<Book> bookList = new ArrayList<>();

        try {
            // Using default constructor
            Book b1 = new Book();

            // Using parameterized constructor
            Book b2 = new Book(
                    "Java Programming",
                    499.99,
                    "ISBN001",
                    "Education",
                    "James Gosling"
            );

            // Using copy constructor
            Book b3 = new Book(b2);

            bookList.add(b1);
            bookList.add(b2);
            bookList.add(b3);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Display all books
        System.out.println("Book Inventory:\n");
        for (Book b : bookList) {
            b.display();
        }
    }
}