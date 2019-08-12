package util;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;


public class CommonUtil {

    /**
     * this Class will load the config.properties file
     */
    public static final Properties properties = new Properties();

    static {
        try {

            // Read the property only once when load the class
            properties.load(DBConnection.class.getResourceAsStream(Constants.PROPERTY_FILE));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Timestamp getCurrentTimeStamp() {
        Date today = new Date();
        return new Timestamp(today.getTime());
    }


}
