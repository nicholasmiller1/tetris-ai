package blocks;

public class ZBlock extends Block {

    public ZBlock(float initX, float initY, int orientation) {
        super(initX, initY, orientation, new int[] {255, 0, 0});
    }

    public float[] getPositions() {
        return new float[] {super.getX() - (super.SQUARE_SIZE * 1.5f), super.getY() - (super.SQUARE_SIZE * 1.5f),
                super.getX() - (super.SQUARE_SIZE * 1.5f), super.getY() - (super.SQUARE_SIZE * 0.5f),
                super.getX() - (super.SQUARE_SIZE * 0.5f), super.getY() - (super.SQUARE_SIZE * 0.5f),
                super.getX() - (super.SQUARE_SIZE * 0.5f), super.getY() + (super.SQUARE_SIZE * 0.5f),
                super.getX() + (super.SQUARE_SIZE * 1.5f), super.getY() + (super.SQUARE_SIZE * 0.5f),
                super.getX() + (super.SQUARE_SIZE * 1.5f), super.getY() - (super.SQUARE_SIZE * 0.5f),
                super.getX() + (super.SQUARE_SIZE * 0.5f), super.getY() - (super.SQUARE_SIZE * 0.5f),
                super.getX() + (super.SQUARE_SIZE * 0.5f), super.getY() - (super.SQUARE_SIZE * 1.5f)};
    }
}
