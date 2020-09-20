package Ai.pathfinding;

import Ai.pathfinding.generics.GraphNode;

import java.util.Objects;

public class Position implements GraphNode {
    private final String id;
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this(x + "" + y, x, y);
    }

    public Position(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
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

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
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
                this.y == position.y;
    }
}
