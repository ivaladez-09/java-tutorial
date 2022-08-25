package DataStructures.Trees;

import java.util.*;

public class BreathFirstSearch<T> {
    public void traverse(Vertex<T> startVertex) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            if (!current.isVisited()) {
                current.setVisited(true);
                System.out.println(current);
                queue.addAll(current.getNeighbors());
            }
        }
    }

    public boolean areMirrored(Vertex<T> v1, Vertex<T> w1) {
        Queue<Vertex<T>> queue1 = new LinkedList<>();
        queue1.add(v1);

        Queue<Vertex<T>> queue2 = new LinkedList<>();
        queue2.add(w1);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            Vertex<T> currentV = queue1.poll();
            Vertex<T> currentW = queue2.poll();

            if (!currentV.isVisited() && !currentW.isVisited()) {
                currentV.setVisited(true);
                currentW.setVisited(true);
                System.out.println(currentV + " : " + currentW);

                if (currentV.getData() != currentW.getData()) {
                    return false;
                }

                queue1.addAll(currentV.getNeighbors());
                if (currentW.getNeighbors() != null && currentW.getNeighbors().size() == 2) {
                    queue2.addAll(Arrays.asList(currentW.getNeighbors().get(1), currentW.getNeighbors().get(0)));
                }
            }
        }

        return true;
    }
}
