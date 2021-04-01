public class Book extends SuperBook {

    private int id;
    private boolean isAvailable = true;

    public Book(int id, UserInput.BookInfo userInput) {
        super(userInput);
        setId(id);
    }

    public void displayBookInfo(){
        System.out.println(getId());
        System.out.println(getTitle());
        System.out.println(getAuthor());
        System.out.println(getPublishedDate());
        System.out.println(isAvailable);
        System.out.println();
    }

    public boolean isAvailable() {
        return isAvailable;
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
    private String publishedDate;

    public SuperBook(UserInput.BookInfo userInput) {
        setTitle(userInput.getTitle());
        setAuthor(userInput.getAuthor());
        setPublishedDate(userInput.getPublishedDate());
    }

    public SuperBook(String[] inputs) {
        setTitle(inputs[0]);
        setAuthor(inputs[1]);
        setPublishedDate(inputs[2]);
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

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}