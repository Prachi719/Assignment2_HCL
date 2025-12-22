package Assignment2;

import java.util.*;
import java.util.regex.Pattern;

interface EmployeeOperations {
    void addEmployee();
    void displayEmployees();
    void searchEmployee(int id);
    void removeEmployee(int id);
    void demoNullSupport();
}

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + "  Name: " + name + "  Salary: " + salary;
    }
}

public class EmployeeMenu implements EmployeeOperations {

    HashMap<Integer, Employee> hmap = new HashMap<>();
    Hashtable<Integer, Employee> htable = new Hashtable<>();
    TreeMap<Integer, Employee> tmap = new TreeMap<>();

    Scanner sc = new Scanner(System.in);
    static String NAME_REGEX = "^[A-Za-z ]+$";

    public static void main(String[] args) {

        EmployeeMenu obj = new EmployeeMenu();
        int n;

        try {
            while (true) {
                System.out.println("------ Employee Menu ------");
                System.out.println("1. Add Employee");
                System.out.println("2. Display Employees");
                System.out.println("3. Search Employee");
                System.out.println("4. Remove Employee");
                System.out.println("5. Demonstrate Null Support");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                n = obj.sc.nextInt();

                switch (n) {
                    case 1:
                        obj.addEmployee();
                        break;
                    case 2:
                        obj.displayEmployees();
                        break;
                    case 3:
                        System.out.print("Enter Employee ID: ");
                        obj.searchEmployee(obj.sc.nextInt());
                        break;
                    case 4:
                        System.out.print("Enter Employee ID: ");
                        obj.removeEmployee(obj.sc.nextInt());
                        break;
                    case 5:
                        obj.demoNullSupport();
                        break;
                    case 6:
                        System.out.println("Program Ended");
                        obj.sc.close();
                        return;
                    default:
                        System.out.println("Invalid Choice");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: Invalid Input");
        }
    }

    public void addEmployee() {
        sc.nextLine();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        if (!Pattern.matches(NAME_REGEX, name)) {
            System.out.println("Invalid Name");
            return;
        }

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        Employee e = new Employee(id, name, salary);

        hmap.put(id, e);
        htable.put(id, e);
        tmap.put(id, e);

        System.out.println("Employee Added");
    }

    public void displayEmployees() {
        if (hmap.isEmpty()) {
            System.out.println("No Records");
            return;
        }

        System.out.println("--- HashMap ---");
        for (Employee e : hmap.values())
            System.out.println(e);

        System.out.println("-- Hashtable ---");
        for (Employee e : htable.values())
            System.out.println(e);

        System.out.println("-- TreeMap ---");
        for (Employee e : tmap.values())
            System.out.println(e);
    }

    public void searchEmployee(int id) {
        Employee e = hmap.get(id);
        if (e != null)
            System.out.println("Found: " + e);
        else
            System.out.println("Employee Not Found");
    }

    public void removeEmployee(int id) {
        if (hmap.remove(id) != null) {
            htable.remove(id);
            tmap.remove(id);
            System.out.println("Employee Removed");
        } else {
            System.out.println("Employee Not Found");
        }
    }

    public void demoNullSupport() {
        System.out.println("HashMap Null Support:");
        hmap.put(null, null);
        System.out.println("HashMap allows one null key and multiple null values");

        System.out.println("Hashtable Null Support:");
        System.out.println("Hashtable does NOT allow null key or null value");

        System.out.println("TreeMap Null Support:");
        System.out.println("TreeMap does NOT allow null key but allows null values");
    }
}
