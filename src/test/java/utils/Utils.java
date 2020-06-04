package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static int randomInt (int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static int randomInt (int max) {
        return ThreadLocalRandom.current().nextInt(0, max + 1);
    }

    public static String nowStamp() {
        String patternNowStamp = "yyyyMMddHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternNowStamp);
        return simpleDateFormat.format(new Date());
    }
}
