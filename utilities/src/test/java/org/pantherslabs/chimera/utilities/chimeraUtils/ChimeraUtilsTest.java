package org.pantherslabs.chimera.utilities.chimeraUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;
import org.pantherslabs.chimera.utilities.ChimeraUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ChimeraUtilsTest {

    @Test
    void toJsonString_withValidBlock_returnsJsonString() throws IOException {
        String json = ChimeraUtils.toJsonString(generator -> {
            generator.writeStartObject();
            generator.writeStringField("key", "value");
            generator.writeEndObject();
        });
        assertEquals("{\"key\":\"value\"}", json);
    }

    @Test
    void toJsonString_withEmptyBlock_returnsEmptyJsonObject() throws IOException {
        String json = ChimeraUtils.toJsonString(JsonGenerator::writeStartObject);
        assertEquals("{}", json);
    }

    @Test
    void toJsonString_withIOException_throwsIOException() {
        assertThrows(IOException.class, () -> ChimeraUtils.toJsonString(generator -> {
            throw new IOException("Test exception");
        }));
    }

    @Test
    void getSparkClassLoader_returnsClassLoader() {
        ClassLoader classLoader = ChimeraUtils.getSparkClassLoader();
        assertNotNull(classLoader);
        assertEquals(ChimeraUtils.class.getClassLoader(), classLoader);
    }
}