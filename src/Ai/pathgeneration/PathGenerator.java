package Ai.pathgeneration;

import Game.blocks.Block;

import java.util.*;

public abstract class PathGenerator {

    /**
     * Generates the possible end positions and the sequence of commands to get there for a piece from a given point
     *
     * @param from The starting position
     * @return A map of Position to a Queue of Commands
     */
    public static Map<Block, Queue<Command>> generatePossibleSequences(Block from) {
        // Defines all finishedSequences where the piece has reached an end
        Map<Block, Queue<Command>> finishedSequences = new HashMap<>();
        // Defines all known nodes in the graph mapping a position to a route
        Set<Block> allNodes = new HashSet<>();
        // Defines the remaining paths to check
        Queue<RoutePosition> openSet = new PriorityQueue<>();

        // Creates the start position and route
        RoutePosition start = new RoutePosition(from);
        // Adds the start position and route to the remaining paths to check
        openSet.add(start);
        // Adds the adds the start position to all known nodes along with its route
        allNodes.add(from);

        // While there are paths to check
        while(!openSet.isEmpty()) {
            // Get the next possible path from the openSet
            RoutePosition next = openSet.poll();
            // Checks if the next path is an end position. If yes, adds it to finishedSequences
            if (next.isEndPosition()) {
                finishedSequences.put(next.getCurrent(), next.getSequence());
            }

            // Finds all connections to the next possible path and loops through them
            next.getConnections().forEach(connection -> {
                // For each connection, if a path to that node doesn't already exist adds the node to allNodes and the openSet
                if (!allNodes.contains(connection.getCurrent())) {
                    allNodes.add(connection.getCurrent());
                    openSet.add(connection);
                }
            });
        }

        // returns the final set of sequences
        return finishedSequences;
    }
}
