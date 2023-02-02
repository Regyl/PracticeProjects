package com.deepspace.concurrency.studentanalaytic;

import lombok.Data;

/**
 * A class representing a single student in a single class.
 */
@Data
public final class Student {

    /**
     * First name of the student.
     */
    private final String firstName;
    /**
     * Surname of the student.
     */
    private final String lastName;
    /**
     * Age of the student.
     */
    private final double age;
    /**
     * Grade the student has received in the class so far.
     */
    private final int grade;
    /**
     * Whether the student is currently enrolled, or has already completed the
     * course.
     */
    private final boolean isCurrent;
}
