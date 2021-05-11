package az.baku.card.util;

import java.security.SecureRandom;

public class RandomUtil {

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = LETTERS + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static String generateCardNumber(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(NUMBER.length());
            char randomChar = NUMBER.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String generateAccountNumber(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char randomChar = DATA_FOR_RANDOM_STRING.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
