package DataAccess;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnector {

    private static final DBConnector instance = new DBConnector();

    //private constructor to avoid client applications to use constructor
    public static DBConnector getInstance(){
        return instance;
    }

    private DBConnector() {

    }
    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {


            String host = "jdbc:mysql://132.72.65.99:3306/football_system?useLegacyDatetimeCode=false&serverTimezone=UTC";
            String uName = "root";
            String uPass = "root";
            Connection con = DriverManager.getConnection(host, uName, uPass);

            return con;

        } catch (SQLException err) {
            throw new RuntimeException("Error connecting to the database", err);
        }
    }
     /**
         * Test Connection
         */


}

