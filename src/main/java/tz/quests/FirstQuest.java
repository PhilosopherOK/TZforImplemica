package tz.quests;

import java.math.BigInteger;
import java.util.Scanner;


public class FirstQuest {
    public static void main(String[] args) {
        System.out.println(calculateCatalan());
    }
    /*
     Number of possible sequences with a fixed number of pairs
     brackets is expressed as a Catalan number which can be obtained using the formula C(n) = (2n)!/(n+1)!*n!
     */
    public static BigInteger calculateCatalan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a non-negative number.");
        int n = -1;
        if (scanner.hasNextInt()) {
            n = scanner.nextInt();
        }
        if (n < 0) {
            System.out.println("N must be a non-negative number.");
            return calculateCatalan();
        } else {
            return factorial(2 * n).divide(factorial(n + 1).multiply(factorial(n)));
        }
    }

    // Method for calculating factorial
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}