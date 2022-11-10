// Done by Mingyang
public enum CinemaClass {
        STANDARD,
        GOLD,
        PLATINUM;

        public static boolean isValid(String s) {
                for (CinemaClass c: values()) {
                        if (s.equals(c.toString()))
                                return true;
                }
                return false;
        }
}