package blocks;

public class TBlock extends Block {

    public TBlock(int initX, int initY, int orientation) {
        super(initX, initY, orientation, new int[] {178, 102, 255});
    }

    public TBlock() {
        super(new int[] {178, 102, 255});
    }

    public int[] getPositions() {
        return new int[] {super.getX(), super.getY(),
            super.getX(), super.getY() - 1,
            super.getX() + 1, super.getY(),
            super.getX() - 1, super.getY()};
    }
}
