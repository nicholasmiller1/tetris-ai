package blocks;

public class OBlock extends Block {

    public OBlock() {
        super(new int[] {4, -1, 4, 0, 5, -1, 5, 0}, new int[] {255, 255, 0});
    }

    @Override
    public void rotateClockwise() {
        return;
    }

    @Override
    public void rotateCounterClockwise() {
        return;
    }
}
