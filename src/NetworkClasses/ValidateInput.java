package NetworkClasses;

public class ValidateInput {

    public static boolean checkThreeOctets (String text) {
        // make a variable called "parts" that will hold multiple strings
        // parts[0] = "192" , parts[1] = "168"
        // if less than 3, return
        if (text == null || text.trim().isEmpty()){
            return false;
        }
        String[] parts = text.trim().split("\\.");
        return parts.length == 3;
    }
}
