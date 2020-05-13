package DataAccess.DataAccess;

import System.Users.User;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserSQL implements DataBase<User> {

    private static final UserSQL instance = new UserSQL();

    //private constructor to avoid client applications to use constructor
    public static UserSQL getInstance(){
        return instance;
    }

    private UserSQL() {
        // users.add(new User("John", "john@domain.com"));
        //users.add(new User("Susan", "susan@domain.com"));
    }
    DBConnector dbc = DBConnector.getInstance();
    @Override
    public Optional<User> get(long id) {
        try {
            Connection con = DBConnector.getConnection();
            Statement stat = con.createStatement();

            String sql = "select * from users where ID="+id;

            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                int id_col = rs.getInt("RunID");
                int id_col2 = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String userName = rs.getString("UserName");
                String password = rs.getString("password");
                int status = rs.getInt("Status");
                int pointet = rs.getInt("Pointet");
                int UserTypeCode = rs.getInt("UserTypeCode");


                String p = id_col + " " + id_col2 + " " + fullName + " " + userName + " " + password + " " + status + " " + pointet + " " + UserTypeCode;
                System.out.println(p);
            }
            con.close();

        } catch (SQLException err) {
            throw new RuntimeException("Error connecting to the database", err);
        }
        return null;
        //return Optional.empty();
    }

    @Override
    public List<User> getAll() {

        try {
            Connection con = DBConnector.getConnection();
            Statement stat = con.createStatement();
            String sql = "select * from users";

            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                int id_col = rs.getInt("RunID");
                int id_col2 = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String userName = rs.getString("UserName");
                String password = rs.getString("password");
                int status = rs.getInt("Status");
                int pointet = rs.getInt("Pointet");
                int UserTypeCode = rs.getInt("UserTypeCode");


                String p = id_col + " " + id_col2 + " " + fullName + " " + userName + " " + password + " " + status + " " + pointet + " " + UserTypeCode;
                System.out.println(p);
            }
            con.close();

        } catch (SQLException err) {
            throw new RuntimeException("Error connecting to the database", err);
        }
        return null;
    }

    @Override
    public void save(User user)  {
            try {
                Connection connection = DBConnector.getConnection();
                int id= user.getId();
                String name= user.getName();
                String userName=user.getUserName();
                String pass=user.getPassword();
                PreparedStatement ps=connection.prepareStatement("insert into users(id, fullName, UserName,password,Status,Pointet,UserTypeCode) "
                        + "values (?,?,?,?,?,?,?)");
                ps.setInt(1, 8);
                ps.setString(2, "inbar");
                ps.setString(3, "inbar1");
                ps.setString(4, "123");
                ps.setInt(5, 0);
                ps.setInt(6, 0);
                ps.setInt(7, 0);


                ps.executeUpdate();
                connection.close();

            } catch (SQLException err) {
                throw new RuntimeException("Error connecting to the database", err);
            }

        }


    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {
        int id= user.getId();

        try {
            Connection con = DBConnector.getConnection();
            Statement stat = con.createStatement();

            //String sql = "DELETE FROM users WHERE ID="+id;

            String query = "DELETE FROM users WHERE ID = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);

            // execute the preparedstatement
            preparedStmt.execute();

           con.close();



        } catch (SQLException err) {
            throw new RuntimeException("Error connecting to the database", err);
        }

    }
}
