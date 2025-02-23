package pl.hotel.drivers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties;

        static {
            properties = new Properties();
            try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    System.out.println("Nie można znaleźć pliku config.properties");
                }
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


    public int getIntProperty(String key) {
        String value = properties.getProperty(key);
        if(value != null) {
            return  Integer.parseInt(value);
        } else {
            return 0;
        }
    }
    }