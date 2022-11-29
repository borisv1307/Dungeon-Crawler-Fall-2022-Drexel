package tiles;

public enum TileType {

    PASSABLE(' '), NOT_PASSABLE('X'), PLAYER('P'), DOOR('D'), KEY('K'),
    COLLECTIBLE('C'), PORTAL('O'), ENEMY('E');


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

}

