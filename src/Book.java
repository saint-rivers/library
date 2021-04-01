public class Book extends SuperBook {

    private int id;
    private boolean isAvailable = true;

    public Book(int id, UserInput.BookInfo userInput) {
        super(userInput);
        setId(id);
    }

    public Book() {
        // only testing
        super(new String[]{"Kafka on the shore","Murakami","1952"});
    }

    public void displayBookInfo(int[] cellSizes){

        Table.printVerticalDoubleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[0], getId());

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[1], getTitle());

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[2], getAuthor());

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[3], getPublishedDate());

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[4], isAvailable);

        Table.printVerticalDoubleDivider();
        System.out.println();
    }

    public void displayHeader(int[] cellSizes){

        Table.printVerticalDoubleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[0], "ID");

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[1], "TITLE");

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[2], "AUTHOR");

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[3], "PUBLISHED_DATE");

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[4], "AVAILABLE");

        Table.printVerticalDoubleDivider();
        System.out.println();
    }

    public static void main(String[] args) {
        Book book = new Book();

        Table.printTableTopBorder(BookManager.cellSizes);
        book.displayHeader(BookManager.cellSizes);
        Table.printTableMiddleBorder(BookManager.cellSizes);

        book.displayBookInfo(BookManager.cellSizes);
        Table.printTableMiddleBorder(BookManager.cellSizes);

        book.displayBookInfo(BookManager.cellSizes);
        Table.printTableBottomBorder(BookManager.cellSizes);
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

    public SuperBook() {
        setTitle("TITLE");
        setAuthor("AUTHOR");
        setPublishedDate("PUBLISHED_DATE");
    }

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

