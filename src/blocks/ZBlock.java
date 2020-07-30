package blocks;

public class ZBlock extends Block {

    public ZBlock(int initX, int initY, int orientation) {
        super(initX, initY, orientation, new int[] {255, 0, 0});
    }

    public ZBlock() {
        super(new int[] {255, 0, 0});
    }

    public int[] getPositions() {
        return new int[] {super.getX(), super.getY(),
            super.getX(), super.getY() - 1,
            super.getX() - 1, super.getY() - 1,
            super.getX() + 1, super.getY()};
    }
}
