package Ai.pathfinding;

import Ai.pathfinding.generics.Graph;
import Ai.pathfinding.generics.RouteFinder;
import Game.GameBoard;

import java.util.*;

public class Pathfinder {
    // IMPORTANT!!!! - If I'm already generating all possible paths, why not just use the CodingBullet method and just use it to generate all possible positions
//    private static final Map<String, >

    private final RouteFinder<Position> routeFinder;

    public Pathfinder() {
        Graph<Position> graph = new Graph<>(new HashSet<Position>(), new TreeMap<String, Set<String>>());
        routeFinder = new RouteFinder<Position>(graph, new ManhattanScorer(), new ManhattanScorer());
    }

    private Graph<Position> generateGraph(Position from, int[][] gameBoard) {
//        Set<Position> nodes = generateNodes();
//        Graph<Position> graph = new Graph<>(nodes);
//
//        Map<String, Set<String>> connections = generateConnections(graph);
//        graph.setConnections(connections);
//
//        return graph;

        Set<Position> nodes = new HashSet<>();
        Map<String, Set<String>> connections = new TreeMap<>();
        Queue<Position> connectionQueue = new PriorityQueue<>();

        connectionQueue.add(from);
        // Possible Actions: move left, move right, move down, turn left, turn right
        while (!connectionQueue.isEmpty()) {
            Position currentPosition = connectionQueue.poll();
            nodes.add(currentPosition);


        }

        return new Graph(nodes, connections);
    }

//    private Set<Position> generateNodes() {
//        Set<Position> nodes = new HashSet<>();
//
//        for (int r = 0; r < gameBoard.length; r++) {
//            for (int c = 0; c < gameBoard[0].length; c++) {
//                if (gameBoard[r][c] == 0) {
//                    Position p = new Position(c + "" + r, c, r, 0);
//                    nodes.add(p);
//                }
//            }
//        }
//
//        return nodes;
//    }
//
//    private Map<String, Set<String>> generateConnections(Graph<Position> graph) {
//        Map<String, Set<String>> connections = new TreeMap<>();
//
//        for (Position n : graph.getNodes()) {
//            Set<String> nConnections = new TreeSet<>();
//            String leftId = (n.getX() - 1) + "" + n.getY();
//            String rightId = (n.getX() + 1) + "" + n.getY();
//            String downId = n.getX() + "" + (n.getY() + 1);
//
//            if (graph.getNode(leftId) != null) {
//                nConnections.add(leftId);
//            }
//            if (graph.getNode(rightId) != null) {
//                nConnections.add(rightId);
//            }
//            if(graph.getNode(downId) != null) {
//                nConnections.add(downId);
//            }
//
//            connections.put(n.getId(), nConnections);
//        }
//
//        return connections;
//    }

    public List<Position> findRoute(Position from, Position to, int[][] gameBoard) {
        routeFinder.setGraph(generateGraph(from, gameBoard));
        return routeFinder.findRoute(from, to);
    }

    @Override
    public String toString() {
        return "Pathfinder{" +
                "routeFinder=" + routeFinder +
                '}';
    }
}
