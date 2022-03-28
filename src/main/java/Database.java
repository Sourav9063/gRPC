import javax.xml.crypto.Data;
import java.sql.*;

public class Database {
    static public Database instance = null;
    private static Connection connection;
    private static Statement statement;

    private Database(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public static Database getInstance() {
        if (instance == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserLogInData", "root", "");
                statement = connection.createStatement();

                 instance = new Database(connection, statement);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    public void insertUser(String email,String password){
        try {

            statement.executeUpdate("INSERT INTO `user_data_hashed`(`email`, `password`) VALUES ('"+email+"','"+password+"')");
        } catch (Exception e) {
            System.out.println("Error in insertUser");
            e.printStackTrace();
        }
    }
    public void getAllUser(){
        try{
           ResultSet resultSet= statement.executeQuery("SELECT * FROM `user_data_hashed`");
            System.out.println(resultSet.toString());
//            System.out.println(resultSet.);
        while (resultSet.next()){
            System.out.println(resultSet.getString("email"));
            System.out.println(resultSet.getString("password"));
        }

        }catch (Exception e){}

    }
    public boolean check(String email,String password){
        try {
            ResultSet resultSet= statement.executeQuery("SELECT * FROM `user_data_hashed` WHERE `email`='"+email+"' AND `password`='"+password+"'");
            while (resultSet.next()){
                if(resultSet.getString("email").equals(email)&&resultSet.getString("password").equals(password)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
public void close(){
    try {
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}



