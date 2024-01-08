package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void errorInfo() {
            System.out.println("Active: " + this.active);
            System.out.println("Status: " + this.status);
            System.out.println("Message: " + this.message);
    }

    public static void main(String[] args) {
        Error error1 = new Error();
        Error error2 = new Error(true, 1, "guy");
        Error error3 = new Error(false, 2, "superman");
        error1.errorInfo();
        error2.errorInfo();
        error3.errorInfo();

    }
}
