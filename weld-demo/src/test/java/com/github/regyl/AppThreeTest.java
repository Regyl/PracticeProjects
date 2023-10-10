package com.github.regyl;

import com.github.regyl.lab3.AppThree;
import com.github.regyl.lab3.model.ExamEnum;
import com.github.regyl.lab3.model.FacultyEnum;
import com.github.regyl.lab3.model.StudentEntity;
import com.github.regyl.lab3.model.dto.NewGradeDto;
import com.github.regyl.lab3.service.GradeEventHandler;
import com.github.regyl.lab3.service.GradeManager;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AppThreeTest {
    
    @AfterMethod
    public void after() {
        getGradeEventHandler().reset();
    }
    
    @Test
    public void shouldReturnEmptyResultOnDidntPassedExams() {
        GradeManager gradeManager = getGradeManager();
        GradeEventHandler gradeEventHandler = getGradeEventHandler();
        NewGradeDto newGradeDto = NewGradeDto.builder()
                .student(generateStudent(1L))
                .grade(5)
                .exam(ExamEnum.MATHEMATICS)
                .build();
        gradeManager.add(newGradeDto);
        
        var result = gradeEventHandler.getAcceptedStudents();
        
        assertThat(result).hasSize(FacultyEnum.values().length);
        assertThat(result)
                .allMatch(item -> item.getRight() == null || item.getRight().isEmpty());
    }
    
    @Test
    public void shouldReturnStudentWithPassedTargetExams() {
        GradeManager gradeManager = getGradeManager();
        GradeEventHandler gradeEventHandler = getGradeEventHandler();
        StudentEntity student = generateStudent(1L);
        NewGradeDto math = new NewGradeDto(student, ExamEnum.MATHEMATICS, 5);
        NewGradeDto algebra = new NewGradeDto(student, ExamEnum.LINEAR_ALGEBRA, 5);
        NewGradeDto physics = new NewGradeDto(student, ExamEnum.PHYSICS, 5);
        for (var grade : Arrays.asList(math, algebra, physics)) {
            gradeManager.add(grade);
        }
        
        var result = gradeEventHandler.getAcceptedStudents();
        
        assertThat(result).hasSize(FacultyEnum.values().length);
        Optional<List<StudentEntity>> optionalAcceptedCsStudents = result.stream()
                .filter(item -> FacultyEnum.DEPARTMENT_OF_CS == item.getLeft())
                .map(Pair::getRight)
                .findFirst();
        assertThat(optionalAcceptedCsStudents).isPresent();
        assertThat(optionalAcceptedCsStudents.get()).containsExactly(student);
    }
    
    @Test
    public void shouldChooseStudentsWithHighestAverageGrade() {
        GradeManager gradeManager = getGradeManager();
        GradeEventHandler gradeEventHandler = getGradeEventHandler();
        
        //Create three students with all passed exams
        StudentEntity student = generateStudent(1L);
        NewGradeDto math = new NewGradeDto(student, ExamEnum.MATHEMATICS, 5);
        NewGradeDto algebra = new NewGradeDto(student, ExamEnum.LINEAR_ALGEBRA, 5);
        NewGradeDto physics = new NewGradeDto(student, ExamEnum.PHYSICS, 5);
        for (var grade : Arrays.asList(math, algebra, physics)) {
            gradeManager.add(grade);
        }
        
        StudentEntity student2 = generateStudent(2L);
        NewGradeDto math2 = new NewGradeDto(student2, ExamEnum.MATHEMATICS, 5);
        NewGradeDto algebra2 = new NewGradeDto(student2, ExamEnum.LINEAR_ALGEBRA, 5);
        NewGradeDto physics2 = new NewGradeDto(student2, ExamEnum.PHYSICS, 4);
        for (var grade : Arrays.asList(math2, algebra2, physics2)) {
            gradeManager.add(grade);
        }
        
        StudentEntity student3 = generateStudent(3L);
        NewGradeDto math3 = new NewGradeDto(student3, ExamEnum.MATHEMATICS, 5);
        NewGradeDto algebra3 = new NewGradeDto(student3, ExamEnum.LINEAR_ALGEBRA, 5);
        NewGradeDto physics3 = new NewGradeDto(student3, ExamEnum.PHYSICS, 3); //lowest avg grade
        for (var grade : Arrays.asList(math3, algebra3, physics3)) {
            gradeManager.add(grade);
        }
        
        var result = gradeEventHandler.getAcceptedStudents();
        
        Optional<List<StudentEntity>> optionalAcceptedCsStudents = result.stream()
                .filter(item -> FacultyEnum.DEPARTMENT_OF_CS == item.getLeft())
                .map(Pair::getRight)
                .findFirst();
        assertThat(optionalAcceptedCsStudents).isPresent();
        assertThat(optionalAcceptedCsStudents.get()).containsExactlyElementsOf(Arrays.asList(student, student2));
    }
    
    @Test
    public void shouldBeAbleToFindBothStudentsInDifferentFaculties() {
        GradeManager gradeManager = getGradeManager();
        GradeEventHandler gradeEventHandler = getGradeEventHandler();
        
        StudentEntity student1 = new StudentEntity(1L, "Novikov Eugene Alekseevich", FacultyEnum.DEPARTMENT_OF_CS);
        NewGradeDto math1 = new NewGradeDto(student1, ExamEnum.MATHEMATICS, 5);
        NewGradeDto algebra1 = new NewGradeDto(student1, ExamEnum.LINEAR_ALGEBRA, 5);
        NewGradeDto physics1 = new NewGradeDto(student1, ExamEnum.PHYSICS, 5);
        for (var grade : Arrays.asList(math1, algebra1, physics1)) {
            gradeManager.add(grade);
        }
        
        StudentEntity student2 = new StudentEntity(2L, "Dolbitsin Vladimir Sergeevich", FacultyEnum.DEPARTMENT_OF_BUSINESS_ADMINISTRATION);
        NewGradeDto math2 = new NewGradeDto(student2, ExamEnum.MATHEMATICS, 5);
        NewGradeDto algebra2 = new NewGradeDto(student2, ExamEnum.LINEAR_ALGEBRA, 5);
        NewGradeDto administration2 = new NewGradeDto(student2, ExamEnum.ADMINISTRATION, 4);
        for (var grade : Arrays.asList(math2, algebra2, administration2)) {
            gradeManager.add(grade);
        }
        
        var result = gradeEventHandler.getAcceptedStudents();
        
        var optionalCsFacultyStudents = result.stream()
                .filter(item -> FacultyEnum.DEPARTMENT_OF_CS == item.getLeft())
                .map(Pair::getRight)
                .findFirst();
        assertThat(optionalCsFacultyStudents).isPresent();
        assertThat(optionalCsFacultyStudents.get()).containsExactly(student1);
        var optionalAdmFacultyStudents = result.stream()
                .filter(item -> FacultyEnum.DEPARTMENT_OF_BUSINESS_ADMINISTRATION == item.getLeft())
                .map(Pair::getRight)
                .findFirst();
        assertThat(optionalAdmFacultyStudents).isPresent();
        assertThat(optionalAdmFacultyStudents.get()).containsExactly(student2);
    }
    
    private GradeManager getGradeManager() {
        return AppThree.WELD_CONTAINER.instance()
                .select(GradeManager.class)
                .get();
    }
    
    private GradeEventHandler getGradeEventHandler() {
        return AppThree.WELD_CONTAINER.instance()
                .select(GradeEventHandler.class)
                .get();
    }
    
    private StudentEntity generateStudent(Long id) {
        return new StudentEntity(id, "Novikov Eugene Alekseevich", FacultyEnum.DEPARTMENT_OF_CS);
    }
}
