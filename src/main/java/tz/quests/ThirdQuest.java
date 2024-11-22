package tz.quests;

import java.math.BigInteger;
import java.util.Scanner;


public class ThirdQuest {
    public static void main(String[] args) {
        sumOfNumbersFromNumber();
    }

    public static long sumOfNumbersFromNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a non-negative number.");
        int n = -1;

        //Checking for correct input
        if (scanner.hasNextInt()) {
            n = scanner.nextInt();
        }
        if (n < 0) {
            System.out.println("incorrect input");
            return sumOfNumbersFromNumber();
        } else {
            long result = 0;

            //obtaining factorial
            String factorialNumbers = factorial(n).toString();

            //breaking a number into digits and summing them one by one
            String [] numbersFromNum = factorialNumbers.split("");
            for(String num:numbersFromNum){
                result+= Integer.parseInt(num);
            }
            System.out.println(result);
            return result;
        }
    }

    // I took the method for finding the factorial from the first task
    public static BigInteger factorial(long n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
