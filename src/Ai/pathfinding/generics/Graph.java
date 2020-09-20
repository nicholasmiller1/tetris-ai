package Ai.pathfinding.generics;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T extends GraphNode> {
    private Set<T> nodes;
    private Map<String, Set<String>> connections;

    public Graph(Set<T> nodes) {
        this.nodes = nodes;
        this.connections = null;
    }

    public Graph(Set<T> nodes, Map<String, Set<String>> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    public T getNode(String id) {
        return nodes.stream()
                .filter(node -> node.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Set<T> getConnections(T node) {
        return connections.get(node.getId()).stream()
                .map(this::getNode)
                .collect(Collectors.toSet());
    }

    public Set<T> getNodes() {
        return nodes;
    }

    public void setNodes(Set<T> nodes) {
        this.nodes = nodes;
    }

    public Map<String, Set<String>> getConnections() {
        return connections;
    }

    public void setConnections(Map<String, Set<String>> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                ", connections=" + connections +
                '}';
    }
}