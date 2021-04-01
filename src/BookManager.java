

public class BookManager {

    static Book[] books = new Book[5];
    static int count = 0;

    public static int[] cellSizes = new int[]{7,20,15,15,15};

    public BookManager(){

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
        if (count >= 5) {
            System.out.println("Maximum Capacity");
            return;
        }
        books[count] = new Book(count + 1, bookDetails);
        incrementCount();
    }

    public static void borrowBook(int i){
        books[i - 1].setAvailable(false);
    }

    public static void returnBook(int i){
        books[i - 1].setAvailable(true);
    }

    private static void incrementCount(){
        count++;
    }
}
