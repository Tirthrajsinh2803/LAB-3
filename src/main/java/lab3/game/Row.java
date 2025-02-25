package lab3.game;

public enum Row {
    TOP, MIDDLE, BOTTOM;

    public static Row parse(String input) {
        input = input.trim().toLowerCase();
        return switch (input) {
            case "1", "t" -> TOP;
            case "2", "m", "c" -> MIDDLE;
            case "3", "b" -> BOTTOM;
            default -> null; // Return null for invalid input
        };
    }
}