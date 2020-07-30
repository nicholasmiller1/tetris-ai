package blocks;

public abstract class Block {

    private float x;
    private float y;
    private int orientation;
    private int[] color;

    public static final int SQUARE_SIZE = 10;

    public Block(float initX, float initY, int orientation, int[] color) {
        this.x = initX;
        this.y = initY;
        this.orientation = orientation;
        this.color = color;
    }

    public void move() {
        y += 1;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    abstract public float[] getPositions();
}
