package com.kykapple.springbootplayground.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = PropertyLoader.class
                    .getClassLoader()
                    .getResourceAsStream("application-secret.yml");
            properties.load(inputStream);

            inputStream.close();

            return properties;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
