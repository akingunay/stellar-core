package uk.ac.lancaster.scc.turtles.stellar.core.util;

public class StringBuilderHelper {

    private StringBuilderHelper() {
    }

    /**
     * If there is a suffix of the builder that is equal to the stringToRemove, 
     * the suffix of the builder is deleted. In any other case, the builder is
     * returned without a change.
     * 
     * 
     * @param builder builder whose suffix to be trimmed
     * @param stringToRemove the suffix to trim
     * @return trimmed builder
     */
    public static StringBuilder trimSuffix(final StringBuilder builder, final String stringToRemove) {
        if (builder.toString().regionMatches(builder.length() - stringToRemove.length(), stringToRemove, 0, stringToRemove.length())) {
            return builder.delete(builder.length() - stringToRemove.length(), builder.length());
        } else {
            return builder;
        }
    }
    
}
