package blocks;

public class LBlock extends Block {

    public LBlock(int initX, int initY, int orientation) {
        super(initX, initY, orientation, new int[] {255, 128, 0});
    }

    public LBlock() {
        super(new int[] {255, 128, 0});
    }

    public int[] getPositions() {
        return new int[] {super.getX(), super.getY(),
            super.getX(), super.getY() - 1,
            super.getX(), super.getY() + 1,
            super.getX() + 1, super.getY() + 1};
    }
}
