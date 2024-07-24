package athirahrahmat.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductPropertiesReader {
    private Properties properties = new Properties();

    public ProductPropertiesReader(String fileName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        int i = 1;
        while (true) {
            String productName = properties.getProperty("product" + i);
            if (productName == null) {
                break;
            }
            products.add(new Product(productName));
            i++;
        }
        return products;
    }
}
