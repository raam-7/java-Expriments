import java.util.ArrayList;

public class ArrayListOfBooks {

    public static void main(String[] args) {

        ArrayList<Book> bookList = new ArrayList<>();

        try {
            // Creating valid Book objects
            Book b1 = new Book("The Alchemist", "Paulo Coelho", 499.0, "Fiction");
            Book b2 = new Book("Clean Code", "Robert Martin", 850.0, "Programming");
            Book b3 = new Book("Harry Potter", "J.K. Rowling", 650.0, "Fiction");

            // Adding to ArrayList
            bookList.add(b1);
            bookList.add(b2);
            bookList.add(b3);

            // Creating book with negative price (Exception case)
            Book b4 = new Book("Invalid Book", "Unknown", -200.0, "Drama");
            bookList.add(b4);

        } catch (InvalidPriceException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        // Display all books
        System.out.println("\nAll Books:");
        double totalPrice = 0;

        for (Book book : bookList) {
            System.out.println(book);
            totalPrice += book.getPrice();
        }

        // Calculate and print average price
        if (!bookList.isEmpty()) {
            double average = totalPrice / bookList.size();
            System.out.println("\nAverage Price of Books: " + average);
        }

        // Print books of genre "Fiction" using forEach()
        System.out.println("\nFiction Books:");
        bookList.forEach(book -> {
            if (book.getGenre().equalsIgnoreCase("Fiction")) {
                System.out.println(book);
            }
        });
    }
}
