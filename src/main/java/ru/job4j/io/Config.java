package ru.job4j.io;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("#") && !line.isEmpty() && !(line.matches(".+=.+"))
                        && (line.startsWith("=") || line.indexOf("=") == line.length() - 1 || !line.contains("="))) {
                    throw new IllegalArgumentException("Error");
                }
                if (line.matches(".+=.+")) {
                    int i = line.indexOf('=');
                    values.put(line.substring(0, i), line.substring(i + 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
