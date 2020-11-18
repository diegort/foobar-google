import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
    interface I {
        public int myMethod();
    }
    private static void measureExecutionTime(I myMethodsInterface, int reps) {
        long startTime = System.nanoTime();
        for (int i = 0; i < reps; ++i){
            myMethodsInterface.myMethod();
        }
        System.out.println(myMethodsInterface.myMethod());
        long stopTime = System.nanoTime();
        System.out.println(((stopTime - startTime)/(reps + 1))/1000.0 + "usec");
    }

    private static void measureExecutionTime(I myMethodsInterface) {
        measureExecutionTime(myMethodsInterface, 100000);
    }

    public static void main(String []args) {
        // Test 1  --> 16
        int[] entrances1 = { 0, 1 };
        int[] exits1 = { 4, 5 };
        int[][] map1 = { { 0, 0, 4, 6, 0, 0 },
                         { 0, 0, 5, 2, 0, 0 },
                         { 0, 0, 0, 0, 4, 4 },
                         { 0, 0, 0, 0, 6, 6 },
                         { 0, 0, 0, 0, 0, 0 },
                         { 0, 0, 0, 0, 0, 0 }};

        // Test 2 --> 6
        int[] entrances2 = { 0 };
        int[] exits2 = { 3 };
        int[][] map2 = { { 0, 7, 0, 0 },
                         { 0, 0, 6, 0 },
                         { 0, 0, 0, 8 },
                         { 9, 0, 0, 0 }};

        // 19
        int[] entrances3 = { 0 };
        int[] exits3 = { 5 };
        int[][] map3 = { { 0, 20, 20, 0, 0, 0 },
                         { 0, 0, 2, 4, 8, 0 },
                         { 0, 0, 0, 0, 9, 0 },
                         { 0, 0, 0, 0, 0, 10 },
                         { 0, 0, 0, 6, 0, 10 },
                         { 0, 0, 0, 0, 0, 0 }};

        /*measureExecutionTime(() -> solution(entrances1, exits1, map1));
        measureExecutionTime(() -> solution(entrances2, exits2, map2));
        measureExecutionTime(() -> solution(entrances3, exits3, map3));*/
        System.out.println(solution(entrances1, exits1, map1));
        System.out.println(solution(entrances2, exits2, map2));
        System.out.println(solution(entrances3, exits3, map3));

    }

    /*
        Reference implementation for Dinic's Algorithm
        https://www.geeksforgeeks.org/dinics-algorithm-maximum-flow/
    */
    private static final int INF = Integer.MAX_VALUE;

    public static int solution(int[] entrances, int[] exits, int[][] path) {
        if (entrances.length > 1 || exits.length > 1 ) {
            path = normalizeMap(entrances, exits, path);
        }

        FlowNetwork graph = createGraph(path);
        int total_flow = 0, temp_flow;

        while (setVertexLevels(graph)) {
            while ((temp_flow = sendFlow(graph, graph.source(), Integer.MAX_VALUE)) != 0) {
                total_flow += temp_flow;
            }
        }
        return total_flow;
    }

    private static int[][] normalizeMap(int[] entrances, int[] exits, int[][] map) {
        int length = map.length;
        int super_source = 0, super_sink = length + 1;
        int[][] normalized_map = new int[length + 2][length + 2];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                normalized_map[i + 1][j + 1] = map[i][j];
            }
        }
        for (int i = 0; i < entrances.length; i++) {
            normalized_map[super_source][entrances[i] + 1] = INF;
        }
        for (int i = 0; i < exits.length; i++) {
            normalized_map[exits[i] + 1][super_sink] = INF;
        }
        return normalized_map;
    }

    private static FlowNetwork createGraph(int[][] map) {
        int length = map.length;
        FlowNetwork graph = new FlowNetwork();
        Vertex[] vertexes = new Vertex[length];
        for (int i = 0; i < length; i++) {
            Vertex current = new Vertex();
            current.id = i;
            current.adjacents = new ArrayList<>();
            vertexes[i] = current;
        }

        graph.vertexes = vertexes;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (map[i][j] > 0) {
                    Edge current = new Edge();
                    current.capacity = map[i][j];
                    current.flow = 0;
                    current.destination = vertexes[j];

                    Edge reverse = new Edge();
                    reverse.capacity = 0;
                    reverse.flow = 0;
                    reverse.destination = vertexes[i];

                    reverse.reverse = current;
                    current.reverse = reverse;

                    vertexes[i].adjacents.add(current);
                    vertexes[j].adjacents.add(reverse);
                }
            }
        }

        return graph;
    }

    private static boolean setVertexLevels(FlowNetwork residual_graph) {
        for (int i = 1; i < residual_graph.vertexes.length; i++) {
            residual_graph.vertexes[i].level = -1;
            residual_graph.vertexes[i].is_path_blocked = false;
        }
        residual_graph.source().level = 0;
        residual_graph.vertexes[0].is_path_blocked = false;

        Queue<Vertex> to_process = new ArrayDeque<>();
        to_process.add(residual_graph.source());

        Vertex current;
        while (!to_process.isEmpty()){
            current = to_process.poll();
            for(Edge e : current.adjacents){
                if (e.destination.level < 0 && e.flow < e.capacity) {
                    e.destination.level = current.level + 1;

                    to_process.add(e.destination);
                }
            }
        }
        return residual_graph.sink().level != -1;
    }

    private static int sendFlow(FlowNetwork graph, Vertex current, int flow) {
        if (current == graph.sink()) {
            return flow;
        }
        int tentative_flow, actual_flow;
        for(Edge e : current.adjacents) {
            if ((e.destination.level == (current.level + 1)) && (e.flow < e.capacity) && !e.destination.is_path_blocked) {
                tentative_flow = Integer.min(flow, e.capacity - e.flow);

                if ((actual_flow = sendFlow(graph, e.destination, tentative_flow)) > 0) {
                    e.flow += actual_flow;
                    e.reverse.flow -= actual_flow;

                    return actual_flow;
                }
                else {
                    e.destination.is_path_blocked = true;
                }
            }
        }
        return 0;
    }

    public static class Edge {
        Vertex destination;
        int flow;    
        int capacity;    
        Edge reverse;
    }

    public static class Vertex {
        int id;
        int level;
        List<Edge> adjacents;
        boolean is_path_blocked;
    }

    public static class FlowNetwork {
        Vertex[] vertexes;

        public Vertex source() {
            return vertexes[0];
        }

        public Vertex sink() {
            return vertexes[vertexes.length - 1];
        }
    }
}
