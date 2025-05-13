package helpers;

import java.util.UUID;

public class RandomGenerator {
    public static String generateRandomString(String prefix) {
        String randomUUID = UUID.randomUUID().toString();
        String randomString = prefix + "_" + randomUUID.replaceAll("-", "");
        return randomString;
    }

}
