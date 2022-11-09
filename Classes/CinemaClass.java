// Done by Mingyang
public enum CinemaClass {
        STANDARD,
        GOLD,
        PLATINUM;

        public static boolean isValid(String s) {
                for (CinemaClass c: values()) {
                        if (s.toUpperCase().equals(c))
                                return true;
                }
                return false;
        }
}