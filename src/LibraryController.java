public class LibraryController {

    private static UserInput.LibraryInfo libInfo;

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
            this.switchUserOptions(choice);

        } while(choice != EXIT_CODE);
    }

    void switchUserOptions(int choice) {
        switch (choice){
            case 1:
                ImportMenu importMenu = new ImportMenu();
                importMenu.menuInteract();
                UserInput.BookInfo userInput = importMenu.getBookInfo();
                BookManager.addBook(userInput);
                break;
            case 2:
                BookInfoMenu bookMenu = new BookInfoMenu();
                bookMenu.showAllBooks(books);
                break;
            case 3:
                break;
            case 4:
                int bookIDBorrow = UserInput.consoleIntegerInput("Enter book id");
                BookManager.borrowBook(bookIDBorrow);

                break;
            case 5:
                int bookIDReturn = UserInput.consoleIntegerInput("Enter book id");
                BookManager.returnBook(bookIDReturn);
                break;
            default:
                break;
        }
    }
}
