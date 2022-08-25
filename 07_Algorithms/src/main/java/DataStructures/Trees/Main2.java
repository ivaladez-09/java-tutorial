package DataStructures.Trees;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        Vertex<Integer> v0 = new Vertex<>(0);
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);
        Vertex<Integer> v5 = new Vertex<>(5);
        Vertex<Integer> v6 = new Vertex<>(6);
        Vertex<Integer> v7 = new Vertex<>(7);
        Vertex<Integer> v8 = new Vertex<>(8);
        Vertex<Integer> v9 = new Vertex<>(9);
        Vertex<Integer> v10 = new Vertex<>(10);
        Vertex<Integer> v11 = new Vertex<>(11);
        Vertex<Integer> v12 = new Vertex<>(12);
        Vertex<Integer> v13 = new Vertex<>(12);
        Vertex<Integer> v14 = new Vertex<>(14);

        v0.setNeighbors(List.of(v1, v2));
        v1.setNeighbors(List.of(v3, v4));
        v2.setNeighbors(List.of(v5, v6));
        v3.setNeighbors(List.of(v7, v8));
        v4.setNeighbors(List.of(v9, v10));
        v5.setNeighbors(List.of(v11, v12));
        v6.setNeighbors(List.of(v13, v14));


        Vertex<Integer> w0 = new Vertex<>(0);
        Vertex<Integer> w1 = new Vertex<>(1);
        Vertex<Integer> w2 = new Vertex<>(2);
        Vertex<Integer> w3 = new Vertex<>(3);
        Vertex<Integer> w4 = new Vertex<>(4);
        Vertex<Integer> w5 = new Vertex<>(5);
        Vertex<Integer> w6 = new Vertex<>(6);
        Vertex<Integer> w7 = new Vertex<>(7);
        Vertex<Integer> w8 = new Vertex<>(8);
        Vertex<Integer> w9 = new Vertex<>(9);
        Vertex<Integer> w10 = new Vertex<>(10);
        Vertex<Integer> w11 = new Vertex<>(11);
        Vertex<Integer> w12 = new Vertex<>(12);
        Vertex<Integer> w13 = new Vertex<>(12);
        Vertex<Integer> w14 = new Vertex<>(14);

        w0.setNeighbors(List.of(w2, w1));
        w1.setNeighbors(List.of(w4, w3));
        w2.setNeighbors(List.of(w6, w5));
        w3.setNeighbors(List.of(w8, w7));
        w4.setNeighbors(List.of(w10, w9));
        w5.setNeighbors(List.of(w12, w11));
        w6.setNeighbors(List.of(w14, w13));

        //new DepthFirstSearch<Integer>().traverse(v0);
        //new DepthFirstSearch<Integer>().traverseRecursively(v0);
        var bfs = new BreathFirstSearch<Integer>();
        var dfs = new DepthFirstSearch<Integer>();
        //System.out.println("Are trees mirrored? " + bfs.areMirrored(v0, w0));
        System.out.println("Are trees mirrored? " + dfs.areMirroredRecursive(v0, w0));
    }
}
