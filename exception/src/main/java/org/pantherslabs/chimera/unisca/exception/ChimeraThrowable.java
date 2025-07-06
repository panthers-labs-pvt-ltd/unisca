package org.pantherslabs.chimera.unisca.exception;

import java.util.Map;
public interface ChimeraThrowable {

    // Abstract method to get the error class
    String getErrorClass();

    // Default method to get SQL state
    default String getSqlState() {
        return ThrowableHelper.getSqlState(this.getErrorClass());
    }

    // Default method to check if the error is internal
    default boolean isInternalError() {
        return ThrowableHelper.isInternalError(this.getErrorClass());
    }

    // Abstract method to get message parameters
    Map<String, String> getMessageParameters();

    String getMessage();
}
