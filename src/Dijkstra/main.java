package Dijkstra;

public class main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");
        Vertex v8 = new Vertex("8");

        v1.addNeighbour(new Edge(1,v1,v2));
        v1.addNeighbour(new Edge(2,v1,v3));
        v1.addNeighbour(new Edge(1,v1,v6));
        v2.addNeighbour(new Edge(2,v2,v3));
        v2.addNeighbour(new Edge(3,v2,v6));
        v2.addNeighbour(new Edge(3,v2,v5));
        v3.addNeighbour(new Edge(3,v3,v8));
        v4.addNeighbour(new Edge(2,v4,v7));
        v5.addNeighbour(new Edge(2,v5,v7));
        v5.addNeighbour(new Edge(3,v5,v8));
        v6.addNeighbour(new Edge(5,v6,v8));
        v7.addNeighbour(new Edge(3,v7,v4));
        v8.addNeighbour(new Edge(4,v8,v5));

        DijkstraShortestPath shortestPath = new DijkstraShortestPath();

        shortestPath.computeShortestPaths(v1);

        System.out.println("======================================");
        System.out.println("Calculating minimum distance");
        System.out.println("======================================");

        System.out.println("Minimum distance from 1 to 2: "+v2.getDistance());
        System.out.println("Minimum distance from 1 to 3: "+v3.getDistance());
        System.out.println("Minimum distance from 1 to 4: "+v4.getDistance());
        System.out.println("Minimum distance from 1 to 5: "+v5.getDistance());
        System.out.println("Minimum distance from 1 to 5: "+v6.getDistance());
        System.out.println("Minimum distance from 1 to 5: "+v7.getDistance());
        System.out.println("Minimum distance from 1 to 5: "+v8.getDistance());


        System.out.println("=====================   =================");
        System.out.println("Calculating Paths");
        System.out.println("======================================");

        System.out.println("Shortest Path from 1 to 2: "+shortestPath.getShortestPathTo(v2));
        System.out.println("Shortest Path from 1 to 3: "+shortestPath.getShortestPathTo(v3));
        System.out.println("Shortest Path from 1 to 4: "+shortestPath.getShortestPathTo(v4));
        System.out.println("Shortest Path from 1 to 5: "+shortestPath.getShortestPathTo(v5));
        System.out.println("Shortest Path from 1 to 5: "+shortestPath.getShortestPathTo(v6));
        System.out.println("Shortest Path from 1 to 5: "+shortestPath.getShortestPathTo(v7));
        System.out.println("Shortest Path from 1 to 5: "+shortestPath.getShortestPathTo(v8));
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println(elapsed+" ms");
    }
}