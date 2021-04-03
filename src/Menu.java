import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

final class ImportMenu extends Menu{

    String[] bookData;
    private final static String header = "ADD BOOK INFO";
    private final static String[] prompts = {
            "=> Enter Book's Name",
            "=> Enter Book Author",
            "=> Enter Published Year"
    };

    public ImportMenu() {
        super(header, prompts);
    }

    @Override
    protected void displayMenu() {
        this.setSuccessfulMessage("\n\tBook added successfully.\n");
        super.displayMenu();
        System.out.println(this.successfulMessage);
    }

    @Override
    protected void menuInteract() {
        Console.printWithPaddingAndColon("=> Book ID");
        System.out.println(BookManager.count + 1);
        bookData = UserInput.getPromptedInputs(prompts);
    }

    UserInput.BookInfo getBookInfo(){
        if (bookData != null) return new UserInput.BookInfo(bookData);
        return null;
    }
}

final class BookInfoMenu extends Menu{

    private boolean isDisplayingAll = true;

    public void setDisplayingAll(boolean displayingAll) {
        this.isDisplayingAll = displayingAll;
    }

    public BookInfoMenu() {
        setHeader(super.header);
        buildFailedMessage();
    }

    public void buildFailedMessage() {
        this.failedMessage = "\t\tNo Books Available\n";
    }

    void displayMenu(Book[] books){
        displayDynamicTableTitle();
        if (books[0] == null) {
            System.out.println(failedMessage);
            return;
        }
        displayBookInfoTable();
        Console.promptEnterKey();
    }

    private void displayDynamicTableTitle() {
        if (isDisplayingAll) super.header = "ALL BOOKS INFO";
        else super.header = "AVAILABLE BOOKS INFO";
        Console.printHeaderStyle(super.header);
    }

    public void displayBookInfo(Book book, int[] cellSizes){

        Table.printVerticalDoubleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[0], book.getId());

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[1], book.getTitle());

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[2], book.getAuthor());

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[3], book.getPublishedYear());

        Table.printVerticalSingleDivider(" ");
        Console.printWithSpecificPadding(cellSizes[4], book.displayAvailable());

        Table.printVerticalDoubleDivider();
        System.out.println();
    }

    public void displayTableHeader(int[] cellSizes){

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

    public void displayBookInfoTable() {

        Table.printTableTopBorder(BookManager.cellSizes);
        displayTableHeader(BookManager.cellSizes);
        Table.printTableMiddleBorder(BookManager.cellSizes);

        for (int i = 0; i < BookManager.count; i++) {
            if (isDisplayingAll) {
                printBookInTable(i);
                continue;
            }
            if (BookManager.books[i].isAvailable()) {
                printBookInTable(i);
            }
        }
        Table.printTableBottomBorder(BookManager.cellSizes);
    }

    private void printBookInTable(int i) {
        displayBookInfo(BookManager.books[i], BookManager.cellSizes);
        if (isDisplayingAll && i < BookManager.count - 1)
            Table.printTableMiddleBorder(BookManager.cellSizes);
        else if (!isDisplayingAll && i < BookManager.getAvailableBookCount() - 1)
            Table.printTableMiddleBorder(BookManager.cellSizes);
    }
}

final class MainMenu extends Menu{

    private static final String[] mainPrompt = {
            "1- Add Book",
            "2- Show All Books",
            "3- Show Available Books",
            "4- Borrow Book",
            "5- Return Book",
            "6- Exit"
    };

    @Override
    protected void displayMenu() {
        super.displayMenu();
        System.out.println(super.footer);
    }

    public void buildHeader(UserInput.LibraryInfo userInput) {
        super.header = userInput.getName() + ", " + userInput.getAddress();
    }

    public MainMenu(UserInput.LibraryInfo info) {
        super(mainPrompt);
        buildHeader(info);
        setInputPrompt("Choose option" + LibraryController.mainValidator.toString());
        setLoopingMenu(true);
    }
}

final class SetUpMenu extends Menu{
    private static final String setupHeader = "SET UP LIBRARY";
    private static final String[] setupPrompts = {
            "=> Enter Library's Name",
            "=> Enter Library's Address"
    };
    UserInput.LibraryInfo userInput;

    public SetUpMenu() {
        super(setupHeader, setupPrompts);
        super.setLoopingMenu(false);
    }

    @Override
    public void menuInteract() {
        String[] inputArray = UserInput.getPromptedInputs(this.prompts);
        this.userInput = new UserInput.LibraryInfo(inputArray);
        buildSuccessMessage(this.userInput);
    }

    @Override
    public void displayMenu() {
        super.displayMenu();
        System.out.println(this.successfulMessage);
        System.out.println();
    }

    public void buildSuccessMessage(UserInput.LibraryInfo userInput) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        ZonedDateTime now = ZonedDateTime.now();
        this.successfulMessage = "\"" + userInput.name + "\" Library is already created in "
                + "\"" + userInput.address + "\" address successfully on "
                + dtf.format(now);
    }

    public UserInput.LibraryInfo getLibraryInfo() {
        if (userInput == null) return new UserInput.LibraryInfo();
        return userInput;
    }
}

abstract class LibraryInteractionMenu extends Menu{
    protected int bookID = -1;
    protected String inputIDPrompt;

    public int getBookIDInput() {
        return bookID;
    }

    public void setInputIDPrompt(String inputIDPrompt) {
        this.inputIDPrompt = inputIDPrompt;
    }

    public void getBookIDFromUser(){
        bookID = UserInput.consoleIntegerInput(inputIDPrompt);
    }

    @Override
    protected void menuInteract() {
        getBookIDFromUser();
    }

    public void displayResultMessage(boolean successful) {
        if (successful) {
            BookManager.displayBookFromID(bookID);
            System.out.println(super.successfulMessage);
            return;
        }
        System.out.println(super.failedMessage);
    }

    abstract public void buildSuccessMessage();
    abstract public void buildFailedMessage();

    @Override
    protected void displayMenu() {
        super.displayMenu();
        this.buildFailedMessage();
        this.buildSuccessMessage();
    }
}

final class BorrowMenu extends LibraryInteractionMenu{

    String header = "BORROW BOOK INFO";
    String inputPrompt = "=> Enter Book ID to Borrow";

    public BorrowMenu() {
        super.header = header;
        setInputIDPrompt(inputPrompt);
    }

    @Override
    public void buildSuccessMessage() {
        super.successfulMessage = "is borrowed successfully.\n";
    }

    @Override
    public void buildFailedMessage() {
        super.failedMessage = "Book ID: "+ this.bookID +" does not Exist…\n";
    }

}

final class ReturnMenu extends LibraryInteractionMenu{

    String header = "RETURN BOOK INFO";
    String inputPrompt = "=> Enter Book ID to Return";

    public ReturnMenu() {
        super.header = header;
        setInputIDPrompt(inputPrompt);
    }

    @Override
    public void buildSuccessMessage() {
        super.successfulMessage = "is returned successfully.\n";
    }

    @Override
    public void buildFailedMessage() {
        super.failedMessage = "Book ID: "+ super.bookID +" failed to return...";
    }
}


class Menu{

    String header;
    String[] prompts;
    String footer = "---------------------------------";
    String successfulMessage;
    String failedMessage;
    protected boolean isLoopingMenu;
    String inputPrompt;

    public void buildHeader(UserInput.LibraryInfo userInput){}
    public void buildSuccessMessage(UserInput userInput){}
    public void buildFailedMessage(UserInput userInput){}
    public void displayResultMessage(){}

    public void setHeader(String header) {
        this.header = header;
    }

    public void setInputPrompt(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    public void setLoopingMenu(boolean loopingMenu) {
        isLoopingMenu = loopingMenu;
    }

    public void setSuccessfulMessage(String successfulMessage) {
        this.successfulMessage = successfulMessage;
    }

    public void setFailedMessage(String failedMessage) {
        this.failedMessage = failedMessage;
    }

    public Menu(){}

    public Menu(String[] prompts) {
        this.prompts = prompts;
    }

    public void displayHeader(){
        Console.printHeaderStyle(this.header);
    }

    public Menu(String header, String[] prompts) {
        this.header = header;
        this.prompts = prompts;
    }

    protected void displayMenu(){
        displayHeader();
        menuInteract();
    }

    protected void menuInteract(){
        for (String line: prompts){
            Console.printWithPaddingAndNewLine(line);
        }
    }
}

