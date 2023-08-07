package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 1, 4);
        ListUtils.addBefore(input, 1, 8);
        ListUtils.addBefore(input, 2, 7);
        assertThat(input).hasSize(6).containsSequence(1, 8, 7, 3, 4, 2);
        ListUtils.removeIf(input, x -> x % 2 == 0);
        assertThat(input).hasSize(3).containsSequence(1, 7, 3);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addBefore(input, 2, 8);
        ListUtils.replaceIf(input, x -> x % 2 == 0, 5);
        assertThat(input).hasSize(4).containsSequence(1, 3, 5, 5);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> rsl = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addBefore(input, 2, 8);
        ListUtils.removeAll(input, rsl);
        assertThat(input).hasSize(2).containsSequence(8, 2);
    }
}