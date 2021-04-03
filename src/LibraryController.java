public class LibraryController {

    private static UserInput.LibraryInfo libInfo;
    static Validator mainValidator = new Validator(1,6);

    static void setupHotel(){
        SetUpMenu menu = new SetUpMenu();
        menu.displayMenu();
        libInfo = menu.getLibraryInfo();
    }

    static void runMainMenu(){
        MainMenu menu = new MainMenu(libInfo);
        MenuManager menuManager = new MenuManager(menu, BookManager.books);
        menuManager.executeLoop();
    }
}

class MenuManager {

    Menu menu;
    Book[] books;
    BookInfoMenu bookInfo = new BookInfoMenu();

    public MenuManager(Menu menu, Book[] books) {
        this.menu = menu;
        this.books = books;
    }

    public void executeLoop(){
        final int EXIT_CODE = 6;
        int choice;
        do{
            menu.displayMenu();

            choice = UserInput.consoleIntegerInput(menu.inputPrompt);
            System.out.println();

            if (LibraryController.mainValidator.isNotInRange(choice))
                continue;
            this.switchUserOptions(choice);

        } while(choice != EXIT_CODE);

        System.out.println("(^-^) Good Bye! (^-^)");
    }

    private void switchUserOptions(int choice) {
        switch (choice){
            case 1:
                importBookToLibrary();
                break;
            case 2:
                bookInfo.displayMenu(BookManager.books);
                break;
            case 3:
                displayOnlyBooksAvailableToBorrow();
                break;
            case 4:
                borrowBookFromLibrary();
                break;
            case 5:
                returnBookToLibrary();
                break;
            default:
                break;
        }
    }

    private void returnBookToLibrary() {
        boolean isSuccessful;
        int searchID;
        ReturnMenu returnMenu = new ReturnMenu();
        returnMenu.displayMenu();
        searchID = returnMenu.getBookIDInput();
        isSuccessful = BookManager.returnBook(searchID);
        returnMenu.displayResultMessage(isSuccessful);
    }

    private void borrowBookFromLibrary() {
        boolean isSuccessful;
        int searchID;
        BorrowMenu borrowMenu = new BorrowMenu();
        borrowMenu.displayMenu();
        searchID = borrowMenu.getBookIDInput();
        isSuccessful = BookManager.borrowBook(searchID);
        borrowMenu.displayResultMessage(isSuccessful);
    }

    private void displayOnlyBooksAvailableToBorrow() {
        bookInfo.setDisplayingAll(false);
        bookInfo.displayMenu(BookManager.books);
        bookInfo.setDisplayingAll(true);
    }

    private void importBookToLibrary() {
        if (BookManager.isFull()){
            BookManager.displayTableIsFull();
            System.out.println();
            return;
        }
        ImportMenu importMenu = new ImportMenu();
        importMenu.displayMenu();
        UserInput.BookInfo userInput = importMenu.getBookInfo();

        if (userInput != null)
            if (userInput.containsValidEntries()) BookManager.addBook(userInput);
    }
}
