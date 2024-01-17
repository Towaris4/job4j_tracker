package ru.job4j.polymorphism;

public class Bus implements Transport {
    int passengers = 0;

    @Override
    public void passengers(int passenger) {
        this.passengers = passenger;
    }

    @Override
    public void drive() {
        System.out.println("вж-вж");
    }

    @Override
    public int fillUp(int fuel) {
        return fuel * 50;
    }
}
