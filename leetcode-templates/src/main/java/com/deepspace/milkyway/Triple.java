package com.deepspace.milkyway;

public class Triple<T, S, R> {
    T first;
    
    S second;
    
    R third;
    
    public Triple() {}
    
    public Triple(T first, S second, R third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}