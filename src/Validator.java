import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    Integer a;
    Integer b;

    @Override
    public String toString() {
        return "(" + a + "-" + b + ")";
    }

    public Validator(int a, int b) {
        this.a = a;
        this.b = b;
    }


    /**
     * Check whether user input is within range of the class.
     * The range is set with the constructor.
     * @param input The number the user input in the console.
     * @return Returns true if input is within range.
     */
    boolean isNotInRange(Integer input){
        if (isMissingBounds()) return true;
        String search = "[" + this.a + "-" + this.b + "]";
        return !Pattern.matches(search, input.toString());
    }

    static boolean isDigits(String input){
        String search = "[0-9]{1,2}";
        return Pattern.matches(search, input);
    }

    static boolean isValidYear(String input){
        String search = "[0-9]{1,4}";
        return Pattern.matches(search, input);
    }

    /**
     * Check if local variables are null.
     * @return Returns true if they are null.
     */
    private boolean isMissingBounds(){
        return this.a == null || this.b == null;
    }

    /**
     * Check if the input is a valid person's name.
     * @param name The name to check.
     * @return True if name is valid for use.
     */
    static boolean isValidEntry(String name){
        if (name == null) return false;
        Pattern pattern = Pattern.compile("[A-Z]*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }


    /**
     * Check if name matches each other.
     * @param searchPattern Word in array you want to match.
     * @param keyword User inputted word to search for.
     * @return  True if a match is found in the array.
     */
    static boolean isMatchingName(String searchPattern, String keyword){
        if (searchPattern == null || keyword == null) return false;
        Pattern pattern = Pattern.compile(searchPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(keyword);
        return matcher.matches();
    }
}
