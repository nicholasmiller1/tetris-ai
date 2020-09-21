package Ai.pathfinding;

import Ai.pathfinding.generics.GraphNode;

import java.util.Objects;

public class Position implements GraphNode {
    private final String id;
    private final int x;
    private final int y;
    private final int orientation;

    public Position(int x, int y, int orientation) {
        this(x + "" + y + "" + orientation, x, y, orientation);
    }

    public Position(String id, int x, int y, int orientation) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    @Override
    public String getId() {
        return id;
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
                "id='" + id + '\'' +
                ", x=" + x +
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

    public boolean isValid() {
        return true;
    }
}
