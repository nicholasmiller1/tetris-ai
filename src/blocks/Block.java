package blocks;

import main.Main;
import java.util.Random;

public abstract class Block {

    private int[] coordinates;
    private int[] color;

    public Block(int[] coordinates, int[] color) {
        this.coordinates = coordinates;
        this.color = color;
    }

    public void fall() {
        for (int i = 1; i < coordinates.length; i += 2) {
            coordinates[i]++;
        }
    }

    public void moveRight() {
        for (int i = 0; i < coordinates.length; i += 2) {
            coordinates[i]++;
        }
    }

    public void moveLeft() {
        for (int i = 0; i < coordinates.length; i += 2) {
            coordinates[i]--;
        }
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public int[] getColor() {
        return color;
    }

    public void rotateCounterClockwise() {
        int n = 2;
        int xOffset = coordinates[2] - 1;
        int yOffset = coordinates[3] - 1;

        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i] - xOffset;
            coordinates[i] = coordinates[i+1] - yOffset;
            coordinates[i+1] = n - x;

            coordinates[i] += xOffset;
            coordinates[i+1] += yOffset;
        }
    }

    public void rotateClockwise() {
        int n = 2;
        int xOffset = coordinates[2] - 1;
        int yOffset = coordinates[3] - 1;

        for (int i = 0; i < coordinates.length; i += 2) {
            int y = coordinates[i+1] - yOffset;
            coordinates[i+1] = coordinates[i] - xOffset;
            coordinates[i] = n - y;

            coordinates[i] += xOffset;
            coordinates[i+1] += yOffset;
        }
    }
}
