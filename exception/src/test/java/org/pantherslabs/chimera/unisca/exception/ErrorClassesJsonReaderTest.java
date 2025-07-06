package org.pantherslabs.chimera.unisca.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ErrorClassesJsonReaderTest {

  private ErrorClassesJsonReader errorClassesJsonReader;

    @BeforeEach
    void setUp() {
        errorClassesJsonReader = new ErrorClassesJsonReader(
                Collections.singletonList(
                        this.getClass().getClassLoader().getResource("test_errors.json")));
    }

    @Test
    void getErrorMessage_withValidErrorClassAndParameters_returnsFormattedMessage() {
        Map<String, String> params = new HashMap<>();
        params.put("param1", "value1");
        String result = errorClassesJsonReader.getErrorMessage("DataSourceException", params);
        //System.out.println(result);
        assertNotNull(result);
    }

    @Test
    void getErrorMessage_withInvalidErrorClass_throwsException() {
        Map<String, String> params = new HashMap<>();
        params.put("param1", "value1");
        //System.out.println(result);
        assertThrows(Exception.class, () -> errorClassesJsonReader.getErrorMessage("invalidErrorClass", params));
    }

    @Test
    void getMessageTemplate_withValidErrorClass_returnsTemplate() {
        String result = errorClassesJsonReader.getMessageTemplate("DataSourceException");
        assertNotNull(result);
    }

    @Test
    void getMessageTemplate_withInvalidErrorClass_throwsException() {
        assertThrows(Exception.class, () -> errorClassesJsonReader.getMessageTemplate("invalidErrorClass"));
    }

    @Test
    void getSqlState_withValidErrorClass_returnsSqlState() {
        String result = errorClassesJsonReader.getSqlState("DataSourceException");
        assertEquals("42704", result);
    }

    @Test
    void getSqlState_withInvalidErrorClass_returnsNull() {
        String result = errorClassesJsonReader.getSqlState("invalidErrorClass");
        assertNull(result);
    }
}