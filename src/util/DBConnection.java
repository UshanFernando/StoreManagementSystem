
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection extends CommonUtil {

    private static Connection connection;

    // This works according to singleton pattern
    private DBConnection() {
    }

    /**
     * @return connection
     * @throws ClassNotFoundException
     * @throws SQLException           this method returns a DataBase Connection
     */
    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        /*
         * This create new connection objects when connection is closed or it is null
         */
        if (connection == null || connection.isClosed()) {

            connection = DriverManager.getConnection(properties.getProperty(Constants.URL),
                    properties.getProperty(Constants.USERNAME), properties.getProperty(Constants.PASSWORD));
        }
        return connection;
    }
}
