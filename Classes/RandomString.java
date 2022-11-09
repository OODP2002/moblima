// Credit: https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomString {
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase(Locale.ROOT);

    public static final String digits = "0123456789";
    public static final String alphanum = upper + lower + digits;

    private final Random random;



    /**
     * Create an alphanumeric string generator.
     */
    public RandomString(int length, Random random) {
        this(length, random, alphanum);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
}
