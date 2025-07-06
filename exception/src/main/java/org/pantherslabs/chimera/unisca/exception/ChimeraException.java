package org.pantherslabs.chimera.unisca.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class ChimeraException extends RuntimeException implements ChimeraThrowable {

    @Getter
    private final HttpStatus httpStatus;
    private String errorClass;
    private Map<String, String> messageParameters;

    // Primary Constructor
   /* public ChimeraException(String message, ChimeraThrowable cause, String errorClass, Map<String, String> messageParameters) {
        super(message, (java.lang.Throwable) cause);
        this.errorClass = errorClass;
        this.messageParameters = messageParameters != null ? messageParameters : new HashMap<>();
    }*/

    public ChimeraException(String message,
                            ChimeraThrowable cause,
                            String errorClass,
                            Map<String, String> messageParameters,
                            HttpStatus httpStatus) {
        super(message, (java.lang.Throwable) cause);
        this.errorClass = errorClass;
        this.messageParameters = messageParameters;
        this.httpStatus = httpStatus;
    }

    // Constructor with message and cause
    public ChimeraException(String message, ChimeraThrowable cause) {
        this(message, cause, null, null,null);
    }

    // Constructor with message only
    public ChimeraException(String message, String errorClass, Map<String, String> messageParameters) {
        this(message, (ChimeraThrowable) null, null, null, null);
        this.errorClass = errorClass;
        this.messageParameters = messageParameters;
    }


    // Constructor with errorClass, messageParameters, and cause
    public ChimeraException(String errorClass, Map<String, String> messageParameters, ChimeraThrowable cause) {
        this(ThrowableHelper.getMessage(errorClass, messageParameters), cause, errorClass, messageParameters, null);
    }

    // Constructor with errorClass, messageParameters, cause, and summary
    public ChimeraException(String errorClass, Map<String, String> messageParameters, ChimeraThrowable cause, String summary) {
        this(ThrowableHelper.getMessage(errorClass, messageParameters, summary), cause, errorClass, messageParameters, null);
    }
    // Constructor with errorClass, messageParameters, cause, and API Return Code
    public ChimeraException(String errorClass,
                            Map<String, String> messageParameters,
                            ChimeraThrowable cause,
                            HttpStatus httpStatus) {
        this(ThrowableHelper.getMessage(errorClass, messageParameters),
                cause,
                errorClass,
                messageParameters,
                httpStatus);
    }

    @Override
    public Map<String, String> getMessageParameters() {
        return messageParameters;
    }

    @Override
    public String getErrorClass() {
        return errorClass;
    }

    // Static Factory Methods
    public static ChimeraException internalError(String msg) {
        Map<String, String> params = new HashMap<>();
        params.put("message", msg);
        return new ChimeraException("INTERNAL ERROR", params, null);
    }

    public static ChimeraException internalError(String msg, ChimeraThrowable cause) {
        Map<String, String> params = new HashMap<>();
        params.put("message", msg);
        return new ChimeraException("INTERNAL ERROR", params, cause);
    }
}
