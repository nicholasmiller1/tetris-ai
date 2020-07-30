package blocks;

public class OBlock extends Block {

    public OBlock(float initX, float initY, int orientation) {
        super(initX, initY, orientation, new int[] {255, 255, 0});
    }

    public float[] getPositions() {
        return new float[] {super.getX() - super.SQUARE_SIZE, super.getY() - super.SQUARE_SIZE,
                super.getX() - super.SQUARE_SIZE, super.getY() + super.SQUARE_SIZE,
                super.getX() + super.SQUARE_SIZE, super.getY() + super.SQUARE_SIZE,
                super.getX() + super.SQUARE_SIZE, super.getY() - super.SQUARE_SIZE};
    }
}
