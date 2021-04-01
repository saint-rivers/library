import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final class ImportMenu extends Menu{

    String[] bookData;
    private final static String header = "========= ADD BOOK INFO =========";
    private final static String[] prompts = {
            "=> Enter Book's Name",
            "=> Enter Book Author",
            "=> Enter Published Year"
    };

    public ImportMenu() {
        super(header, prompts);
    }

    @Override
    protected void menuInteract() {
        Console.printWithColon("=> Book ID");
        System.out.println(BookManager.count + 1);
        bookData = UserInput.getPromptedInputs(prompts);
    }

    UserInput.BookInfo getBookInfo(){
        if (bookData != null) return new UserInput.BookInfo(bookData);
        return null;
    }
}

final class BookInfoMenu extends Menu{

    private static final String header = "========= All Books Info =========";

    public BookInfoMenu() {
        setHeader(header);
        buildFailedMessage();
    }

    public void buildFailedMessage() {
        this.failedMessage = "\t\tNo Books Available";
    }

    void showAllBooks(Book[] books){
        if (books[0] == null) {
            System.out.println(failedMessage);
            return;
        }
        for (int i = 0; i < BookManager.count; i++) {
            books[i].displayBookInfo();
        }
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
        super.header = "========= " + userInput.getName() + ", " + userInput.getAddress() + " =========";
    }

    public MainMenu(UserInput.LibraryInfo info) {
        super(mainPrompt);
        buildHeader(info);
        setInputPrompt("Choose option dolphin");
        setLoopingMenu(true);
    }
}

final class SetUpMenu extends Menu{
    private static final String setupHeader = "========= SET UP LIBRARY =========";
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
    }

    public void buildSuccessMessage(UserInput.LibraryInfo userInput) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.successfulMessage = "\"" + userInput.name + "\" Library is already created in "
                + "\"" + userInput.address + "\" address successfully on "
                + dtf.format(now);
    }

    public UserInput.LibraryInfo getLibraryInfo() {
        if (userInput == null) return new UserInput.LibraryInfo();
        return userInput;
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

    public Menu(String header, String[] prompts) {
        this.header = header;
        this.prompts = prompts;
    }

    protected void displayMenu(){
        System.out.println(this.header);
        menuInteract();
    }

    protected void menuInteract(){
        for (String line: prompts){
            Console.printWithPaddingAndNewLine(line);
        }
    }
}

class Table extends Console{
    private final char TOP_LEFT_BORDER = 201;
    private final char TOP_RIGHT_BORDER = 187;
    private final char BOTTOM_LEFT_BORDER = 200;
    private final char BOTTOM_RIGHT_BORDER = 188;
    private final char T_BORDER = 188;

    private final int CELL_WIDTH = 12;
}

class Console {

    public static void printWithColon(Object obj){
        printWithPadding(obj);
        System.out.print(": ");
    }

    public static void printWithPaddingAndNewLine(Object obj){
        System.out.printf("%-30s\n", obj);
    }

    public static void printWithPadding(Object obj){
        System.out.printf("%-30s", obj);
    }
}