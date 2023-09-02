package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void whenTestOne(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.log").toFile();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "500 10:59:01\n"
                    + "400 11:01:02\n"
                    + "300 11:02:02");

        }
        File target = tempDir.resolve("target.csv").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;11:02:02;").hasToString(rsl.toString());
    }

    @Test
    void whenTestTwo(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.log").toFile();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "300 10:59:01\n"
                    + "500 11:01:02\n"
                    + "200 11:02:02");

        }
        File target = tempDir.resolve("target.csv").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").hasToString(rsl.toString());
    }
}