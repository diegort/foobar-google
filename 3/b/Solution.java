import java.util.ArrayDeque;
import java.util.LinkedList;
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
        int[][] m1 = { {0, 1, 1, 0},
                       {0, 1, 1, 1}, 
                       {0, 1, 1, 0}, 
                       {1, 0, 0, 0}}; //7
        int[][] m2 = { {0, 0, 0, 0, 0, 0}, 
                       {1, 1, 1, 1, 1, 0}, 
                       {0, 0, 0, 0, 0, 0}, 
                       {0, 1, 1, 1, 1, 1}, 
                       {0, 1, 1, 1, 1, 1}, 
					   {0, 0, 0, 0, 0, 0}}; // 11
		int[][] m3 = { {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
					   {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					   {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
					   {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					   {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
					   {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        int[][] m4 = { {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
					   {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
					   {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
					   {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
					   {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
					   {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
					   {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
					   {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
					   {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
					   {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
					   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, //
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
					   {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		int[][] m5 = {{0}};

		isWorthyToRemoveTheWall(m1, 3, 0);
		measureExecutionTime(() -> solution(m5));
		measureExecutionTime(() -> solution(m1)); 
		measureExecutionTime(() -> solution(m2));
		measureExecutionTime(() -> solution(m3), 1000);
		measureExecutionTime(() -> solution(m4), 1000);
    }

	public static int solution(int[][] map) {
		int min_distance = Integer.MAX_VALUE;
		int widht = map[0].length, height = map.length;
		List<int[][]> maps = getMaps(map);
		
		for(int[][] m : maps){
			min_distance = Math.min(min_distance, calculateShortestPath(m));
			if (min_distance == widht + height - 1){
				return min_distance;
			}
		}

		return min_distance;
	}

	private static List<int[][]> getMaps(int[][] map){
		List<int[][]> maps = new LinkedList<>();
		maps.add(map);
		int[][] temp_copy;
		int widht = map[0].length, height = map.length;
		for (int i = 0 ; i < height; ++i){
			for (int j = 0; j < widht; ++j){
				if (map[i][j] == 1 && isWorthyToRemoveTheWall(map, i, j)) {
					temp_copy = map.clone();
					for (int k = 0 ; k < height; ++k){
						temp_copy[k] = map[k].clone();
					}

					temp_copy[i][j] = 0;
					maps.add(temp_copy);
				}
			}
		}
		return maps;
	}

	private static boolean isWorthyToRemoveTheWall(int[][] map, int row, int col) {
		Node temp = new Node(row, col);

		List<Node> neignbors = getDestinationCells(temp, map);
		
		return neignbors.size() >= 2;
	}

    public static int calculateShortestPath(int[][] map) {
        int total_distance = Integer.MAX_VALUE - 1;
        Node start = new Node(0, 0);
		Node end = new Node(map.length - 1, map[0].length - 1);
		Queue<Node> to_process = new ArrayDeque <Node>();
        List<Node> processed = new LinkedList<>();
        to_process.add(start);
       
        while (!to_process.isEmpty()) {
            Node current = to_process.poll();
            processed.add(current);
            if (current.equals(end)) {
				total_distance = current.distance;
				to_process.clear();
            } 
            else {
                List<Node> destination_cells = getDestinationCells(current, map);
                destination_cells.forEach(c -> {
                    if (!processed.contains(c) && !to_process.contains(c)) {
                        c.distance = current.distance + 1;
                        to_process.add(c);
                    }
                });
            }
        }
       
        return total_distance + 1;
    }

    private static List<Node> getDestinationCells(Node source, int[][] map) {
		List<Node> cells = new LinkedList<>();
		int[][] desp = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };

        Node candidate;
        for (int i = 0; i < desp.length; ++i) {
            candidate = new Node(source.position.x + desp[i][0], source.position.y + desp[i][1]);
            
            if (isNodeInsideMap(candidate, map) && map[candidate.position.x][candidate.position.y] == 0) {
                cells.add(candidate);
            }
        }

        return cells;
	}
	
	public static boolean isNodeInsideMap(Node n, int[][] map) {
		return n.position.x >= 0 && n.position.x < map.length && n.position.y >= 0 && n.position.y < map[n.position.x].length;
	}

    public static class Node {
        public int distance;
        public Coordinates position;

        public Node(int x, int y) {
            initialize();
           
            position.x = x;
            position.y = y;
        }
       
        private void initialize() {
            distance = 0;
            position = new Coordinates();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }
            return position.equals(((Node)other).position);
        }
    }
   
    public static class Coordinates {
        public int x;
        public int y;

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }
            return x  == ((Coordinates)other).x && y == ((Coordinates)other).y;
        }
    }
}