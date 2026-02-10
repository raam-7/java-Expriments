public class Book {

    private String title;
    private double price;
    private String ISBN;
    private String genre;
    private String author;

    // 1️⃣ Default constructor
    public Book() {
        this.title = "Not Assigned";
        this.author = "Unknown";
        this.genre = "General";
        this.ISBN = "N/A";
        this.price = 0.0;
    }

    // 2️⃣ Parameterized constructor
    public Book(String title, double price, String ISBN, String genre, String author) throws Exception {
        if (price < 0) {
            throw new Exception("Price cannot be negative");
        }
        this.title = title;
        this.price = price;
        this.ISBN = ISBN;
        this.genre = genre;
        this.author = author;
    }

    // 3️⃣ Copy constructor
    public Book(Book b) {
        this.title = b.title;
        this.price = b.price;
        this.ISBN = b.ISBN;
        this.genre = b.genre;
        this.author = b.author;
    }

    // Display method
    public void display() {
        System.out.println("Title  : " + title);
        System.out.println("Author : " + author);
        System.out.println("Genre  : " + genre);
        System.out.println("ISBN   : " + ISBN);
        System.out.println("Price  : " + price);
        System.out.println("------------------------");
    }
}