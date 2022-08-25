package DataStructures.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex<T> {
    private final T data;
    private boolean visited;
    private List<Vertex<T>> neighbors = new ArrayList<>();

    public Vertex(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Vertex<T>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Vertex<T>> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return visited == vertex.visited && Objects.equals(data, vertex.data) && Objects.equals(neighbors, vertex.neighbors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, visited, neighbors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        neighbors.forEach(neighbor -> sb.append(neighbor.data + ","));

        return "Vertex{" +
                "data=" + data +
                ", visited=" + visited +
                ", neighbors=" + sb.toString() +
                '}';
    }
}
