package blocks;

public class IBlock extends Block {

    public IBlock(float initX, float initY, int orientation) {
        super(initX, initY, orientation, new int[] {0, 255, 255});
    }

    public float[] getPositions() {
        return new float[] {super.getX() - super.SQUARE_SIZE, super.getY() - (super.SQUARE_SIZE * 2),
                super.getX() - super.SQUARE_SIZE, super.getY() + (super.SQUARE_SIZE * 2),
                super.getX(), super.getY() + (super.SQUARE_SIZE * 2),
                super.getX(), super.getY() - (super.SQUARE_SIZE * 2)};
    }
}
