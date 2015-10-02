package example;

/**
 * Hello Gradle!
 */
public class App {

    public String getIntCountFromString() {

        String strValue = "Total for all 50 creatives";
        String str = strValue.trim();
        String digits = "";
        for (int i = 0; i < str.length(); i++) {
            char chars = str.charAt(i);
            if (Character.isDigit(chars))
                digits = digits + chars;
        }
        return digits;

    }
}