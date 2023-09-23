package com.github.regyl;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * Abstract application class for encapsulate Weld's bean container.
 */
public abstract class AbstractApp {
    
    protected static final Weld WELD;
    protected static final WeldContainer WELD_CONTAINER;
    
    /**
     * Initialization of Weld's bean container.
     */
    static {
        WELD = new Weld();
        WELD_CONTAINER = WELD.initialize();
    }
}
