package com.github.regyl;

import com.github.regyl.lab1.ProxySolver;
import lombok.Getter;
import lombok.Setter;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

@Getter
@Setter
public class App {
    
    private static Weld weld;
    private static WeldContainer container;
    
    public static void main(String[] args) {
        weld = new Weld();
        container = weld.initialize();
        
        ProxySolver proxySolver = container.instance()
                .select(ProxySolver.class)
                .get();
        
        String firstEquation = "2x*3y=10";
        String secondEquation = "3x+5y=30";
        proxySolver.solve(firstEquation, secondEquation);
    }
}
