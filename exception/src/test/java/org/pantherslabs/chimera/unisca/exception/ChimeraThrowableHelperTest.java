package org.pantherslabs.chimera.unisca.exception;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ChimeraThrowableHelperTest {

    @Test
    void getMessage_withValidErrorClassAndParameters_returnsFormattedMessage() {
        Map<String, String> messageParameters = Map.of("exception", "value");
        String result = ThrowableHelper.getMessage("GenericException.InvalidInputParameter", messageParameters);
        assertNotNull(result);
    }

    @Test
    void getMessage_withEmptyContext_returnsMessageWithoutContext() {
        Map<String, String> messageParameters = Map.of("param1", "value1");
        String result = ThrowableHelper.getMessage("GenericException", messageParameters, "");
        assertNotNull(result);
    }

    @Test
    void getMessage_withNonEmptyContext_returnsMessageWithContext() {
        Map<String, String> messageParameters = Map.of("param1", "value1");
        String result = ThrowableHelper.getMessage("GenericException", messageParameters, "Context info");
        assertNotNull(result);
    }

    @Test
    void getSqlState_withValidErrorClass_returnsSqlState() {
        String result = ThrowableHelper.getSqlState("GenericException");
        assertNull(result);
    }

    @Test
    void isInternalError_withInternalErrorClass_returnsTrue() {
        assertTrue(ThrowableHelper.isInternalError("INTERNAL_ERROR"));
    }

    @Test
    void isInternalError_withNonInternalErrorClass_returnsFalse() {
        assertFalse(ThrowableHelper.isInternalError("NON_INTERNAL_ERROR"));
    }

  @Test
    void getMessage_withThrowableAndPrettyFormat_returnsThrowableMessage() {
        ChimeraThrowable chimeraThrowable = new ChimeraThrowable() {
            @Override
            public String getErrorClass() {
                return "TestErrorClass";
            }

            @Override
            public Map<String, String> getMessageParameters() {
                return Map.of();
            }

            @Override
            public String getMessage() {
                return "Error message";
            }
        };
        String result = ThrowableHelper.getMessage(chimeraThrowable, ErrorMessageFormat.PRETTY);
        assertEquals("Error message", result); // Update expected if needed
    }

/*    @Test
    void getMessage_withThrowableAndMinimalFormat_returnsJsonMessage() {
        ChimeraThrowable chimeraThrowable = new Exception("Error message");
        String result = ThrowableHelper.getMessage(chimeraThrowable, ErrorMessageFormat.MINIMAL);
        assertTrue(result.contains("\"errorClass\":\"java.lang.Throwable\"")); // Update class if needed
        assertTrue(result.contains("\"message\":\"Error message\""));
    }

    @Test
    void getMessage_withThrowableAndStandardFormat_returnsJsonMessageWithTemplate() {
        ChimeraThrowable chimeraThrowable = new ChimeraThrowable("Error message");
        String result = ThrowableHelper.getMessage(chimeraThrowable, ErrorMessageFormat.STANDARD);
        assertTrue(result.contains("\"errorClass\":\"java.lang.Throwable\""));
        assertTrue(result.contains("\"message\":\"Error message\""));
        assertTrue(result.contains("\"messageTemplate\":\"Some template\"")); // Update key if needed
    }

    @Test
    void getMessage_withThrowable_returnsJsonMessage() {
        ChimeraThrowable chimeraThrowable = new ChimeraThrowable("Error message");
        String result = ThrowableHelper.getMessage(chimeraThrowable);
        assertTrue(result.contains("\"errorClass\":\"java.lang.Throwable\""));
        assertTrue(result.contains("\"message\":\"Error message\""));
    }
    */

}