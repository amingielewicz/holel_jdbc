package pl.hotel.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverFactory {

    private static final String URL = ConfigLoader.getProperty("url");
    private static final String USER = "root";
    private static final String PASSWORD = ConfigLoader.getProperty("password");
    private static Connection connection = null;

    public static void installJdbcDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Sterowniki bazy danych zainicjalizowane.");
        } catch (Exception e) {
            System.err.println("Problem z inicjalizacją sterowników bazy danych" + e);
        }
    }

    public static void DbConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączono z Bazą Danych.");

        } catch (SQLException e) {
            System.err.println("Niepołączono z Bazą Danych" + e);
        } finally {
            closeConnection(connection);
        }
    }


    private static void closeConnection(Connection connection) {
        try{
            connection.close();
        }catch(SQLException e){
            System.err.println("Rozłączono Bazę Danych.");
        }
    }
}
