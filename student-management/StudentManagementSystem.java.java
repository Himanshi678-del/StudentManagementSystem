package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

//import static jdk.internal.org.jline.utils.Colors.s;


class student {
    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;

    public student(int id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", course='" + course + '\'' +
                ", marks=" + marks +
                '}';
    }

}
public class project1 {
    private static ArrayList<student> list=new ArrayList<>();
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {

        while (true) {
            System.out.println("1. ADD 2.VIEW 3.SEARCH 4.UPDATE 5.DELETE 6.EXIT");


            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("EXIT");
                    System.exit(0);
                default:
                    System.out.println("invalid choice!");
            }
        }
    }
        public static void addStudent(){
            System.out.println("ENTER NAME:");
            String name=sc.nextLine();
            if(name.isEmpty()){
                System.out.println("NAME CANNOT BE EMPTY!");
            }

            System.out.println("ENTER ID");
            int id =sc.nextInt();
            sc.nextLine();

            boolean exists=list.stream().anyMatch(st->st.getId()==id);
            if(exists){
                System.out.println("ID ALREADY EXISTS!");
                return;
            }

            System.out.println("ENTER AGE");
            int age=sc.nextInt();
            sc.nextLine();
            if(age<=0){
                System.out.println("AGE MUST BE POSITIVE!");
                return;
            }

            System.out.println("ENTER COURSE");
            String course=sc.nextLine();
            if(course.isEmpty()){
                System.out.println("HAVE TO ENTER COURSE");
                return;
            }

            System.out.println("ENTER MARKS");
            double marks=sc.nextDouble();
            sc.nextLine();
            if(marks<0||marks>100){
                System.out.println("MARKS MUST BE BETWEEN 0 AND 100!");
                return;
            }

            list.add(new student(id,name,age,course,marks));
            System.out.println("Student add successfully!!");

        }
        public static void viewStudent(){
        if(list.isEmpty()){
            System.out.println("NO STUDENT FOUND");
            return;
        }
        for(student s:list){
            System.out.println(s);
        }
        }
        public static void searchStudent(){
            System.out.println("ENTER ID TO SEARCH");
            int id=sc.nextInt();
            sc.nextLine();
            Optional<student> s=list.stream().filter(st->st.getId()==id).findFirst();
            if(s.isPresent()){
                System.out.println("STUDENT FOUND:"+s.get());
            }else{
                System.out.println("STUDENT NOT FOUND");
            }


        }
        public static void updateStudent(){
            System.out.println("ENTER ID OF STUDENT FOR UPDATION");
            int id=sc.nextInt();
            sc.nextLine();
            Optional<student> s=list.stream().filter(st->st.getId()==id).findFirst();
            if(s.isPresent()){
                student student=s.get();
                System.out.println("ENTER NEW NAME:");
                student.setName(sc.nextLine());

                System.out.println("ENTER NEW AGE:");
                student.setAge(sc.nextInt());
                sc.nextLine();

                System.out.println("ENTER NEW COURSE:");
                student.setCourse(sc.nextLine());

                System.out.println("ENTER NEW MARKS:");
                student.setMarks(sc.nextDouble());
                sc.nextLine();

                System.out.println("STUDENT DETAILS UPDATED SUCCESSFULLY!!");

            }else {
                System.out.println("STUDENT NOT FOUND FOR ANY UPDATION");
            }
        }
        public static void deleteStudent(){
            System.out.println("ENTER ID OF STUDENT FOR DELETION:");
            int id=sc.nextInt();

            sc.nextLine();
            Optional<student> s=list.stream().filter(st->st.getId()==id).findFirst();
            if(s.isPresent()){
                list.remove(s.get());
                System.out.println("STUDENT DELETED SUCCESSFULLY");
            }else {
                System.out.println("STUDENT NOT FOUND!");
            }

        }
        public static void sortMenu(){
            System.out.println("Sort by 1.ID  2.NAME  3.MARKS");
            int choice=sc.nextInt();
            switch (choice){
                case 1:list.stream().sorted(Comparator.comparingInt(student::getId)).forEach(System.out::println);break;
                case 2:list.stream().sorted(Comparator.comparing(student::getName)).forEach(System.out::println);break;
                case 3:list.stream().sorted(Comparator.comparingDouble(student::getMarks)).forEach(System.out::println);break;
                default:
                    System.out.println("INVALID CHOICE");
            }

        }

    }

