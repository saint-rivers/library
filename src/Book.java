public class Book extends SuperBook {

    private int id;
    private Boolean isAvailable = true;

    public Book(int id, UserInput.BookInfo userInput) {
        super(userInput);
        setId(id);
    }

    public void displaySelf(){
        Console.printWithPaddingAndColon("Book ID");
        System.out.println(this.getId());
        Console.printWithPaddingAndColon("Book Title");
        System.out.println(this.getTitle());
        Console.printWithPaddingAndColon("Book Author");
        System.out.println(this.getAuthor());
        Console.printWithPaddingAndColon("Published Year");
        System.out.println(this.getPublishedYear());
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String displayAvailable() {
        if (isAvailable) return "Available";
        return "Not Available";
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public boolean isEmpty(){
        return this.id > 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

abstract class SuperBook {
    private String title;
    private String author;
    private String publishedYear;

    public SuperBook() {
        setTitle("TITLE");
        setAuthor("AUTHOR");
        setPublishedYear("PUBLISHED_DATE");
    }

    public SuperBook(UserInput.BookInfo userInput) {
        setTitle(userInput.getTitle());
        setAuthor(userInput.getAuthor());
        setPublishedYear(userInput.getPublishedYear());
    }

    public SuperBook(String[] inputs) {
        setTitle(inputs[0]);
        setAuthor(inputs[1]);
        setPublishedYear(inputs[2]);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear;
    }
}

