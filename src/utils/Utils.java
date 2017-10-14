package utils;

public class Utils {
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0||str.equals("null"))
            return true;
        else
            return false;
    }
}
