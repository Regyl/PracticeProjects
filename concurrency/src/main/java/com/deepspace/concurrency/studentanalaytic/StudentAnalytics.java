package com.deepspace.concurrency.studentanalaytic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * A simple wrapper class for various analytics methods.
 */
public final class StudentAnalytics {

    /**
     * Sequentially computes the average age of all actively enrolled students
     * using loops.
     *
     * @param studentArray Student data for the class.
     * @return Average age of enrolled students
     */
    public double averageAgeOfEnrolledStudentsImperative(final Student[] studentArray) {
        List<Student> activeStudents = new ArrayList<>();

        for (Student s : studentArray) {
            if (s.isCurrent()) {
                activeStudents.add(s);
            }
        }

        double ageSum = 0.0;
        for (Student s : activeStudents) {
            ageSum += s.getAge();
        }

        return ageSum / (double) activeStudents.size();
    }

    /**
     * Computes average students age, but in parallel.
     *
     * @param studentArray Student data for the class.
     * @return Average age of enrolled students
     */
    public double averageAgeOfEnrolledStudentsParallelStream(final Student[] studentArray) {
        return Stream.of(studentArray).parallel()
                .filter(Student::isCurrent)
                .mapToDouble(Student::getAge)
                .average()
                .orElse(Double.NaN);
    }

    /**
     * Sequentially computes the most common first name out of all students that
     * are no longer active in the class using loops.
     *
     * @param studentArray Student data for the class.
     * @return Most common first name of inactive students
     */
    public String mostCommonFirstNameOfInactiveStudentsImperative(final Student[] studentArray) {
        List<Student> inactiveStudents = new ArrayList<>();

        for (Student s : studentArray) {
            if (!s.isCurrent()) {
                inactiveStudents.add(s);
            }
        }

        Map<String, Integer> nameCounts = new HashMap<>();

        for (Student s : inactiveStudents) {
            if (nameCounts.containsKey(s.getFirstName())) {
                nameCounts.put(s.getFirstName(), nameCounts.get(s.getFirstName()) + 1);
            } else {
                nameCounts.put(s.getFirstName(), 1);
            }
        }

        String mostCommon = null;
        int mostCommonCount = -1;
        for (Map.Entry<String, Integer> entry : nameCounts.entrySet()) {
            if (mostCommon == null || entry.getValue() > mostCommonCount) {
                mostCommon = entry.getKey();
                mostCommonCount = entry.getValue();
            }
        }

        return mostCommon;
    }

    /**
     * Computes most common name in parallel.
     *
     * @param studentArray Student data for the class.
     * @return Most common first name of inactive students
     */
    public String mostCommonFirstNameOfInactiveStudentsParallelStream(final Student[] studentArray) {
        ConcurrentMap<String, Integer> firstNameCounter = new ConcurrentHashMap<>(7, 1);

        AtomicInteger commonNameCount = new AtomicInteger(0);
        AtomicReference<String> commonNameReference = new AtomicReference<>();

        Stream.of(studentArray).parallel()
                .filter(s -> !s.isCurrent())
                .forEach(student -> {
                    String currentFirstName = student.getFirstName();

                    int newValue;
                    synchronized (firstNameCounter) { //FIXME erase synchronized
                        newValue = firstNameCounter.getOrDefault(currentFirstName, 0) + 1;
                        firstNameCounter.put(currentFirstName, newValue);
                    }

                    if (newValue > commonNameCount.get()) {
                        commonNameCount.set(newValue);
                        commonNameReference.set(currentFirstName);
                    }
                });
        return commonNameReference.get();
    }

    /**
     * Sequentially computes the number of students who have failed the course
     * who are also older than 20 years old. A failing grade is anything below a
     * 65. A student has only failed the course if they have a failing grade and
     * they are not currently active.
     *
     * @param studentArray Student data for the class.
     * @return Number of failed grades from students older than 20 years old.
     */
    public int countNumberOfFailedStudentsOlderThan20Imperative(final Student[] studentArray) {
        int count = 0;
        for (Student s : studentArray) {
            if (!s.isCurrent() && s.getAge() > 20 && s.getGrade() < 65) {
                count++;
            }
        }
        return count;
    }

    /**
     * Computes number of inactive students in parallel.
     *
     * @param studentArray Student data for the class.
     * @return Number of failed grades from students older than 20 years old.
     */
    public int countNumberOfFailedStudentsOlderThan20ParallelStream(final Student[] studentArray) {
        return (int) Stream.of(studentArray).parallel()
                .filter(s -> !s.isCurrent() && s.getAge() > 20 && s.getGrade() < 65)
                .count();
    }
}
