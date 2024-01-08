package ru.job4j.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PointTest {
    @Test
    void when00to20then2() {
        double expected = 2;
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        double output = a.distance(b);
        assertThat(output).isEqualTo(expected, withPrecision(0.01));
    }

    @Test
    void when0Minus10to00then10() {
        double expected = 10;
        Point a = new Point(0, -10);
        Point b = new Point(0, 0);
        double output = a.distance(b);
        assertThat(output).isEqualTo(expected, withPrecision(0.01));
    }

    @Test
    void when00to100then100() {
        double expected = 100;
        Point a = new Point(0, 0);
        Point b = new Point(0, 100);
        double output = a.distance(b);
        assertThat(output).isEqualTo(expected, withPrecision(0.01));
    }

    @Test
    void when050to000then5() {
        double expected = 5;
        Point a = new Point(0, 5, 0);
        Point b = new Point(0, 0, 0);
        double output = a.distance(b);
        assertThat(output).isEqualTo(expected, withPrecision(0.01));
    }

    @Test
    void when000to1000then100() {
        double expected = 100;
        Point a = new Point(0, 0, 0);
        Point b = new Point(0, 100, 0);
        double output = a.distance(b);
        assertThat(output).isEqualTo(expected, withPrecision(0.01));
    }
}