package Assignment2;

import java.util.*;
import java.util.regex.Pattern;

interface StudentOperations {
    void addStudent();
    void displayStudents();
    void removeStudent(int roll);
    void searchStudent(int roll);
}

class Student {
    int roll;
    String name;
    int marks;

    Student(int roll, String name, int marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return roll + "  " + name + "  " + marks;
    }
}

public class StudentMenu implements StudentOperations {

    List<Student> list = new ArrayList<>();
    Vector<Student> vec = new Vector<>();
    Scanner sc = new Scanner(System.in);

    static String NAME_REGEX = "^[A-Za-z ]+$";

    public static void main(String[] args) {

        StudentMenu obj = new StudentMenu();
        int n;

        while (true) {
            System.out.println("\n------ Student Menu ------");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            n = obj.sc.nextInt();

            switch (n) {
                case 1:
                    obj.addStudent();
                    break;
                case 2:
                    obj.displayStudents();
                    break;
                case 3:
                    System.out.print("Enter roll number: ");
                    int r1 = obj.sc.nextInt();
                    obj.removeStudent(r1);
                    break;
                case 4:
                    System.out.print("Enter roll number: ");
                    int r2 = obj.sc.nextInt();
                    obj.searchStudent(r2);
                    break;
                case 5:
                    System.out.println("Program Ended");
                    obj.sc.close();
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void addStudent() {
        sc.nextLine();

        System.out.print("Enter Roll: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        if (!Pattern.matches(NAME_REGEX, name)) {
            System.out.println("Invalid Name");
            return;
        }

        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        Student s = new Student(roll, name, marks);
        list.add(s);
        vec.add(s);

        System.out.println("Student Added");
    }

    public void displayStudents() {
        if (list.isEmpty()) {
            System.out.println("No Records");
            return;
        }

        for (Student s : list) {
            System.out.println(s);
        }
    }

    public void removeStudent(int roll) {
        Iterator<Student> it = list.iterator();
        boolean f = false;

        while (it.hasNext()) {
            Student s = it.next();
            if (s.roll == roll) {
                it.remove();
                vec.remove(s);
                f = true;
                System.out.println("Student Removed");
                break;
            }
        }

        if (!f) {
            System.out.println("Student Not Found");
        }
    }

    public void searchStudent(int roll) {
        for (Student s : list) {
            if (s.roll == roll) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student Not Found");
    }
}

