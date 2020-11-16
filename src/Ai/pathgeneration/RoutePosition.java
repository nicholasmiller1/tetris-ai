package Ai.pathgeneration;

import Game.blocks.Block;

import java.util.*;

public class RoutePosition implements Comparable<RoutePosition> {
    private Block current;
    private LinkedList<Command> sequence;

    public RoutePosition(Block current) {
        this(current, new LinkedList<>());
    }

    public RoutePosition(Block current, LinkedList<Command> sequence) {
        this.current = current;
        this.sequence = sequence;
    }

    public Set<RoutePosition> getConnections() {
        Set<RoutePosition> connections = new LinkedHashSet<>();

        Command lastCommand = sequence.isEmpty() ? null : sequence.getLast();

        Command.getPossibleCommands(lastCommand).forEach(command -> {
            RoutePosition connectedNode = this.applyCommand(command);
            if (connectedNode != null) {
                connections.add(connectedNode);
            }
        });

        return connections;
    }

    private RoutePosition applyCommand(Command command) {
        int xIncrement = command.getIncrements()[0];
        int yIncrement = command.getIncrements()[1];
        int rotation = command.getIncrements()[2];

        Block newBlock = new Block(current);
        LinkedList<Command> newSequence = new LinkedList<>(sequence);
        newSequence.add(command);

        RoutePosition newRoutePosition = null;
        if (rotation == 0) {
            newRoutePosition = newBlock.move(xIncrement, yIncrement) ? new RoutePosition(newBlock, newSequence) : null;
        } else if (rotation == 1) {
            newRoutePosition = newBlock.rotateClockwise() ? new RoutePosition(newBlock, newSequence) : null;
        } else if (rotation == -1) {
            newRoutePosition = newBlock.rotateCounterClockwise() ? new RoutePosition(newBlock, newSequence) : null;
        }

        return newRoutePosition;
    }

    public boolean isEndPosition() {
        return !current.checkCollisions(0, 1);
    }

    @Override
    public int compareTo(RoutePosition o) {
        return Integer.compare(this.sequence.size(), o.sequence.size());
    }

    public Block getCurrent() {
        return current;
    }

    public void setCurrent(Block current) {
        this.current = current;
    }

    public Queue<Command> getSequence() {
        return sequence;
    }

    public void setSequence(LinkedList<Command> sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return current.toString() + " | " + sequence.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return current.equals(obj);
    }
}
