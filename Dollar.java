import java.util.Scanner;

public class Dollar {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();

       
        Pattern(1, rows);
    }

   
    public static void Pattern(int cRow, int tRows) {
        if (cRow > tRows) {
            return;
        }
        String dollar = String.format("%" + cRow + "s", " ").replace(" ", "$ ");
        System.out.println(dollar);

    
       Pattern(cRow + 1, tRows);
    }
}


/*
// Output

C:\Users\User1\Desktop\java programs>java Dollar
Enter the number of rows: 6
$
$ $
$ $ $
$ $ $ $
$ $ $ $ $
$ $ $ $ $ $

*/
