package ru.job4j.io;

import java.io.*;
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

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(out)))) {
            data.forEach(output::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
