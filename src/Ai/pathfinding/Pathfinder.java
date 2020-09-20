package Ai.pathfinding;

import Ai.pathfinding.generics.Graph;
import Ai.pathfinding.generics.RouteFinder;
import Game.GameBoard;

import java.util.*;

public class Pathfinder {
    private final RouteFinder<Position> routeFinder;

    public Pathfinder() {
        Graph<Position> graph = generateGraph(GameBoard.gameBoard);

        routeFinder = new RouteFinder<>(graph, new ManhattanScorer(), new ManhattanScorer());
    }

    private Graph<Position> generateGraph(int[][] board) {
        Set<Position> nodes = generateNodes(board);
        Graph<Position> graph = new Graph<>(nodes);

        Map<String, Set<String>> connections = generateConnections(graph);
        graph.setConnections(connections);

        return graph;
    }

    private Set<Position> generateNodes(int[][] board) {
        Set<Position> nodes = new HashSet<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 0) {
                    Position p = new Position(c + "" + r, c, r);
                    nodes.add(p);
                }
            }
        }

        return nodes;
    }

    private Map<String, Set<String>> generateConnections(Graph<Position> graph) {
        Map<String, Set<String>> connections = new TreeMap<>();

        for (Position n : graph.getNodes()) {
            Set<String> nConnections = new TreeSet<>();
            String leftId = (n.getX() - 1) + "" + n.getY();
            String rightId = (n.getX() + 1) + "" + n.getY();
            String downId = n.getX() + "" + (n.getY() + 1);

            if (graph.getNode(leftId) != null) {
                nConnections.add(leftId);
            }
            if (graph.getNode(rightId) != null) {
                nConnections.add(rightId);
            }
            if(graph.getNode(downId) != null) {
                nConnections.add(downId);
            }

            connections.put(n.getId(), nConnections);
        }

        return connections;
    }

    public void updateGraph() {
        Graph<Position> graph = routeFinder.getGraph();
        graph.setNodes(generateNodes(GameBoard.gameBoard));
        graph.setConnections(generateConnections(graph));
    }

    public List<Position> findRoute(Position from, Position to) {
        return routeFinder.findRoute(from, to);
    }

    @Override
    public String toString() {
        return "Pathfinder{" +
                "routeFinder=" + routeFinder +
                '}';
    }
}
