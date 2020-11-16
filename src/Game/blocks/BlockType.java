package Game.blocks;

public enum BlockType {
    IBLOCK(1),
    JBLOCK(2),
    LBLOCK(3),
    OBLOCK(4),
    SBLOCK(5),
    TBLOCK(6),
    ZBLOCK(7);

    public final int code;

    private BlockType(int code) {
        this.code = code;
    }
}
