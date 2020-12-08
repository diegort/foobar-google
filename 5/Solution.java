import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Solution {
    interface I {
        public String myMethod();
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
        // Test 1 --> 4208
        String str1 = "77";

        // Test 2 --> 19
        String str2 = "5";

        String str3 = "6"; // 27

        String str4 = "1000000"; // 707106988293

        System.out.println(solution(str1));
        System.out.println(solution(str2));
        System.out.println(solution(str3));
        System.out.println(solution(str4));
    }

    private static BigDecimal TWO = new BigDecimal(2);
    private static BigDecimal CONST_VALUE = TWO.sqrt(new MathContext(101)).subtract(BigDecimal.ONE);

    public static String solution(String s) {
        return solutionCalculation(new BigDecimal(s)).toString();
    }

    private static BigDecimal solutionCalculation(BigDecimal n) {
        if (n.equals(BigDecimal.ZERO)){
            return BigDecimal.ZERO;
        }

        BigDecimal next_step = CONST_VALUE.multiply(n).setScale(0, RoundingMode.FLOOR);

        BigDecimal term1 = n.multiply(next_step);
        BigDecimal term2 = n.add(BigDecimal.ONE).multiply(n).divide(TWO);
        BigDecimal term3 = next_step.add(BigDecimal.ONE).multiply(next_step).divide(TWO);

        return term1.add(term2).subtract(term3).subtract(solutionCalculation(next_step));
    }
}