package Ai.pathgeneration;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class RoutePosition implements Comparable<RoutePosition> {
    private Position current;
    private LinkedList<Command> sequence;

    public RoutePosition(Position current) {
        this(current, new LinkedList<>());
    }

    public RoutePosition(Position current, LinkedList<Command> sequence) {
        this.current = current;
        this.sequence = sequence;
    }

    public Set<RoutePosition> getConnections() {
        Set<RoutePosition> connections = new HashSet<>();

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
        if (current.checkCollisionConditions(xIncrement, yIncrement)) {
            Position newPosition = new Position(current.getX() + xIncrement, current.getY() + yIncrement, current.getOrientation());
            LinkedList<Command> newSequence = new LinkedList<>(sequence);

            newSequence.add(command);
            return new RoutePosition(newPosition, newSequence);
        } else {
            return null;
        }
    }

    public boolean isEndPosition() {
        return !current.checkCollisionConditions(0, 1);
    }

    @Override
    public int compareTo(RoutePosition o) {
        return Integer.compare(this.sequence.size(), o.sequence.size());
    }

    public Position getCurrent() {
        return current;
    }

    public void setCurrent(Position current) {
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
