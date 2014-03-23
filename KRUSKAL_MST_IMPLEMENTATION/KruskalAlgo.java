
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class KruskalAlgo {
    public static void main(String args[]) throws IOException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("/users/dany/downloads/suma.txt"));
        } catch (IOException ioe) {
            System.err.println("Could not open file");
        }
        int minimumCost = 0;
        Long start = System.currentTimeMillis();
        scanner.nextInt();
        int edgeCount = scanner.nextInt();
        ArrayList<KruskalEdge> edgeArrayList = new ArrayList<KruskalEdge>();
        for (int index = 1; index <= edgeCount; index++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            int weight = scanner.nextInt();
            edgeArrayList.add(new KruskalEdge(v, w, weight));
        }
        HashMap<Integer, Set<Integer>> mapForForest = new HashMap<Integer, Set<Integer>>();
        for (KruskalEdge edge : edgeArrayList) {
            if (null != edge) {
                Set<Integer> verticesSet = new HashSet<Integer>();
                verticesSet.add(edge.u);
                mapForForest.put(edge.u, verticesSet);
            }
        }
        Collections.sort(edgeArrayList);
        ArrayList<KruskalEdge> minSpanTree = new ArrayList<KruskalEdge>();
        while (!edgeArrayList.isEmpty()) {
            KruskalEdge edgeCheck = edgeArrayList.remove(0);

            Set<Integer> visitedFirstVertex = mapForForest.get(edgeCheck.u);
            Set<Integer> visitedSecondVertex = mapForForest.get(edgeCheck.v);
            if (visitedFirstVertex.equals(visitedSecondVertex))
                continue;
            minSpanTree.add(edgeCheck);
            visitedFirstVertex.addAll(visitedSecondVertex);
            for (Integer index : visitedFirstVertex) {
                mapForForest.put(index, visitedFirstVertex);
            }
            if (visitedFirstVertex.size() == edgeArrayList.size())
                break;
        }
        for (KruskalEdge ed : minSpanTree) {
            minimumCost = minimumCost + ed.weight;
        }
        Long end = System.currentTimeMillis();
        System.out.println(minimumCost);
        System.out.println("Total Time: " + (end - start));
    }
}
