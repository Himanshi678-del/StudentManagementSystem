package com.company;

import java.util.*;

// Model Class
class Student {
    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;

    public Student(int id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return "ID=" + id +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                ", Course='" + course + '\'' +
                ", Marks=" + marks;
    }
}

// Service Class
class StudentService {
    private List<Student> list = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n1.ADD 2.VIEW 3.SEARCH 4.UPDATE 5.DELETE 6.SORT 7.STATS 8.EXIT");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> sortStudents();
                case 7 -> showStats();
                case 8 -> {
                    System.out.println("EXIT");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addStudent() {
        System.out.println("Enter Name:");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.println("Enter ID:");
        int id = sc.nextInt();
        sc.nextLine();

        boolean exists = list.stream().anyMatch(s -> s.getId() == id);
        if (exists) {
            System.out.println("ID already exists!");
            return;
        }

        System.out.println("Enter Age:");
        int age = sc.nextInt();
        sc.nextLine();
        if (age <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        System.out.println("Enter Course:");
        String course = sc.nextLine();

        System.out.println("Enter Marks:");
        double marks = sc.nextDouble();
        sc.nextLine();
        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0–100!");
            return;
        }

        list.add(new Student(id, name, age, course, marks));
        System.out.println("Student added successfully!");
    }

    private void viewStudents() {
        if (list.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        list.forEach(System.out::println);
    }

    private void searchStudent() {
        System.out.println("Enter ID:");
        int id = sc.nextInt();
        sc.nextLine();

        Optional<Student> student = list.stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        student.ifPresentOrElse(
                s -> System.out.println("Found: " + s),
                () -> System.out.println("Student not found!")
        );
    }

    private void updateStudent() {
        System.out.println("Enter ID:");
        int id = sc.nextInt();
        sc.nextLine();

        Optional<Student> optional = list.stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        if (optional.isEmpty()) {
            System.out.println("Student not found!");
            return;
        }

        Student s = optional.get();

        System.out.println("New Name:");
        s.setName(sc.nextLine());

        System.out.println("New Age:");
        s.setAge(sc.nextInt());
        sc.nextLine();

        System.out.println("New Course:");
        s.setCourse(sc.nextLine());

        System.out.println("New Marks:");
        s.setMarks(sc.nextDouble());
        sc.nextLine();

        System.out.println("Updated successfully!");
    }

    private void deleteStudent() {
        System.out.println("Enter ID:");
        int id = sc.nextInt();
        sc.nextLine();

        boolean removed = list.removeIf(s -> s.getId() == id);

        if (removed) System.out.println("Deleted successfully!");
        else System.out.println("Student not found!");
    }

    private void sortStudents() {
        System.out.println("Sort by: 1.ID 2.Name 3.Marks");
        int choice = sc.nextInt();

        Comparator<Student> comparator = switch (choice) {
            case 1 -> Comparator.comparingInt(Student::getId);
            case 2 -> Comparator.comparing(Student::getName);
            case 3 -> Comparator.comparingDouble(Student::getMarks);
            default -> null;
        };

        if (comparator == null) {
            System.out.println("Invalid choice!");
            return;
        }

        list.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }

    private void showStats() {
        if (list.isEmpty()) {
            System.out.println("No data!");
            return;
        }

        double avg = list.stream()
                .mapToDouble(Student::getMarks)
                .average()
                .orElse(0);

        Optional<Student> topper = list.stream()
                .max(Comparator.comparingDouble(Student::getMarks));

        System.out.println("Average Marks: " + avg);
        topper.ifPresent(s -> System.out.println("Topper: " + s));
    }
}

// Main Class
public class StudentManagementSystem {
    public static void main(String[] args) {
        new StudentService().start();
    }
}
