package com.company;

import java.util.ArrayList;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private double tuitionFee;
    private ArrayList<String> courses = new ArrayList<>();
    private String programName;
    private float currentGpa;


    // Constructor for student class
    public Student(int studentId) {
        this.studentId = studentId;
    }

    public Student(int studentId, String firstName, String lastName, double tuitionFee, ArrayList<String> courses, String programName, float currentGpa) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tuitionFee = tuitionFee;
        this.courses = courses;
        this.programName = programName;
        this.currentGpa = currentGpa;
    }

    public Student(int studentId, String firstName, String lastName, double tuitionFee, String programName, float currentGpa) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tuitionFee = tuitionFee;
        this.programName = programName;
        this.currentGpa = currentGpa;
    }



    // setters


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void updateTuitionFee(double tuitionFee){
        this.tuitionFee = tuitionFee;
    }

    public void addCourse(String course){
        this.courses.add(course);
    }

    public void updateGpa(float currentGpa){
        this.currentGpa = currentGpa;
    }

    // Getters

    public int getStudentId(){
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public String getProgramName() {
        return programName;
    }

    public float getCurrentGpa() {
        return currentGpa;
    }

    public ArrayList<String> getCourseList(){
        return courses;
    }

    // Prints

    public void printCourseList(){
        System.out.println("\tStudent course List: ");
        for(int i = 0; i<courses.size();i++){
            System.out.println("\t\t\t\t" + courses.get(i));
        }
    }

    public void printStudentInfo(){
        System.out.println("\tStudent ID:\n\t\t\t\t" + studentId);
        System.out.println("\tName:\t\t\t\t" + firstName + " " + lastName);
        System.out.println("\tTuition Fee :\t\t\t\t$" + tuitionFee);
        System.out.println("\tProgram:\t\t\t\t" + programName);
        System.out.println("\tCurrent GPA:\t\t\t\t" + currentGpa + "/4.0");
        printCourseList();
    }

    // Converts all contents of Student object to string

    public String studentToString(){
        String str = studentId + "-" + firstName + "-" + lastName + "-" + programName + "-" +
                tuitionFee+ "-" + currentGpa;
        for (int i =0;i<courses.size();i++){
            str += "-"+courses.get(i);
        }
        return str;
    }

    // Converts String to a student object
    public static Student stringToStudent(String str){
        Student student;
        String[] strArray = str.split("-");
        student = new Student(Integer.parseInt(strArray[0]));
        student.setFirstName(strArray[1]);
        student.setLastName(strArray[2]);
        student.setProgramName(strArray[3]);
        student.updateTuitionFee(Double.parseDouble(strArray[4]));
        student.updateGpa(Float.parseFloat(strArray[5]));
        if (strArray.length >6){
            for (int i =6; i < strArray.length;i++){
                student.addCourse(strArray[i]);
            }
        }
        return student;
    }


}
