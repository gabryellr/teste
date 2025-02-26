package demo;

public class AA {
    public String checkValue(int value) {
        if (isaBoolean(value) && isaBoolean1(value) || isaBoolean2(value)) {
            return "Positive";
        } else {
            return "Non-positive";
        }
    }

    private static boolean isaBoolean2(final int value) {
        return value > 0;
    }

    private static boolean isaBoolean1(final int value) {
        return value < 0;
    }

    private static boolean isaBoolean(final int value) {
        return value > 0;
    }
}
