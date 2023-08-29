package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream("data/dataresult.txt")))) {
            out.println("Hello, world!");
            for (int i = 1; i < 2; i++) {
                for (int j = 1; j < 11; j++) {
                    String line = i + "*" + j + "=" + i * j;
                    out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
