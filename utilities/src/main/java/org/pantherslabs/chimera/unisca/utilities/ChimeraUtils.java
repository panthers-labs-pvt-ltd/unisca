package org.pantherslabs.chimera.unisca.utilities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;

public class ChimeraUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts the given block of code to a JSON string using a Jackson JsonGenerator.
     *
     * @param block The block of code that writes JSON using the generator.
     * @return A JSON string produced by the block.
     * @throws IOException If an I/O error occurs while writing the JSON.
     */
    public static String toJsonString(JsonGeneratorBlock block) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            JsonGenerator generator = mapper.createGenerator(baos, JsonEncoding.UTF8);
            block.write(generator);
            generator.close();
            return baos.toString(StandardCharsets.UTF_8);
        }
    }

    /**
     * Returns the class loader associated with this class.
     *
     * @return The class loader of the ChimeraUtils class.
     */
    public static ClassLoader getSparkClassLoader() {
        return ChimeraUtils.class.getClassLoader();
    }

    /**
     * Functional interface used for passing the JsonGenerator to a block of code.
     */
    @FunctionalInterface
    public interface JsonGeneratorBlock {
        void write(JsonGenerator generator) throws IOException;
    }

    public static String convertTimeFormat(String timeFormat, Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        format.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        return format.format(calendar.getTime());
    }

    public static  Calendar currentGMTCalendar() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        return Calendar.getInstance(timeZone);
    }

    public static boolean isValidJson(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readTree(jsonString);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static Map<String, String> jsonToMapConverter(String inJsonConf) {
        Map<String, String> map = new HashMap<>();
        if (inJsonConf != null && !inJsonConf.isBlank()) {
            List<HashMap<String, String>> userConf = new Gson().fromJson(inJsonConf,
                    new TypeToken<List<HashMap<String, String>>>() {}.getType());
            for (HashMap<String, String> item : userConf) {
                map.put(item.get("Key"), item.get("Value"));
            }
        }
        return map;
    }
    public static String transformMetadataFields(String elementValue, JsonNode eventJSON) {
        String returnValue = "";
        if (!isNullOrBlank(elementValue)) {
            returnValue = elementValue;
            Pattern stringPattern = Pattern.compile("\\$\\{trigger/(.*?)}");
            if (elementValue.contains("${trigger/")) {
                Matcher patternMatches = stringPattern.matcher(elementValue);
                while (patternMatches.find()) {
                    String curMatch = patternMatches.group();
                    String replacingString = eventJSON.at(curMatch.substring(9, curMatch.length() - 1)).toString()
                            .replace("\"", "");
                    returnValue = returnValue.replace(curMatch, replacingString);
                }
            }
        }
        return returnValue;
    }

    public static boolean isNullOrBlank(String i) {
        return i == null || i.isEmpty();
    }

    @FunctionalInterface
    public interface JsonGeneratorConsumer {
        void accept(JsonGenerator generator) throws Exception;
    }

    public static String toCamelCase(String snakeCase) {
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;

        for (char ch : snakeCase.toCharArray()) {
            if (ch == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(ch));
                    nextUpper = false;
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public static Map<String, Object> convertKeysToCamelCase(Map<String, Object> input) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            result.put(toCamelCase(entry.getKey()), entry.getValue());
        }
        return result;
    }
}
