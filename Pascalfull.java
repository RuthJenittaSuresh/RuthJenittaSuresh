
import java.util.Scanner;

public class Pascalfull {
    public static void main(String args[]) {
        Scanner scin = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = scin.nextInt();
        Pyramid(rows, 1);
    }

    public static void Pyramid(int rows, int cRow) {
        if (cRow <= rows) {
            Spaces(rows - cRow);
            Asterisks(1, cRow);
            System.out.println();
            Pyramid(rows, cRow + 1);
        } else {
            ReversePyramid(rows, rows - 1);
        }
    }

    public static void ReversePyramid(int rows, int cRow) {
        if (cRow >= 1) {
            Spaces(rows - cRow);
            Asterisks(1, cRow);
            System.out.println();
            ReversePyramid(rows, cRow - 1);
        }
    }

    public static void Spaces(int count) {
        if (count > 0) {
            System.out.print(" ");
            Spaces(count - 1);
        }
    }

    public static void Asterisks(int current, int max) {
        if (current <= max) {
            System.out.print("* ");
            Asterisks(current + 1, max);
        }
    }
}

/*

C:\Users\User1\Desktop\java programs>java Pascalfull
Enter the number of rows: 5
    *
   * *
  * * *
 * * * *
* * * * *
 * * * *
  * * *
   * *
    *

*/
