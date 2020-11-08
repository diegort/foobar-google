import java.math.BigInteger;

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
        String n1 = "4"; // Test case 1 --> 2
        String n2 = "15"; // Test case 2 --> 5
        String n3 = "16"; 
        String n4 = "32"; 
        String n5 = "124480579411363943422977485485450829978158403576349485507396127987323092328068524587695005561434534623452345436346456353425362283769712245781118297614280332424240701048410620648401132628401374562794562558123463462235342526466804149296501029546541499918765438784295157088047123009825235235168758962399";
        String n6 = "33";
        String n7 = "1024";

        measureExecutionTime(() -> solution("1"));
        measureExecutionTime(() -> solution("2"));
        measureExecutionTime(() -> solution(n1));
        measureExecutionTime(() -> solution(n2));
        measureExecutionTime(() -> solution(n3));
        measureExecutionTime(() -> solution(n4));
        measureExecutionTime(() -> solution(n6));
        measureExecutionTime(() -> solution(n7));
        measureExecutionTime(() -> solution(n5));
    }

    public static int solution(String x) {
        BigInteger num = new BigInteger(x);
        int num_ops = 0, consecutive_zeros, bitLenght;
        boolean tmp;
        while ((bitLenght = num.bitLength()) > 1) {
            if (num.testBit(0)) {  
                tmp = bitLenght > 2 && num.testBit(1);
                if (!tmp) {
                    num = num.subtract(BigInteger.ONE);
                }
                else { 
                    num = num.add(BigInteger.ONE);
                }
                num_ops += 1;
            } else {
                consecutive_zeros = num.getLowestSetBit(); 
                num = num.shiftRight(consecutive_zeros);
                num_ops += consecutive_zeros;
            }
        }
        return num_ops;
    }
}
