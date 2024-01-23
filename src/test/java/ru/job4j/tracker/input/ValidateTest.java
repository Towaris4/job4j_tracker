package ru.job4j.tracker.input;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.Mock;
import ru.job4j.tracker.input.Validate;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.Stub;

import static org.assertj.core.api.Assertions.assertThat;

    class ValidateTest {

        @Test
        void whenInvalidInput() {
            Output output = new Stub();
            Input in = new Mock(
                    new String[] {"one", "two1", "1"}
            );
            Validate input = new Validate(output, in);
            int selected = input.askInt("Enter menu: ");
            assertThat(selected).isEqualTo(1);
        }

        @Test
        void whenValidInput() {
            Output output = new Stub();
            Input in = new Mock(
                    new String[] {"7"}
            );
            Validate input = new Validate(output, in);
            int selected = input.askInt("Enter menu: ");
            assertThat(selected).isEqualTo(7);
        }

        @Test
        void whenValidInputMinus7() {
            Output output = new Stub();
            Input in = new Mock(
                    new String[] {"-7"}
            );
            Validate input = new Validate(output, in);
            int selected = input.askInt("Enter menu: ");
            assertThat(selected).isEqualTo(-7);
        }

        @Test
        void whenValidInputMulti() {
            Output output = new Stub();
            Input in = new Mock(
                    new String[] {"-7", "1", "2", "6"}
            );
            Validate input = new Validate(output, in);
            int selected = input.askInt("Enter menu: ");
            assertThat(selected).isEqualTo(-7);
            selected = input.askInt("Enter menu: ");
            assertThat(selected).isEqualTo(1);
            selected = input.askInt("Enter menu: ");
            assertThat(selected).isEqualTo(2);
            selected = input.askInt("Enter menu: ");
            assertThat(selected).isEqualTo(6);
        }
    }