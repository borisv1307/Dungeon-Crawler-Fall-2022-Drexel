package tiles;

public enum TileType {

    PASSABLE(' '), NOT_PASSABLE('X'), PLAYER('P'), RIGHT_SHOOTER('R'), LEFT_SHOOTER('L'), UP_SHOOTER('U'),
    DOWN_SHOOTER('D');

    static final String INVALID_CHARACTER_PROVIDED_MESSAGE = "Invalid character provided: ";
    private final char asChar;

    private TileType(char asChar) {
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

    public static boolean isShooter(TileType tileType) {
        return tileType == RIGHT_SHOOTER || tileType == LEFT_SHOOTER || tileType == UP_SHOOTER || tileType == DOWN_SHOOTER;
    }
}
