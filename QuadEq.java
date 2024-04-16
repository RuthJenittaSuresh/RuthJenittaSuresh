import java.util.*;

/**
 * A program to calculate two roots of a quadratic equation.
 * Assume that a <> 0 and the relationship b^2 >= 4ac holds,
 * so there will be real number solutions for x.
 * 
 * @author James T. Streib and Takako Soma
 * 
*/

public class QuadEq
{
    
/**
 * This method inputs three numbers then calculates and
 * outputs two roots.
 */
 
    public static void main(String[] args)
    {
        // declaration and initialization of variables 
        double a, b, c, root1, root2, sqrtDiscr;
        Scanner scanner;
        scanner = new Scanner(System.in);

        // input a, b, and c 
        System.out.print("Enter a: "); 
        a = scanner.nextDouble(); 
        System.out.print("Enter b: "); 
        b = scanner.nextDouble();
        System.out.print("Enter c: ");
        c = scanner.nextDouble();

        // compute the two roots
        sqrtDiscr = Math.sqrt(Math.pow(b, 2) - 4*a*c);
        root1 = (-b + sqrtDiscr) / (2*a);
        root2 = (-b - sqrtDiscr) / (2*a);

        // output two roots
        System.out.println();
        System.out.println("Two roots of the equation, " + a + "*x*x + " + b + "*x + " + c + " = 0, are");

        System.out.printf("%.2f and %.2f.", root1, root2);

    }
}


/*

D:\tejalsunki\home-120324\continue>javadoc QuadEq.java
Loading source file QuadEq.java...
Constructing Javadoc information...
Standard Doclet version 11.0.20
Building tree for all the packages and classes...
QuadEq.java:5: error: malformed HTML
 * Assume that a <> 0 and the relationship b^2 >= 4ac holds,
                 ^
QuadEq.java:5: error: bad use of '>'
 * Assume that a <> 0 and the relationship b^2 >= 4ac holds,
                  ^
QuadEq.java:5: error: bad use of '>'
 * Assume that a <> 0 and the relationship b^2 >= 4ac holds,
                                               ^
Generating .\QuadEq.html...
QuadEq.java:20: warning: no @param for args
    public static void main(String[] args)
                       ^
Generating .\package-summary.html...
Generating .\package-tree.html...
Generating .\constant-values.html...
Building index for all the packages and classes...
Generating .\overview-tree.html...
Generating .\index-all.html...
Building index for all classes...
Generating .\allclasses-index.html...
Generating .\allpackages-index.html...
Generating .\deprecated-list.html...
Building index for all classes...
Generating .\allclasses.html...
Generating .\allclasses.html...
Generating .\index.html...
Generating .\help-doc.html...
3 errors
1 warning

D:\tejalsunki\home-120324\continue>javac QuadEq.java

D:\mahesh\home-120324\continue>java QuadEq
Enter a: 1
Enter b: -3
Enter c: 2

Two roots of the equation, 1.0*x*x + -3.0*x + 2.0 = 0, are
2.00 and 1.00.


*/