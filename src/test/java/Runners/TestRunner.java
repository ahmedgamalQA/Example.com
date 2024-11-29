package Runners;

import org.junit.runner.JUnitCore;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestRunner {
    private static Properties properties = new Properties();
    public static final String TAGS;

    static {
        try {
            // Load the properties file
            FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/configuration.properties");
            properties.load(fileReader);

            // Get tags from properties
            TAGS = properties.getProperty("tags");

        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static void main(String[] args) {
        // Decide which runner to execute based on the tags
        if ("@ApiTests".equals(TAGS)) {
            JUnitCore.runClasses(ApiTestRunner.class);
        } else if ("@ExampleDomain_WebTests".equals(TAGS)) {
            JUnitCore.runClasses(ExampleDomainTestRunner.class);

        }
    }
}
