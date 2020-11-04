import java.util.stream.IntStream;

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
        int[] l1 = {1, 1, 1}; //1
        int[] l2 = {1, 2, 3, 4, 5, 6}; // 3
        int[] l3 = {1, 5, 6}; // 0
        int[] l4 = IntStream.range(1, 20001).map(i -> i = i*3 + 123).toArray();
        int[] l5 = {2, 3, 4, 5, 6, 8, 12, 16};

        for (int i = 0; i < 500; ++i){
            solution2(l5);
        }

        System.out.println("{1, 1, 1}");
        measureExecutionTime(() -> solution(l1));
        //measureExecutionTime(() -> solution1(l1));
        measureExecutionTime(() -> solution2(l1));
        System.out.println("\n{1, 2, 3, 4, 5, 6}");
        measureExecutionTime(() -> solution(l2));
        //measureExecutionTime(() -> solution1(l2));
        measureExecutionTime(() -> solution2(l2));
        System.out.println("\n{1, 5, 6}");
        measureExecutionTime(() -> solution(l3));
        //measureExecutionTime(() -> solution1(l3));
        measureExecutionTime(() -> solution2(l3));
        System.out.println("\n{1..2001}");
        measureExecutionTime(() -> solution(l4), 500);
        //measureExecutionTime(() -> solution1(l4), 500);
        measureExecutionTime(() -> solution2(l4), 500);
        System.out.println("\n{2, 3, 4, 5, 6, 8, 12, 16}");
        measureExecutionTime(() -> solution(l5));
        //measureExecutionTime(() -> solution1(l5));
        measureExecutionTime(() -> solution2(l5)); 
    }

    public static int solution(int[] l) {
        int[] num_dividers = new int[l.length];
        int result = 0;
        for (int i = 0; i < l.length; ++i) {
            num_dividers[i] = 0;
            for (int j=0; j < i; ++j){
                if (l[i] % l[j] == 0){
                    num_dividers[i]++;
                    result += num_dividers[j];
                }
            }
        }
        return result;
    }

    public static int solution1(int[] l) {
        int dividers;
        int multiples;
        int result = 0;
        for (int i = 1; i < l.length - 1; ++i) {
            dividers = 0;
            multiples = 0;
            for (int j = 0; j < i; ++j) {
                if (l[i] % l[j] == 0){
                    dividers++;
                }
            }

            for (int j = i + 1; j < l.length; ++j) {
                if (l[j] % l[i] == 0){
                    multiples++;
                }
            }

            result += dividers * multiples;
        }
        return result;
    }

    public static int solution2(int[] l) {
        boolean[][] divisibility = new boolean[l.length][l.length];
        int num_dividers;
        int num_multiples;
        int result = 0;
        for (int i = 0; i < l.length - 1; ++i) {
            num_dividers = 0;
            num_multiples = 0;
            for (int j = 0; j < i; ++j) {
                if (divisibility[i][j]) {
                    num_dividers++;
                }
            }

            for (int j = i + 1; j < l.length; ++j) {
                if (l[j] % l[i] == 0){
                    num_multiples++;
                    divisibility[j][i] = true;
                }
            }

            result += num_dividers * num_multiples;
        }
        return result;
    }
}