interface Palindrome {
    boolean isPalindrome(int number);
}

class PalindromeChecker implements Palindrome {
    @Override
    public boolean isPalindrome(int number) {
        int originalNumber = number;
        int reversedNumber = 0;

        while (number != 0) {
            int remainder = number % 10;
            reversedNumber = reversedNumber * 10 + remainder;
            number /= 10;
        }

        return originalNumber == reversedNumber;
    }
}

public class Main {
    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();
        int number1 = 12321;
        int number2 = 12345;

        System.out.println(number1 + " is palindrome? " + checker.isPalindrome(number1));
        System.out.println(number2 + " is palindrome? " + checker.isPalindrome(number2));
    }
}