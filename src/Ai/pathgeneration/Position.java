package Ai.pathgeneration;

import Game.GameBoard;
import Game.blocks.Block;

public class Position {
    private final int x;
    private final int y;
    private final int orientation;

    public Position() {
        this.x = 0;
        this.y = 0;
        this.orientation = 0;
    }

    public Position(int x, int y, int orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;
        return this.x == position.x &&
                this.y == position.y &&
                this.orientation == position.orientation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.x;
        result = prime * result + this.y;
        result = prime * result + this.orientation;
        return result;
    }

    public boolean checkCollisionConditions(int xIncrement, int yIncrement) {
        int testX = this.x + xIncrement;
        int testY = this.y + yIncrement;

        if (testX >= 10 || testX < 0 || testY >= 20) {
            return false;
        }

        return true;
    }
}
