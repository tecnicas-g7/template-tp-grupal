package game.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by ezequiel on 17/05/16.
 */
public class Messages {

    private static String fileName = "message.properties";
    private static FileInputStream fileInput;

    public static String getMessage(String key) {
        try {

            File file = new File(fileName);
            Properties properties = new Properties();
            fileInput = new FileInputStream(file);
            properties.load(fileInput);
            fileInput.close();
            String message = properties.getProperty(key);
            if (message == null) {
                message = "Message not found for key " + key;
            }
            return message;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                fileInput.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        return "Message not found for key " + key;
    }

}
