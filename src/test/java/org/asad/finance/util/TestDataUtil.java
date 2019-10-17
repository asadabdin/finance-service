package org.asad.finance.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

@UtilityClass
public final class TestDataUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
    }

    public static String readResourceToString(String resourceName) {
        try {
            return IOUtils.toString(requireNonNull(getSystemResourceAsStream(resourceName)), UTF_8);
        } catch (NullPointerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertJsonFileTo(String jsonPath, Class<T> type) {
        try {
            return objectMapper.readValue(readResourceToString(jsonPath), type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> convertJsonFileToList(String jsonPath, Class<T> type) {
        try {
            return objectMapper.readValue(readResourceToString(jsonPath), objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
