package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkValidateDoesNotContainTheSymbol() {
        NameLoad nameLoad = new NameLoad();
        String word = "sun:one";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("symbol '='")
                .hasMessageContaining(word)
                .hasMessageContaining("sun:one");
    }

    @Test
    void checkValidateDoesNotContainAKey() {
        NameLoad nameLoad = new NameLoad();
        String word = "=sun";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(word)
                .hasMessageContaining("=sun");
    }

    @Test
    void checkValidateDoesNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        String word = "sun=";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a value")
                .hasMessageContaining(word)
                .hasMessageContaining("sun=");
    }

    @Test
    void checkParseIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }
}