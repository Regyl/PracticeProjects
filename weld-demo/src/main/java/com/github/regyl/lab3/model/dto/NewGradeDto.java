package com.github.regyl.lab3.model.dto;

import com.github.regyl.lab3.model.ExamEnum;
import com.github.regyl.lab3.model.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO with info about new student's grade.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewGradeDto {
    
    private StudentEntity student;
    
    private ExamEnum exam;
    
    private Integer grade;
}
