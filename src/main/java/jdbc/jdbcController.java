package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcController {
    public class DatabaseConnection{
        public static Connection connectToDb(){
            Connection con = null;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Class loaded successfully");
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/javaproject",
                        "root",
                        "Raghav@2006"
                );
                System.out.println("Connected to database successfully");
            }
            catch(ClassNotFoundException exp)
            {
                exp.printStackTrace();
            }
            catch (SQLException exp)
            {
                exp.printStackTrace();
            }
            return con;
        }
    }
}
