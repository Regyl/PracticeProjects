package com.deepspace.concurrency.studentanalaytic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StudentGeneratorUtils {

    private static final Random RND = new Random(System.currentTimeMillis());

    private final static String[] firstNames = {"Sanjay", "Yunming", "John", "Vivek", "Shams", "Max"};
    private final static String[] lastNames = {"Chatterjee", "Zhang", "Smith", "Sarkar", "Imam", "Grossman"};

    public static Student[] generateStudentDataDefault() {
        final int N_STUDENTS = 2000000;
        final int N_CURRENT_STUDENTS = 600000;

        Student[] students = new Student[N_STUDENTS];

        for (int s = 0; s < N_STUDENTS; s++) {
            final String firstName = firstNames[RND.nextInt(firstNames.length)];
            final String lastName = lastNames[RND.nextInt(lastNames.length)];
            final double age = RND.nextDouble() * 100.0;
            final int grade = 1 + RND.nextInt(100);
            final boolean current = (s < N_CURRENT_STUDENTS);

            students[s] = new Student(firstName, lastName, age, grade, current);
        }

        return students;
    }

    public static Student[] generateInactiveStudentData() {
        final int N_STUDENTS = 2000000;

        Student[] students = new Student[N_STUDENTS];

        for (int s = 0; s < N_STUDENTS; s++) {
            final String firstName = firstNames[RND.nextInt(firstNames.length)];
            final String lastName = lastNames[RND.nextInt(lastNames.length)];
            final double age = RND.nextDouble() * 100.0;
            final int grade = 1 + RND.nextInt(100);
            final boolean current = Boolean.FALSE;

            students[s] = new Student(firstName, lastName, age, grade, current);
        }

        return students;
    }
}
