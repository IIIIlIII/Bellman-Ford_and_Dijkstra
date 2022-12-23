package BellmanFord;

import java.util.HashMap;
import java.util.Scanner;

public class mainBF {

    public static class edge {
        int u;
        int v;
        public edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
        public String toString() {
            return this.u + " " + this.v;
        }
    }
    public static class Node {
        int val;
        int dist;
        String path;
        public Node(int val, int dist, String path) {
            this.val = val;
            this.dist = dist;
            this.path = path;
        }
        public String toString() {
            return "Distance of " + this.val + " from source is " + this.dist + " and path followed is " + this.path;
        }

    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int nodes = scn.nextInt();
        int[][] graph = new int[nodes + 1][nodes + 1];
        int numEdges = scn.nextInt();
        for (int edge = 0; edge < numEdges; edge++) {
            int u = scn.nextInt(), v = scn.nextInt(), w = scn.nextInt();
            graph[u][v] = w;
        }
        long start = System.currentTimeMillis();
        bellmanford(scn.nextInt(), nodes, numEdges, graph);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("execution time " + elapsed+" ms");
    }
    public static edge[] getEdges(int numEdges, int[][] graph) {
        edge[] rv = new edge[numEdges];
        int idx = 0;
        for (int u = 1; u < graph.length && idx < rv.length; u++) {
            for (int v = 1; v < graph[u].length && idx < rv.length; v++) {
                rv[idx] = new edge(u, v);
                idx = graph[u][v] != 0 ? idx + 1 : idx;
            }
        }
        return rv;
    }

    public static void bellmanford(int src, int nodes, int edges, int[][] graph) {
        //используем hashmap для хранения узлов каждой вершины (имя вершины, узел) будет ключом, парой значений

        HashMap<Integer, Node> nodesMap = new HashMap<>();
        for (int i = 1; i < graph.length; i++) {
            /*
             * инициализировать кратчайшее расстояние каждой вершины равным Infinity,
             * так как для этой вершины кратчайшее расстояние еще предстоит вычислить,
             * и инициализировать пустой путь. Но если вершина сама является исходной вершиной,
             * то кратчайшее расстояние для нее будет равно 0,
             * и путь будет инициализирован именем вершины.
             */
            nodesMap.put(i, new Node(i, i == src ? 0 : (int) 1e9 + 1, i == src ?
                    Integer.toString(i) : ""));
        }
        /* внешний цикл будет выполняться для вершин - 1 раз */
        for (int var = 1; var <= nodes - 1; var++) {
            /* выполнение внутреннего цикла по набору ребер,
               возвращаемых функцией getEdges */
            for (edge e : getEdges(edges, graph)) {
                Node u = nodesMap.get(e.u);
                Node v = nodesMap.get(e.v);
                // основное условие для обновления кратчайшего расстояния любого узла, как упоминалось выше.
                if (v.dist > u.dist + graph[u.val][v.val]) {
                    v.dist = u.dist + graph[u.val][v.val];
                    v.path = u.path + "->" + Integer.toString(v.val);
                }
            }
        }
        /*
         * еще один цикл в случайном наборе ребер, чтобы определить,
         * есть ли какой-либо отрицательный цикл или нет
         */
        for (edge e : getEdges(edges, graph)) {
            Node u = nodesMap.get(e.u);
            Node v = nodesMap.get(e.v);
            /*
             * если мы все еще можем найти короткое расстояние,
             * это просто означает, что наверняка есть отрицательный цикл,
             * и, следовательно, мы возвращаемся из функции,
             * поскольку кратчайшее расстояние для каждой вершины от источника не может быть найдено для такого графа,
             * поскольку мы можем получить еще более короткое расстояние зациклившись еще раз в этом отрицательном цикле.
             */
            if (v.dist > u.dist + graph[u.val][v.val]) {
                System.out.println("Negative Cycle Detected");
                return;
            }
        }
        for (int node : nodesMap.keySet()) {
            System.out.println(nodesMap.get(node));
        }
    }
}
/*8 15
1 2 2
1 2 3
1 4 1
1 6 1
2 3 1
2 6 4
2 8 5
3 8 2
4 7 3
5 7 3
5 8 2
6 8 6
7 4 3
7 5 3
8 5 2
1*/