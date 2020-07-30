package blocks;

public class JBlock extends Block {

    public JBlock(int initX, int initY, int orientation) {
        super(initX, initY, orientation, new int[] {0, 0, 255});
    }

    public JBlock() {
        super(new int[] {0, 0, 255});
    }

    public int[] getPositions() {
        return new int[] {super.getX(), super.getY(),
            super.getX(), super.getY() - 1,
            super.getX(), super.getY() + 1,
            super.getX() - 1, super.getY() + 1};
    }
}
