package tiles;

public enum TileType {

    PASSABLE_HIDDEN('1'), PASSABLE_HARMFUL_HIDDEN('2'), PASSABLE_HELPFUL_HIDDEN('3'), NOT_PASSABLE('X'), PLAYER('P'),
    PASSABLE(' '), PASSABLE_HARMFUL('-'), PASSABLE_HELPFUL('+');

    static final String INVALID_CHARACTER_PROVIDED_MESSAGE = "Invalid character provided: ";
    private final char asChar;

    TileType(char asChar) {
        this.asChar = asChar;
    }

    public static TileType getTileTypeByChar(final char ch) {
        for (TileType type : TileType.values()) {
            if (type.asChar == ch) {
                return type;
            }
        }

        throw new IllegalArgumentException(INVALID_CHARACTER_PROVIDED_MESSAGE + ch);
    }
}
