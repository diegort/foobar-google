public class Solution {
    private static int ID_LENGTH = 5;
    public static String solution(int i) {
        // Your code here
        String primes = generatePrimesString(i + ID_LENGTH);
        return primes.substring(i, i + ID_LENGTH);
    }
    
    private static String generatePrimesString(int maxLength) {
        String numbers = "";
        int currentNumber = 2; 
        while (numbers.length() <= maxLength) {
            if (isPrime(currentNumber)) {
                numbers += String.valueOf(currentNumber);
            }
            currentNumber++;
        }
        
        return numbers;
    }
    
    private static Boolean isPrime(int number) {
        Boolean temporaryPrime = true;
        for (int i=2; i<=number/2 && temporaryPrime; i++) {
            if (number % i == 0) {
                temporaryPrime = false;
            }
        }
        
        return temporaryPrime;
    }
}