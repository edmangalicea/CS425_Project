import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("hey");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CS425", "root", "Prometheus12");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Customer");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}