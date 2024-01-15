package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("Ball story", 10);
        books[1] = new Book("war and world", 2000);
        books[2] = new Book("Clean code", 500);
        books[3] = new Book("red hat", 30);
        books[2].setName("Clean code");
        for (int index = 0; index < books.length; index++) {
            System.out.println(books[index].getName() + " - " + books[index].getPages());
        }
        System.out.println();
        Book swap = books[0];
        books[0] = books[3];
        books[3] = swap;
        for (int index = 0; index < books.length; index++) {
            System.out.println(books[index].getName() + " - " + books[index].getPages());
        }
        System.out.println();
        for (int index = 0; index < books.length; index++) {
            Book book = books[index];
            if ("Clean code".equals(book.getName())) {
                System.out.println(book.getName() + " - " + book.getPages());
            }
        }
    }
}
