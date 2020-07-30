package blocks;

public class JBlock extends Block {

    public JBlock(float initX, float initY, int orientation) {
        super(initX, initY, orientation, new int[] {0, 0, 255});
    }

    public float[] getPositions() {
        return new float[] {super.getX() - (super.SQUARE_SIZE * 0.5f), super.getY() - (super.SQUARE_SIZE * 1.5f),
                super.getX() - (super.SQUARE_SIZE * 0.5f), super.getY() + (super.SQUARE_SIZE * 0.5f),
                super.getX() - (super.SQUARE_SIZE * 1.5f), super.getY() + (super.SQUARE_SIZE * 0.5f),
                super.getX() - (super.SQUARE_SIZE * 1.5f), super.getY() + (super.SQUARE_SIZE * 1.5f),
                super.getX() + (super.SQUARE_SIZE * 0.5f), super.getY() + (super.SQUARE_SIZE * 1.5f),
                super.getX() + (super.SQUARE_SIZE * 0.5f), super.getY() - (super.SQUARE_SIZE * 1.5f)};
    }
}
