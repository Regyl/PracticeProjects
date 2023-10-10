package com.github.regyl.lab3.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Entity represents student.
 */
@Getter
public class StudentEntity extends AbstractEntity {
    
    private final String fio;
    
    private final FacultyEnum facultyEnum;
    
    private final Map<ExamEnum, Integer> gradeExamMap;
    
    public StudentEntity(Long id, String fio, FacultyEnum facultyEnum) {
        super(id);
        this.fio = fio;
        this.facultyEnum = facultyEnum;
        this.gradeExamMap = new HashMap<>();
    }
}
