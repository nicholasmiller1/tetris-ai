package blocks;

public class TBlock extends Block {

    public TBlock(float initX, float initY, int orientation) {
        super(initX, initY, orientation, new int[] {178, 102, 255});
    }

    public float[] getPositions() {
        return new float[] {super.getX() - (super.SQUARE_SIZE * 0.5f), super.getY() - (super.SQUARE_SIZE * 1.5f),
                super.getX() - (super.SQUARE_SIZE * 0.5f), super.getY() - (super.SQUARE_SIZE * 0.5f),
                super.getX() - (super.SQUARE_SIZE * 1.5f), super.getY() - (super.SQUARE_SIZE * 0.5f),
                super.getX() - (super.SQUARE_SIZE * 1.5f), super.getY() + (super.SQUARE_SIZE * 0.5f),
                super.getX() + (super.SQUARE_SIZE * 1.5f), super.getY() + (super.SQUARE_SIZE * 0.5f),
                super.getX() + (super.SQUARE_SIZE * 1.5f), super.getY() - (super.SQUARE_SIZE * 0.5f),
                super.getX() + (super.SQUARE_SIZE * 0.5f), super.getY() - (super.SQUARE_SIZE * 0.5f),
                super.getX() + (super.SQUARE_SIZE * 0.5f), super.getY() - (super.SQUARE_SIZE * 1.5f)};
    }
}
