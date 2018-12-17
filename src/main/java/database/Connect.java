package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private final static String CLASS_FOR_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://www.cointweetcap.com/";
    private final static String DB_NAME = "cointweetcap?useSSL=false";
    private final static String DB_User_Name = "cointweetcap";
    private final static String DB_User_Pass = "Uv$kd130";


    public static Connection getConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(CLASS_FOR_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            dbConnection = DriverManager.getConnection(DB_URL + DB_NAME, DB_User_Name, DB_User_Pass);
            return dbConnection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}