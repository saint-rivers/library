import java.util.Scanner;

/**
 * Stores user inputs to be passed as arguments to other objects.
 */
class UserInput extends ConsoleIn{

    static class BookInfo extends SuperBook {

        public BookInfo(String[] inputs) {
            super(inputs);
        }
    }

    static class LibraryInfo{
        String name;
        String address;

        public String getName() {
            return name.toUpperCase();
        }

        public String getAddress() {
            return address.toUpperCase();
        }

        public LibraryInfo() {
        }

        public LibraryInfo(String[] inputs) {
            this.name = inputs[0];
            this.address = inputs[1];
        }
    }
}

/**
 * Handles taking in user input via Python style console prompt.
 */
class ConsoleIn {

    private static final Scanner scanner;
    static {
        scanner = new Scanner(System.in);
    }

    public static int consoleIntegerInput(String message){
        System.out.print(message + ": ");
        String s = scanner.nextLine();
        if (Validator.isDigit(s)) {
            return Integer.parseInt(s);
        }
        return 0;
    }

    public static String consoleStringInput(String message){
        Console.printWithColon(message);
        return scanner.nextLine();
    }

    static String[] getPromptedInputs(String[] prompts) {
        String[] indexes = new String[prompts.length];
        for (int i = 0; i < prompts.length; i++) {
            indexes[i] = UserInput.consoleStringInput(prompts[i]);
        }
        return indexes;
    }
}
