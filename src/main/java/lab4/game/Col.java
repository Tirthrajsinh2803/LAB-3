package lab4.game;

public enum Col {
    Left, Middle, Right;

    public static Col from(String str) {
        return switch (str.toLowerCase()) {
            case "1", "l" -> Left;
            case "2", "m", "c" -> Middle;
            case "3", "r" -> Right;
            default -> throw new IllegalArgumentException("Invalid column: " + str);
        };
    }
}