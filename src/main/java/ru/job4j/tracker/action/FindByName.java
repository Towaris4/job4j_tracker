package ru.job4j.tracker.action;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.output.Output;

import java.util.List;

public class FindByName implements UserAction {

    private final Output output;

    public FindByName(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Показать заявку по имени";
    }

    @Override
    public boolean execute(Input input, Store store) {
        output.println("=== Вывод заявок по имени ===");
        String name = input.askStr("Enter name: ");
        List<Item> items = store.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                output.println(item);
            }
        } else {
            output.println("Заявки с именем: " + name + " не найдены.");
        }
        return true;
    }
}
