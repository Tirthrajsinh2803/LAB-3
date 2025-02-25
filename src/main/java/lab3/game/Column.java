package lab3.game;

public enum Column {
    LEFT, MIDDLE, RIGHT;

    public static Column parse(String input) {
        input = input.trim().toLowerCase();
        return switch (input) {
            case "1", "l" -> LEFT;
            case "2", "m", "c" -> MIDDLE;
            case "3", "r" -> RIGHT;
            default -> null; // Return null for invalid input
        };
    }
}