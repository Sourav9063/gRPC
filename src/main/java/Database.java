import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
public void close(){
    try {
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}



