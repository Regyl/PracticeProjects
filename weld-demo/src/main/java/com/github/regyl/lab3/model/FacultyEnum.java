package com.github.regyl.lab3.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * Allowed faculties.
 */
@Getter
@RequiredArgsConstructor
public enum FacultyEnum {

    DEPARTMENT_OF_BUSINESS_ADMINISTRATION(List.of(ExamEnum.MATHEMATICS, ExamEnum.LINEAR_ALGEBRA, ExamEnum.ADMINISTRATION), 7),
    
    DEPARTMENT_OF_CS(List.of(ExamEnum.MATHEMATICS, ExamEnum.LINEAR_ALGEBRA, ExamEnum.PHYSICS), 2);
    
    /**
     * Exams, required to get to the faculty.
     */
    private final Collection<ExamEnum> requiredExams;
    
    /**
     * Faculty's max students quantity.
     */
    private final int maxStudents;
    
}
