package com.github.regyl.lab3;

import com.github.regyl.lab3.model.StudentEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Comparator;

/**
 * Comparator to compare students by their average grade {@link Pair#getRight()}.
 */
public class StudentRateComparator implements Comparator<Pair<StudentEntity, Double>> {
    
    @Override
    public int compare(Pair<StudentEntity, Double> o1, Pair<StudentEntity, Double> o2) {
        return o2.getRight().compareTo(o1.getRight());
    }
}
