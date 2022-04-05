package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    // Console scanner
    public static Scanner scanner = new Scanner(System.in);
    // Create a new Hash map for to store students information and load from routine
    public static HashMap<Integer, Student> studentInfoMap = new HashMap<Integer, Student>();
    public static boolean mainMenu = true;


    //************************************
    // ####### Main function ########
    //************************************
    public static void main(String[] args) throws IOException {

        loadFile(); // converts student Info file into Hashmap

        // ### Main Menu ###
        while (mainMenu) {
            System.out.println("###### Main Menu ######");
            System.out.println("#1 Add new Student");
            System.out.println("#2 Access current student information");
            System.out.println("#3 Exit Application");

            try {
                int selection = scanner.nextInt();
                scanner.nextLine();

                if (selection == 1) {
                    // #### Sub menu - 1 ####
                    System.out.print("Enter new student ID : ");
                    Student student = new Student(scanner.nextInt());
                    scanner.nextLine();

                    // Checks if new student ID exists in database and asks user for other information
                    if (!studentInfoMap.containsKey(student.getStudentId())) {
                        System.out.print("Enter student first name : ");
                        student.setFirstName(scanner.nextLine());
                        System.out.print("Enter student last name : ");
                        student.setLastName(scanner.nextLine());
                        System.out.print("Enter student program name : ");
                        student.setProgramName(scanner.nextLine());
                        studentInfoMap.put(student.getStudentId(), student); // Add student information to the Hashmap
                        System.out.println("Student information added to database\n");
                    } else {
                        System.out.println("Student Id already exists in database\n");
                    }

                } else if (selection == 2) {

                    // #### Sub menu - 2 ####
                    System.out.print("Enter Student ID : ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println();
                    subMenu2(studentId);  // Calling sub menu 2 routine

                } else if (selection == 3) {
                    mainMenu = false;
                } else {
                    System.out.println("Invalid entry, try again\n");
                }

            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid Entry, try again\n");
            }
        }
        writeToFile();  // writes updated hashmap to student info file

    }


    //************************************
    // ####### Sub menu 2 Routine ########
    //************************************

    public static void subMenu2(int studentId) {
        // Check if student ID exists in database
        if (studentInfoMap.containsKey(studentId)) {
            Student student = studentInfoMap.get(studentId);
            boolean menu2 = true;
            while (menu2) {
                System.out.println("###### Sub Menu ######");
                System.out.println("#1 Add courses for a student");
                System.out.println("#2 Get student info");
                System.out.println("#3 Update Student current gpa");
                System.out.println("#4 Update student tuition fee");
                System.out.println("#5 Goto previous menu");
                System.out.println("#6 Exit application");
                int selection = scanner.nextInt();
                scanner.nextLine();

                switch (selection) {
                    case (1): {
                        // #1 Add courses for a student
                        System.out.println("Enter course name to add : ");
                        student.addCourse(scanner.nextLine());
                        System.out.println("Updated " + student.getFirstName() + "'s course list :");
                        student.printCourseList();
                        System.out.println();
                        break;
                    }
                    case (2): {
                        // #2 Get student info
                        student.printStudentInfo();
                        System.out.println();
                        break;
                    }

                    case (3): {
                        // #3 Update Student current gpa
                        System.out.println("Enter " + student.getFirstName() + "'s new gpa : ");
                        student.updateGpa(scanner.nextFloat());
                        scanner.nextLine();
                        System.out.println("successfully updated " + student.getFirstName() + "'s gpa\n");
                        break;
                    }

                    case (4): {
                        // #4 Update student tuition fee
                        System.out.println("Enter " + student.getFirstName() + "'s new tuition fee : ");
                        student.updateTuitionFee(scanner.nextDouble());
                        System.out.println("successfully updated " + student.getFirstName() + "'s tuition fee\n");
                        break;
                    }

                    case (5): {
                        // #5 Goto previous menu;
                        System.out.println();
                        menu2 = false;
                        break;
                    }
                    case (6): {
                        // #6 Exit application;
                        System.out.println();
                        menu2 = false;
                        mainMenu = false;
                        break;
                    }
                    default: {
                        System.out.println("Invalid Entry, try again\n");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Student ID does not exist in database\n");
        }

    }


    //**********************************************
    // ####### Saving student info file ########
    //**********************************************


    public static void writeToFile() {
        try {
            FileWriter writer = new FileWriter("studentsInfo.txt");
            for (Student student : studentInfoMap.values()) {
                writer.write(student.studentToString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //**********************************************
    // ####### Loading student info file ########
    //**********************************************

    public static void loadFile() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("studentsInfo.txt"));
            String line = reader.readLine();
            while (line != null) {
                Student student = Student.stringToStudent(line);
                studentInfoMap.put(student.getStudentId(), student);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
