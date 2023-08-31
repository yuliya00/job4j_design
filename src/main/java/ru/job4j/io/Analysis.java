package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(target)) {
            boolean server = true;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] box = line.split(" ");
                if (server && ("400".equals(box[0]) || "500".equals(box[0]))) {
                    server = false;
                    writer.printf("%s;", box[1]);
                }
                if (!server && ("200".equals(box[0]) || "300".equals(box[0]))) {
                    server = true;
                    writer.printf("%s;%n", box[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
