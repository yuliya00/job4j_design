package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> newLog = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("data/log.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(" ");
                if ("404".equals(parts[parts.length - 2])) {
                    newLog.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newLog;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
