package com.deepspace.milkyway;

public class Tuple<T, S> {
    T first;
    
    S second;
    
    public Tuple() {}
    
    public Tuple(T first, S second) {
        this.first = first;
        this.second = second;
    }
}