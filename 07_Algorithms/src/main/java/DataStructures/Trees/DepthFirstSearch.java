package DataStructures.Trees;

import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstSearch<T> {
    public void traverse(Vertex<T> startVertex) {
        Deque<Vertex<T>> stack = new LinkedList<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Vertex<T> current = stack.pop();

            if (!current.isVisited()) {
                current.setVisited(true);
                System.out.println(current);

                current.getNeighbors().forEach(stack::push);
            }
        }
    }

    public void traverseRecursively(Vertex<T> vertex) {
        vertex.setVisited(true);
        System.out.println(vertex);
        vertex.getNeighbors().stream()
                .filter(neighbor -> !neighbor.isVisited())
                .forEach(this::traverseRecursively);
    }

    public boolean areMirroredRecursive(Vertex<T> v1, Vertex<T> w1) {
        v1.setVisited(true);
        w1.setVisited(true);
        System.out.println(v1 + " : " + w1);

        if (v1.getData() != w1.getData()) {
            return false;
        }

        if (v1.getNeighbors() != null && w1.getNeighbors() != null & v1.getNeighbors().size() == 2 && w1.getNeighbors().size() == 2) {
            return areMirroredRecursive(v1.getNeighbors().get(0), w1.getNeighbors().get(1)) &&
                    areMirroredRecursive(v1.getNeighbors().get(1), w1.getNeighbors().get(0));
        }

        return true;
    }
}
