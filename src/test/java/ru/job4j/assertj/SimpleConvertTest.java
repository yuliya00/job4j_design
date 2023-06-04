package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Index;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("sun", "moon", "space", "star");
        assertThat(list).hasSize(4)
                .contains("sun", "star")
                .doesNotContain("Earth")
                .anySatisfy(e -> {
                    assertThat(e).contains("s");
                    assertThat(e).hasSize(4);
                })
                .element(2).isNotNull().isEqualTo("space");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("sun", "moon", "space", "star", "moon", "Earth", "Star");
        assertThat(set).isNotNull()
                .hasSize(6)
                .contains("moon")
                .startsWith("Earth")
                .allMatch(e -> e.length() >= 3)
                .anyMatch(e -> e.length() == 4)
                .last().isNotNull().isEqualTo("space");
        assertThat(set).filteredOn(e -> e.length() > 4).last().isEqualTo("space");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("sun", "moon", "space", "star");
        assertThat(map).isNotNull()
                .hasSize(4)
                .containsKeys("sun", "moon")
                .containsValues(2, 3, 0)
                .containsEntry("moon", 1);
    }
}