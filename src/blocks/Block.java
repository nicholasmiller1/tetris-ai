package blocks;

import main.Main;
import java.util.Random;

public abstract class Block {

    private int x;
    private int y;
    private int orientation;
    private int[] color;

    public Block(int initX, int initY, int orientation, int[] color) {
        this.x = initX;
        this.y = initY;
        this.orientation = orientation;
        this.color = color;
    }

    public Block(int[] color) {
        Random rng = new Random();
        this.x = rng.nextInt(((Main.STAGE_BORDERS[2] - Main.STAGE_BORDERS[0]) / 10) - 6) + 3;
        this.y = 0;
        this.orientation = rng.nextInt(4);
        this.color = color;
    }

    public void move() {
        y += 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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

    abstract public int[] getPositions();
}
