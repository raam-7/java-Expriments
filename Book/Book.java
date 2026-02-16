class Book {

    private String title;
    private String author;
    private double price;
    private String genre;

    // Constructor with exception
    public Book(String title, String author, double price, String genre)
            throws InvalidPriceException {

        if (price < 0) {
            throw new InvalidPriceException("Price cannot be negative");
        }

        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Title: " + title +
               ", Author: " + author +
               ", Price: " + price +
               ", Genre: " + genre;
    }
}
