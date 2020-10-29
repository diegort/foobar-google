public class Solution {
    public static int MOST_JUNIOR_PAYMENT = 1;
    public static int solution(int total_lambs) {
        //Your code here
        return stingyHandout(total_lambs) - generousHandout(total_lambs);
    }
    
    private static int generousHandout(int total_lambs) {
        Boolean cont = total_lambs > 0;
        int remaining_lambs = total_lambs;
        int next_payment = 0;
        int current_payment = 0;
        int num_payments = 0;
        while (cont) {
            if (num_payments == 0) {
                current_payment = MOST_JUNIOR_PAYMENT;
            }
            else {
                current_payment = next_payment;
            }
            
            remaining_lambs -= current_payment;
            
            next_payment = getNextGenerousPayment(current_payment);
            cont = remaining_lambs >= next_payment;

            num_payments++;
        }

        return num_payments;
    }
    
    private static int getNextGenerousPayment(int current_payment) {
        return 2 * current_payment;
    }
    
    private static int stingyHandout(int total_lambs) {
        Boolean cont = total_lambs > 0;
        int remaining_lambs = total_lambs;
        int next_payment = 0;
        int current_payment = 0;
        int previous_payment = 0;
        int num_payments = 0;
        while (cont) {
            if ((num_payments == 0) || (num_payments == 1)) {
                current_payment = MOST_JUNIOR_PAYMENT;
            }
            else {
                current_payment = next_payment;
            }
            
            remaining_lambs -= current_payment;
            
            next_payment = getNextStingyPayment(current_payment, previous_payment);
            cont = remaining_lambs >= next_payment;
            
            previous_payment = current_payment;
            num_payments++;
        }

        return num_payments;
    }
    
    private static int getNextStingyPayment(int current_payment, int previous_payment) {
        return current_payment + previous_payment;
    }
}