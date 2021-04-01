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
            books[i].displayBookInfo(BookManager.cellSizes);
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
    public static String VERTICAL_DOUBLE = "\u2551";
    public static String VERTICAL_SINGLE = "\u2502";

    public static void printVerticalDoubleDivider(){
        System.out.print(VERTICAL_DOUBLE);
    }

    public static void printVerticalSingleDivider(){
        System.out.print(VERTICAL_SINGLE);
    }

    public static void printVerticalDoubleDivider(String s){
        System.out.print(VERTICAL_DOUBLE + s);
    }

    public static void printVerticalSingleDivider(String s){
        System.out.print(VERTICAL_SINGLE + s);
    }


    public static String TOP_LEFT_CORNER = "\u2554";
    public static String STRAIGHT_HORIZONTAL_BORDER = "\u2550";
    public static String STRAIGHT_CROSSING_BORDER = "\u2564";
    public static String TOP_RIGHT_CORNER = "\u2557";

    private static void printCellTopBorder(int length){
        for (int i = 0; i < length + 1; i++) {
            System.out.print(STRAIGHT_HORIZONTAL_BORDER);
        }
    }

    public static void printTableTopBorder(int[] arr){
        System.out.print(Table.TOP_LEFT_CORNER);
        for (int i = 0; i < arr.length; i++) {
            Table.printCellTopBorder(arr[i]);
            if (i < arr.length - 1) {

                System.out.print(STRAIGHT_CROSSING_BORDER);
            }
        }
        System.out.print(TOP_RIGHT_CORNER);
        System.out.println();
    }

    public static String LEFT_DIVIDER = "\u255F";
    public static String BOTTOM_DIVIDER = "\u2500";
    public static String CROSS_DIVIDER = "\u253C";
    public static String RIGHT_DIVIDER = "\u2562";

    private static void printCellBottomBorder(int length){
        for (int i = 0; i < length + 1; i++) {
            System.out.print(BOTTOM_DIVIDER);
        }
    }

    public static void printTableMiddleBorder(int[] arr){
        System.out.print(Table.LEFT_DIVIDER);
        for (int i = 0; i < arr.length; i++) {
            Table.printCellBottomBorder(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(CROSS_DIVIDER);
            }
        }
        System.out.print(RIGHT_DIVIDER);
        System.out.println();
    }

    public static String BOTTOM_LEFT_CORNER = "\u255A";
    public static String BOTTOM_RIGHT_CORNER = "\u255D";
    public static String BOTTOM_CROSS = "\u2567";

    public static void printTableBottomBorder(int[] arr){
        System.out.print(Table.BOTTOM_LEFT_CORNER);
        for (int i = 0; i < arr.length; i++) {
            Table.printCellTopBorder(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(BOTTOM_CROSS);
            }
        }
        System.out.print(BOTTOM_RIGHT_CORNER);
        System.out.println();
    }
}

class Console {

    public static void printWithSpecificPadding(int padSize, Object obj){
        String s = "%-" + padSize + "s";
        System.out.printf(s, obj);
    }

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