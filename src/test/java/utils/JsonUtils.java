package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtils {
    public static List<User> readUsers(String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(path),
                    new TypeReference<List<User>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + path, e);
        }
    }
}
