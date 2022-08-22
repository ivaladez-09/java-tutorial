package DataStructures.Trees;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Vertex<Integer> v0 = new Vertex<>(0);
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);
        Vertex<Integer> v5 = new Vertex<>(5);
        Vertex<Integer> v6 = new Vertex<>(6);

        v0.setNeighbors(List.of(v1, v5, v6));
        v1.setNeighbors(List.of(v3, v4, v5));
        v4.setNeighbors(List.of(v2, v6));
        v6.setNeighbors(List.of(v0));

        new DepthFirstSearch<Integer>().traverse(v0);

        v0.setVisited(false);
        v1.setVisited(false);
        v2.setVisited(false);
        v3.setVisited(false);
        v4.setVisited(false);
        v5.setVisited(false);
        v6.setVisited(false);
        System.out.println("\n");

        new DepthFirstSearch<Integer>().traverseRecursively(v0);
    }
}
