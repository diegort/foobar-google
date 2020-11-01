import java.util.Queue;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String []args) {
        for (int i = 0; i < 5000; ++i){
            solution(0, 56);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; ++i){
            solution(0, 56);
        }
        System.out.println(solution(0, 56)); // 3
        long stopTime = System.nanoTime();
        System.out.println((stopTime - startTime)/10001);
    }

    public static int BOARD_SIZE = 8;

    public static int solution(int src, int dst) {
        int total_distance = -1;
        Cell start = new Cell(src);
        Cell end = new Cell(dst);
        Queue<Cell> to_process = new LinkedList<Cell>();
        List<Cell> processed = new LinkedList<>();
        to_process.add(start);
       
        while (!to_process.isEmpty() && total_distance == -1) {
            Cell current = to_process.poll();
            processed.add(current);
            if (current.equals(end)) {
                total_distance = current.distance;
            } 
            else {
                List<Cell> destination_cells = getDestinationCells(current);
                destination_cells.forEach(c -> {
                    if (!processed.contains(c) && !to_process.contains(c)) {
                        c.distance = current.distance + 1;
                        to_process.add(c);
                    }
                });
            }
        }
       
        return total_distance;
    }

    private static List<Cell> getDestinationCells(Cell source) {
        List<Cell> cells = new LinkedList<>();
        int [] desp_rows = { -2, -2, -1, -1, 1, 1, 2, 2 };
        int [] desp_cols = { -1, 1, -2, 2, -2, 2, -1, 1 };

        Cell candidate;
        for (int i = 0; i < 8; ++i) {
            candidate = new Cell(source.position.x + desp_rows[i], source.position.y + desp_cols[i]);
            
            if (candidate.isInsideBoard()) {
                cells.add(candidate);
            }
        }

        return cells;
    }

    public static class Cell {
        public int distance;
        public Coordinates position;
       
        public Cell(int cellNumber) {
            initialize();
           
            position.x = cellNumber % BOARD_SIZE;
            position.y = cellNumber / BOARD_SIZE;
        }
       
        public Cell(int x, int y) {
            initialize();
           
            position.x = x;
            position.y = y;
        }
       
        private void initialize() {
            distance = 0;
            position = new Coordinates();
        }

        public Boolean isInsideBoard() {
            return position.x >= 0 && position.x < BOARD_SIZE && position.y >= 0 && position.y < BOARD_SIZE;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }
            return position.equals(((Cell)other).position);
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