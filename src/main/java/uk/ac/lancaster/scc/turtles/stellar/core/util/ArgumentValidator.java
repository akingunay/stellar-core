package uk.ac.lancaster.scc.turtles.stellar.core.util;

import java.util.Collection;

public class ArgumentValidator {

    public static void notEmpty(final String argument) {
        if (argument.isEmpty()) {
            throw new IllegalArgumentException("String argument cannot be empty.");
        }
    }
    
    public static<T> void notEmpty(final Collection<T> argument) {
        if (argument.isEmpty()) {
            throw new IllegalArgumentException("String argument cannot be empty.");
        }
    }
    
    
    public static<T> void notNull(final T argument) {
        if (argument == null) {
            throw new NullPointerException("Argument cannot be null.");
        }
    }
    
    
    
}
