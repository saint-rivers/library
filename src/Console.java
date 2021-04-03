import java.util.Scanner;

public class Console {

    public static void printHeaderStyle(Object obj){
        for (int i = 0; i < 9; i++) {
            System.out.print("=");
        }
        System.out.print(" " + obj + " ");
        for (int i = 0; i < 9; i++) {
            System.out.print("=");
        }
        System.out.print("\n");
    }

    public static void printWithSpecificPadding(int padSize, Object obj){
        String s = "%-" + padSize + "s";
        System.out.printf(s, obj);
    }

    public static void printWithPaddingAndColon(Object obj){
        printWithPadding(obj);
        System.out.print(": ");
    }

    public static void printWithPaddingAndNewLine(Object obj){
        System.out.printf("%-30s\n", obj);
    }

    public static void printWithPadding(Object obj){
        System.out.printf("%-30s", obj);
    }


    private static final Scanner scanner;
    static {
        scanner = new Scanner(System.in);
    }

    public static int consoleIntegerInput(String message){
        System.out.print(message + ": ");
        String s = scanner.nextLine();
        if (Validator.isDigits(s)) {
            return Integer.parseInt(s);
        }
        return 0;
    }

    public static String consoleStringInput(String message){
        printWithPaddingAndColon(message);
        return scanner.nextLine();
    }

    static String[] getPromptedInputs(String[] prompts) {
        String[] indexes = new String[prompts.length];
        for (int i = 0; i < prompts.length; i++) {
            indexes[i] = UserInput.consoleStringInput(prompts[i]);
        }
        return indexes;
    }

    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}

class Table extends Console {
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
        printHorizontalLine(arr, STRAIGHT_CROSSING_BORDER, TOP_RIGHT_CORNER);
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
        printHorizontalLine(arr, BOTTOM_CROSS, BOTTOM_RIGHT_CORNER);
    }

    private static void printHorizontalLine(int[] arr, String bottomCross, String bottomRightCorner) {
        for (int i = 0; i < arr.length; i++) {
            Table.printCellTopBorder(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(bottomCross);
            }
        }
        System.out.print(bottomRightCorner);
        System.out.println();
    }
}
