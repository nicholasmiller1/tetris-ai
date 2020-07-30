package blocks;

public class OBlock extends Block {

    public OBlock(int initX, int initY, int orientation) {
        super(initX, initY, orientation, new int[] {255, 255, 0});
    }

    public OBlock() {
        super(new int[] {255, 255, 0});
    }

    public int[] getPositions() {
        return new int[] {super.getX(), super.getY(),
            super.getX() + 1, super.getY(),
            super.getX(), super.getY() + 1,
            super.getX() + 1, super.getY() + 1};
    }
}
