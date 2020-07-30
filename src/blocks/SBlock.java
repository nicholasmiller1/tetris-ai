package blocks;

public class SBlock extends Block {

    public SBlock(int initX, int initY, int orientation) {
        super(initX, initY, orientation, new int[] {0, 255, 0});
    }

    public SBlock() {
        super(new int[] {0, 255, 0});
    }

    public int[] getPositions() {
        return new int[] {super.getX(), super.getY(),
            super.getX(), super.getY() - 1,
            super.getX() + 1, super.getY() - 1,
            super.getX() - 1, super.getY()};
    }
}
