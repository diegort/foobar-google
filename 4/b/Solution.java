import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        // Test 1 --> 7
        int[] s1 = { 3, 2 };
        int[] p1 = { 1, 1 };
        int[] g1 = { 2, 1 };
        int d1 = 4;

        // Test 2 --> 9
        int[] s2 = { 300, 275 };
        int[] p2 = { 150, 150 };
        int[] g2 = { 185, 100 };
        int d2 = 500;

        System.out.println(solution(s1, p1, g1, d1));
        System.out.println(solution(s2, p2, g2, d2));
    }

    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) {
        Position reference = new Position(your_position[0], your_position[1]);
        Position guard = new Position(guard_position[0], guard_position[1]);
        List<Target> targets = getPossibleTargets(dimensions, reference, guard, distance);

        targets = filterTargetsByDistance(targets, reference, distance);
        targets = filterHiddenTargets(targets, reference);

        return Math.toIntExact(targets.stream().filter(t -> t.type == Target.ObjectType.Guard).count());
    }

    private static List<Target> getPossibleTargets(int[] dimensions, Position your_position, Position guard_position, int distance) {
        int max_horizontal_copies = (your_position.x + distance)/dimensions[0] + 1;
        int max_vertical_copies = (your_position.y + distance)/dimensions[1] + 1;
        int last_me_index = 0, last_guard_index = 0;
        List<Target> possible_targets = new LinkedList<>();
        Target tmp;
        Position temp_me = new Position(), temp_guard = new Position();
        
        for (int i = 0; i < max_horizontal_copies; i++) {
            if (i == 0)  {
                temp_me.x = your_position.x;
                temp_guard.x = guard_position.x;
            }
            else {
                temp_me.x = 2 * dimensions[0] * i - possible_targets.get(last_me_index).position.x;
                temp_guard.x = 2 * dimensions[0] * i - possible_targets.get(last_guard_index).position.x;
            }

            for (int j = 0; j < max_vertical_copies; j++) {
                if (j == 0)  {
                    temp_me.y = your_position.y;
                    temp_guard.y = guard_position.y;
                }
                else {
                    temp_me.y = 2 * dimensions[1] * j - possible_targets.get(last_me_index).position.y;
                    temp_guard.y = 2 * dimensions[1] * j - possible_targets.get(last_guard_index).position.y;
                }
    
                if (temp_me.distance(your_position) <= distance) {
                    tmp = new Target(temp_me, Target.ObjectType.Me);
                    possible_targets.add(tmp);
                    last_me_index = possible_targets.size() - 1;
                }
    
                if (temp_guard.distance(your_position) <= distance) {
                    tmp = new Target(temp_guard, Target.ObjectType.Guard);
                    possible_targets.add(tmp);
                    last_guard_index = possible_targets.size() - 1;
                }
            }
        }

        List<Target> mirrored_targets = mirror(possible_targets, new int[]{ -1, 1 });
        mirrored_targets.addAll(mirror(possible_targets, new int[]{ -1, -1 }));
        mirrored_targets.addAll(mirror(possible_targets, new int[]{ 1, -1 }));

        possible_targets.addAll(mirrored_targets);
        return possible_targets;
    }

    private static List<Target> mirror(List<Target> originals, int[] transformation) {
        List<Target> tmp = new LinkedList<>();

        for (Target t : originals) {
            tmp.add(new Target(t.position.x * transformation[0], t.position.y * transformation[1], t.type));
        }

        return tmp;
    }

    private static List<Target> filterTargetsByDistance(List<Target> targets, Position origin, int distance) {
        List<Target> filtered = targets.stream().filter(t -> t.position.distance(origin) <= distance).collect(Collectors.toList());
        filtered.sort((t1, t2) -> Double.compare(t1.position.distance(origin), t2.position.distance(origin)));
        return filtered;
    }

    private static List<Target> filterHiddenTargets(List<Target> targets, Position reference) {
        Map<Double, Target> filtered = new HashMap<>();
        double angle;

        targets.remove(0);
        for (Target t : targets) {
            angle = reference.angle(t.position);
            if (!filtered.containsKey(angle)) {
                filtered.put(angle, t);
            }
        }

        List<Target> tmp = new LinkedList<>(filtered.values());
        return tmp;
    }

    public static class Target {
        public static enum ObjectType {
            Me,
            Guard
        }

        public ObjectType type;
        public Position position;

        public Target (int x, int y, ObjectType type) {
            position = new Position(x, y);
            this.type = type;
        }

        public Target (Position position, ObjectType type) {
            this(position.x, position.y, type);
        }
    }

    public static class Position {
        public int x;
        public int y;

        public Position() {}

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double distance(Position other) {
            return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
        }

        public double angle(Position other) {
            return Math.atan2(other.y - y, other.x - x);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }
            return x  == ((Position)other).x && y == ((Position)other).y;
        }
    }
}