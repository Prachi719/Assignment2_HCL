package Assignment2;

import java.util.*;
import java.util.regex.Pattern;

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

class Student1 {
    int id;
    String name;
    String course;
    int marks;

    Student1(int id, String name, String course, int marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public String toString() {
        return id + "  " + name + "  " + course + "  " + marks;
    }
}

public class UniversityMenu implements UniversityOperations {

    List<Student> arrList = new ArrayList<>();
    Vector<Student> vec = new Vector<>();
    Stack<Student> stk = new Stack<>();

    HashMap<Integer, Student> hmap = new HashMap<>();
    Hashtable<Integer, Student> htable = new Hashtable<>();
    TreeMap<Integer, Student> tmap = new TreeMap<>();

    Set<String> courseSet = new HashSet<>();

    Scanner sc = new Scanner(System.in);

    static String NAME_REGEX = "^[A-Za-z ]+$";
    static String COURSE_REGEX = "^[A-Za-z ]+$";

    public static void main(String[] args) {

        UniversityMenu obj = new UniversityMenu();
        int n;

        try {
            while (true) {
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

                n = obj.sc.nextInt();

                switch (n) {
                    case 1:
                        obj.addStudent();
                        break;
                    case 2:
                        obj.displayStudents();
                        break;
                    case 3:
                        System.out.print("Enter ID: ");
                        obj.removeStudent(obj.sc.nextInt());
                        break;
                    case 4:
                        System.out.print("Enter ID: ");
                        obj.searchStudent(obj.sc.nextInt());
                        break;
                    case 5:
                        obj.sortByMarks();
                        break;
                    case 6:
                        obj.convertHashMapToTreeMap();
                        break;
                    case 7:
                        obj.countStudentsCourseWise();
                        break;
                    case 8:
                        obj.displayCourses();
                        break;
                    case 9:
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

        Student s = new Student1(id, name, course, marks);

        arrList.add(s);
        vec.add(s);
        stk.push(s);

        hmap.put(id, s);
        htable.put(id, s);

        courseSet.add(course);

        System.out.println("Student Added");
    }

    public void displayStudents() {
        if (arrList.isEmpty()) {
            System.out.println("No Records");
            return;
        }

        for (Student s : arrList)
            System.out.println(s);
    }

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

    public void searchStudent(int id) {
        Student s = hmap.get(id);
        if (s != null)
            System.out.println("Found: " + s);
        else
            System.out.println("Student Not Found");
    }

    public void sortByMarks() {
        arrList.sort(Comparator.comparingInt(st -> st.marks));
        System.out.println("Students Sorted by Marks");
        displayStudents();
    }

    public void convertHashMapToTreeMap() {
        tmap.clear();
        tmap.putAll(hmap);
        System.out.println("HashMap Converted to TreeMap");
        for (Student s : tmap.values())
            System.out.println(s);
    }

    public void countStudentsCourseWise() {
        HashMap<String, Integer> countMap = new HashMap<>();

        for (Student s : arrList) {
            countMap.put(s.course, countMap.getOrDefault(s.course, 0) + 1);
        }

        for (Map.Entry<String, Integer> e : countMap.entrySet())
            System.out.println(e.getKey() + " : " + e.getValue());
    }

    public void displayCourses() {
        System.out.println("Courses:");
        for (String c : courseSet)
            System.out.println(c);
    }
}
