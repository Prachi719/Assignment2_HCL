package Assignment2;

import java.util.*;
import java.util.regex.Pattern;

/* Interface */
interface UniversityOperations {
    void addStudent();
    void displayStudents();
    void removeStudent(int id);
    void searchStudent(int id);
    void sortByMarks();
    void convertHashMapToTreeMap();
    void countStudentsCourseWise();
    void displayCourses();
}

/* Student Class */
class Student {
    int id;
    String name;
    String course;
    int marks;

    public Student(int id, String name, String course, int marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return id + "  " + name + "  " + course + "  " + marks;
    }
}

/* Main Class */
public class UniversityMenu implements UniversityOperations {

    List<Student> arrList = new ArrayList<>();
    Vector<Student> vec = new Vector<>();
    Stack<Student> stk = new Stack<>();

    HashMap<Integer, Student> hmap = new HashMap<>();
    Hashtable<Integer, Student> htable = new Hashtable<>();
    TreeMap<Integer, Student> tmap = new TreeMap<>();

    Set<String> courseSet = new HashSet<>();

    Scanner sc = new Scanner(System.in);

    static final String NAME_REGEX = "^[A-Za-z ]+$";
    static final String COURSE_REGEX = "^[A-Za-z ]+$";

    public static void main(String[] args) {
        UniversityMenu obj = new UniversityMenu();

        while (true) {
            try {
                System.out.println("\n------ University Menu ------");
                System.out.println("1. Add Student");
                System.out.println("2. Display Students");
                System.out.println("3. Remove Student by ID");
                System.out.println("4. Search Student by ID");
                System.out.println("5. Sort Students by Marks");
                System.out.println("6. Convert HashMap to TreeMap");
                System.out.println("7. Count Students Course-wise");
                System.out.println("8. Display All Courses");
                System.out.println("9. Exit");
                System.out.print("Enter choice: ");

                int choice = obj.sc.nextInt();

                switch (choice) {
                    case 1 -> obj.addStudent();
                    case 2 -> obj.displayStudents();
                    case 3 -> {
                        System.out.print("Enter ID: ");
                        obj.removeStudent(obj.sc.nextInt());
                    }
                    case 4 -> {
                        System.out.print("Enter ID: ");
                        obj.searchStudent(obj.sc.nextInt());
                    }
                    case 5 -> obj.sortByMarks();
                    case 6 -> obj.convertHashMapToTreeMap();
                    case 7 -> obj.countStudentsCourseWise();
                    case 8 -> obj.displayCourses();
                    case 9 -> {
                        System.out.println("Program Ended");
                        obj.sc.close();
                        return;
                    }
                    default -> System.out.println("Invalid Choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input! Please enter numbers only.");
                obj.sc.nextLine();
            }
        }
    }

    @Override
    public void addStudent() {
        sc.nextLine();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (hmap.containsKey(id)) {
            System.out.println("Duplicate Student ID Not Allowed");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        if (!Pattern.matches(NAME_REGEX, name)) {
            System.out.println("Invalid Name");
            return;
        }

        System.out.print("Enter Course: ");
        String course = sc.nextLine();
        if (!Pattern.matches(COURSE_REGEX, course)) {
            System.out.println("Invalid Course Name");
            return;
        }

        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        Student s = new Student(id, name, course, marks);

        arrList.add(s);
        vec.add(s);
        stk.push(s);
        hmap.put(id, s);
        htable.put(id, s);
        courseSet.add(course);

        System.out.println("Student Added Successfully");
    }

    @Override
    public void displayStudents() {
        if (arrList.isEmpty()) {
            System.out.println("No Records Found");
            return;
        }
        arrList.forEach(System.out::println);
    }

    @Override
    public void removeStudent(int id) {
        Student s = hmap.remove(id);
        if (s != null) {
            htable.remove(id);
            arrList.remove(s);
            vec.remove(s);
            stk.remove(s);
            System.out.println("Student Removed");
        } else {
            System.out.println("Student Not Found");
        }
    }

    @Override
    public void searchStudent(int id) {
        Student s = hmap.get(id);
        System.out.println(s != null ? "Found: " + s : "Student Not Found");
    }

    @Override
    public void sortByMarks() {
        arrList.sort(Comparator.comparingInt(st -> st.marks));
        System.out.println("Students Sorted by Marks:");
        displayStudents();
    }

    @Override
    public void convertHashMapToTreeMap() {
        tmap.clear();
        tmap.putAll(hmap);
        System.out.println("HashMap Converted to TreeMap:");
        tmap.values().forEach(System.out::println);
    }

    @Override
    public void countStudentsCourseWise() {
        HashMap<String, Integer> countMap = new HashMap<>();
        for (Student s : arrList) {
            countMap.put(s.course, countMap.getOrDefault(s.course, 0) + 1);
        }
        countMap.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    @Override
    public void displayCourses() {
        System.out.println("Courses:");
        courseSet.forEach(System.out::println);
    }
}