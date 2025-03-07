package lab4.game;

public enum Row {
    Top, Middle, Bottom;

    public static Row from(String str) {
        return switch (str.toLowerCase()) {
            case "1", "t" -> Top;
            case "2", "m", "c" -> Middle;
            case "3", "b" -> Bottom;
            default -> throw new IllegalArgumentException("Invalid row: " + str);
        };
    }
}