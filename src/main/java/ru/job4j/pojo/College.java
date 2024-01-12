package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setDateOfReceipt("10.10.1998");
        student.setFullName("Tarasov Ivan Vladimirovich");
        student.setGroup("SUZ.12");
        System.out.println(student.getFullName());
        System.out.println(student.getGroup());
        System.out.println(student.getDateOfReceipt());
    }
}
