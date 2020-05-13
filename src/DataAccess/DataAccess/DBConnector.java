package DataAccess.DataAccess;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnector {
    //public static final String URL = "jdbc:mysql://localhost:3306/football_association";
    public static final String URL = "jdbc:mysql://localhost:3306/football";
    public static final String USER = "root";
    //public static final String PASS = "Shachar202@";
    public static final String PASS = "Team1616";

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


            //String host = "jdbc:mysql://132.72.65.99:3306/remoteDB/football";
            //String host = "jdbc:mysql://localhost:3306/football_association";
            //String host = "jdbc:mysql://localhost:3306/football_association";
            //String uName = "root";
            //String uPass = "Shachar202@";

            String host = "jdbc:mysql://132.72.65.99:3306/football?useLegacyDatetimeCode=false&serverTimezone=UTC";
            String uName = "root";
            String uPass = "root";



            //String host = "jdbc:mysql://132.72.65.99:3306";
            //String host = "jdbc:mysql://127.0.0.1:3306/football";
//            String host = " \"jdbc:mysql://132.72.65.99:3306/mysql?\"\n" +
//                    "                    + \"user=root&password=Team1616\"";
            //String host2=" jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
           // String uName = "root";
            //String uPass = "Shachar202@";
            //String uPass = "root";
            //String uPass = "Team123";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            System.out.println(con.isClosed());
            System.out.println(con.getSchema());

            return con;

        } catch (SQLException err) {
            throw new RuntimeException("Error connecting to the database", err);
        }
    }
     /**
         * Test Connection
         */


}

