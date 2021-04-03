

public class BookManager {

    static Book[] books = new Book[5];
    static int count = 0;
    private static Validator validator;

    public static int[] cellSizes = new int[]{7,20,15,15,15};

    public BookManager(){

    }

    public static void displayBookFromID(int id){
        for (Book book : books) {
            if (book.getId() == id) {
                book.displaySelf();
                break;
            }
        }
    }

    public static int getAvailableBookCount(){
        int count = 0;
        for (Book book : books) {
            if (book != null)
                if (book.isAvailable()) count++;
        }
        return count;
    }

    public static void addBook(UserInput.BookInfo bookDetails){
        if (count > 5) {
            System.out.println("Maximum Capacity");
            return;
        }
        books[count] = new Book(count + 1, bookDetails);
        incrementCount();
    }

    public static boolean borrowBook(int i){
        if (isInvalidBookRanges(i)) return false;

        Book book = books[i - 1];
        if (!book.isAvailable()) return false;

        book.setAvailable(false);
        return true;
    }

    public static boolean returnBook(int i){
        if (isInvalidBookRanges(i)) return false;

        Book book = books[i - 1];
        if (book.isAvailable()) return false;

        books[i - 1].setAvailable(true);
        return true;
    }

    private static boolean isInvalidBookRanges(int i) {
        if (count <= 0) return true;

        validator = new Validator(1, count);
        return validator.isNotInRange(i);
    }


    private static void incrementCount(){
        count++;
    }
}
