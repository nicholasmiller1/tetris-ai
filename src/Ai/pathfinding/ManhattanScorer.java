package Ai.pathfinding;

import Ai.pathfinding.generics.Scorer;

public class ManhattanScorer implements Scorer<Position> {

    @Override
    public double computeCost(Position from, Position to) {
        return Math.abs(from.getX() - to.getX()) +
                Math.abs(from.getY() - to.getY());
    }
}
