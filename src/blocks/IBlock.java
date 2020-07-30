package blocks;

public class IBlock extends Block {

    public IBlock(int initX, int initY, int orientation) {
        super(initX, initY, orientation, new int[] {0, 255, 255});
    }

    public IBlock() {
        super(new int[] {0, 255, 255});
    }

    public int[] getPositions() {
        return new int[] {super.getX(), super.getY(),
            super.getX(), super.getY() - 1,
            super.getX(), super.getY() + 1,
            super.getX(), super.getY() + 2};
    }
}
