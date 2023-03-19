package uk.onefamily.utils;

import java.util.Random;

public class Utils {

    private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWZ";

    private static String numbers = "0123456789";

    public static String getStringOf(int numOfChars) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int len = 0; len < numOfChars; len++) {
            int charat = random.nextInt(characters.length());
            stringBuffer.append(characters.charAt(charat));
        }
        return stringBuffer.toString();
    }

    public static String getNumbersOf(int numOfChars) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int len = 0; len < numOfChars; len++) {
            int charat = random.nextInt(numbers.length());
            stringBuffer.append(numbers.charAt(charat));
        }
        return stringBuffer.toString();
    }
}
