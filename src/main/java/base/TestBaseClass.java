package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBaseClass {

    public Properties prop;

    public FileInputStream file;

    String path = "C:\\Users\\nehal\\RestAPIUse\\src\\main\\resources\\config.properties";

    public TestBaseClass() {

        try {
            prop = new Properties();
            file = new FileInputStream(path);
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (file!=null){
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     prop = new Properties();
     try {
     file = new FileInputStream(path);
     prop.load(file);
     } catch (java.io.IOException e) {
     e.printStackTrace();
     }
     }
     */
}

