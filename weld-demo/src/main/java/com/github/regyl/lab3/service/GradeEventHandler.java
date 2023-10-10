package com.github.regyl.lab3.service;

import com.github.regyl.lab3.StudentRateComparator;
import com.github.regyl.lab3.model.ExamEnum;
import com.github.regyl.lab3.model.FacultyEnum;
import com.github.regyl.lab3.model.StudentEntity;
import com.github.regyl.lab3.model.dto.NewGradeDto;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Default;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handler for new grade events.
 */
@Slf4j
@Default
public class GradeEventHandler {
    
    private static Map<FacultyEnum, List<Pair<StudentEntity, Double>>> studentFacultyMap;
    
    static {
        studentFacultyMap = new HashMap<>();
        
        Arrays.stream(FacultyEnum.values())
                .forEach(faculty -> studentFacultyMap.put(faculty, new ArrayList<>()));
    }
    
    /**
     * Add grade to student's exam.
     *
     * @param newGradeDto       DTO with info about grade, passed exam and student
     */
    public void addStudentGrade(@Observes NewGradeDto newGradeDto) {
        StudentEntity student = newGradeDto.getStudent();
        if (!validate(student)) {
            return;
        }
        
        double avgGrade = countAvgGrade(student);
        FacultyEnum faculty = newGradeDto.getStudent().getFacultyEnum();
        var studentRate = studentFacultyMap.get(faculty);
        studentRate.add(new ImmutablePair<>(student, avgGrade));
    }
    
    /**
     * Acquire all faculties with accepted students.
     *
     * @return  all faculties with accepted students
     */
    public List<Pair<FacultyEnum, List<StudentEntity>>> getAcceptedStudents() {
        List<Pair<FacultyEnum, List<StudentEntity>>> result = new ArrayList<>();
        StudentRateComparator comparator = new StudentRateComparator();
        
        for (FacultyEnum faculty : FacultyEnum.values()) {
            int maxStudents = faculty.getMaxStudents();
            var studentRate = studentFacultyMap.get(faculty);
            
            List<StudentEntity> acceptedStudents = studentRate.stream()
                    .sorted(comparator)
                    .limit(maxStudents)
                    .map(Pair::getLeft)
                    .toList();
            result.add(new ImmutablePair<>(faculty, acceptedStudents));
        }
        
        return result;
    }
    
    /**
     * Reset student's grades.
     */
    public void reset() {
        studentFacultyMap = new HashMap<>();
        
        Arrays.stream(FacultyEnum.values())
                .forEach(faculty -> studentFacultyMap.put(faculty, new ArrayList<>()));
    }
    
    /**
     * Does student pass all exams.
     *
     * @param student   student's info
     * @return          does student ready to be included in general rate
     */
    private boolean validate(StudentEntity student) {
        var gradeMap = student.getGradeExamMap();
        
        return gradeMap != null && gradeMap.keySet().containsAll(student.getFacultyEnum().getRequiredExams());
    }
    
    /**
     * Count average student's grade.
     *
     * @param student   student entity
     * @return          student's average grade
     */
    private double countAvgGrade(StudentEntity student) {
        FacultyEnum faculty = student.getFacultyEnum();
        
        int result = 0;
        var gradeMap = student.getGradeExamMap();
        for (ExamEnum exam : faculty.getRequiredExams()) {
            int grade = gradeMap.get(exam);
            result += grade;
        }
        
        return ((double) result) / faculty.getRequiredExams().size();
    }
}
