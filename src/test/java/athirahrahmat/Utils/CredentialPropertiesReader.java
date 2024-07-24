package athirahrahmat.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialPropertiesReader {
    private Properties properties = new Properties();

    public CredentialPropertiesReader(String fileName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

