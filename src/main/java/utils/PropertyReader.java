package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader
{
    private static Properties properties;
    private static FileInputStream inputStream;

    public static String loadProperty(String propertyName)
    {
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/config.properties");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return properties.getProperty(propertyName); //return string property value
    }

}
