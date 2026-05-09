package ru.job4j.tracker.action;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.Stub;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteTest {
    @Test
    public void whenItemWasDeletedSuccessfully() {
        Output output = new Stub();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        String deletedName = "New item name";
        Delete deleteAction = new Delete(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(deletedName);
        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Удаление заявки ===" + ln
                        + "Заявка удалена успешно." + ln
        );
    }
}