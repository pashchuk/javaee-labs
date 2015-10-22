package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by mamax on 10/22/2015.
 */
public class DatabaseProperties {
    public static final Properties properties = loadProperties();

    public static final String DB_URL = properties.getProperty("url").trim();
    public static final String DB_NAME = properties.getProperty("dbName").trim();
    public static final String DB_USER = properties.getProperty("dbUser").trim();
    public static final String DB_PASSWORD = properties.getProperty("dbPassword").trim();

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new BufferedReader(new FileReader("./lab1/database.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading properties");
        }
        return properties;
    }
}
