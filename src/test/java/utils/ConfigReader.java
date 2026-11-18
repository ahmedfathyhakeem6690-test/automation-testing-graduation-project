package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    // save config file in java properties class
    private static final Properties properties = new Properties();
    // open config file and read it and save in memory only one time
    static {
        try (InputStream in = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new RuntimeException("config.properties not found on classpath");
            }
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
    // return config keys as String
    public static String get(String key) {
        return properties.getProperty(key);
    }
    // return config keys as Integer
    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key).trim());
    }
}
