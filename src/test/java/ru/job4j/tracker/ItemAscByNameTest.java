package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemAscByNameTest {

    @Test
    void whenAscByNameSuccesfull() {
        Item one = new Item("Cbr");
        Item two = new Item("Abr");
        Item three = new Item("Ebr");
        Item four = new Item("Bbr");
        List<Item> items = Arrays.asList(one, two, three, four);
        items.sort(new ItemAscByName());
        List<Item> expected = Arrays.asList(
                two, four, one, three);
        assertThat(items).isEqualTo(expected);
    }

    @Test
    void whenDescByNameSuccesfull() {
        Item one = new Item("Cbr");
        Item two = new Item("Abr");
        Item three = new Item("Ebr");
        Item four = new Item("Bbr");
        List<Item> items = Arrays.asList(one, two, three, four);
        items.sort(new ItemDescByName());
        List<Item> expected = Arrays.asList(
        three, one, four, two);
        assertThat(items).isEqualTo(expected);
    }
}