package Ai.pathfinding.generics;

public interface Scorer<T extends GraphNode> {
    double computeCost(T from, T to);
}
