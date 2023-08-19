package ru.job4j.map;

import java.util.*;

public class College {
    private final Map<Student, Set<Subject>> students;

    public College(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Optional<Student> findByAccount(String account) {
        Optional<Student> rsl = Optional.empty();
        for (Student student : students.keySet()) {
            if (student.account().equals(account)) {
                rsl = Optional.of(student);
                break;
            }
        }
        return rsl;
    }

    public Optional<Subject> findBySubjectName(String account, String name) {
        Optional<Subject> rsl = Optional.empty();
        Optional<Student> stud = findByAccount(account);
        if (!stud.isEmpty()) {
            Set<Subject> subjects = students.get(stud.get());
            for (Subject subject : subjects) {
                if (subject.name().equals(name)) {
                    rsl = Optional.of(subject);
                }
            }
        }
        return rsl;
    }
}