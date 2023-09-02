package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("ID")).isEqualTo("name");
    }

    @Test
    void whenException() {
        String path = "./data/exception.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                config::load
        );
        assertThat(exception.getMessage()).isEqualTo("Error");
    }

    @Test
    void whenLoadTrue() {
        String path = "./data/key.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Yuliya=");
    }

    @Test
    void whenValueToKey() {
        String path = "./data/value.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("age")).isEqualTo("22");
    }

    @Test
    void whenExceptionToKey() {
        String path = "./data/=values.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                config::load
        );
        assertThat(exception.getMessage()).isEqualTo("Error");
    }

    @Test
    void whenExceptionToValue() {
        String path = "./data/key=.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                config::load
        );
        assertThat(exception.getMessage()).isEqualTo("Error");
    }
}