import java.util.*;
class Courses 
{
    public static void main(String[] args)
    {
        Course course;
        String str, dept, number, name; 
        Scanner scanner;
        scanner = new Scanner(System.in);

        System.out.print("Enter the course: ");
        str = scanner.nextLine();
        dept = str.substring(0, str.indexOf(" "));
        str = str.substring(str.indexOf(" ")+1, str.length());
        number = str.substring(0, str.indexOf(" "));
        name = str.substring(str.indexOf(" ")+1, str.length());
        course = new Course(dept, number, name);
        System.out.println();
        System.out.println("The class, \"" + course.getName() + "\", is a ");
        System.out.println(course.getLevel() + " level class offered by the ");
        System.out.println(course.getDepartment() + " department.");
    }
}


/*


D:\tejalsunki\home-120324>javac Course.java

D:\tejalsunki\home-120324>javac Courses.java

D:\tejalsunki\home-120324>java Courses
Enter the course: CS 101 Java Programming

The class, "Java Programming", is a
first-year level class offered by the
Computer Science department.


*/