package com.github.regyl.lab3.service;

import com.github.regyl.lab3.model.ExamEnum;
import com.github.regyl.lab3.model.StudentEntity;
import com.github.regyl.lab3.model.dto.NewGradeDto;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;

/**
 * Service to manipulate student's grades.
 */
@Default
public class GradeManager {
    
    private final Event<NewGradeDto> events;
    
    @Inject
    public GradeManager(Event<NewGradeDto> events) {
        this.events = events;
    }
    
    /**
     * Add grade to student's entity and fire event.
     *
     * @param newGradeDto DTO with info about student and it's new grade
     */
    public void add(NewGradeDto newGradeDto) {
        StudentEntity student = newGradeDto.getStudent();
        ExamEnum examEnum = newGradeDto.getExam();
        int grade = newGradeDto.getGrade();
        
        student.getGradeExamMap().put(examEnum, grade);
        events.fire(newGradeDto);
    }

}
