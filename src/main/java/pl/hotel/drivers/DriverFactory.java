package pl.hotel.drivers;

import pl.hotel.customer.Customer;
import pl.hotel.customer.CustomerRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

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
            System.out.println("Połączono z Bazą Danych.");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            CustomerRepository customerRepository = new CustomerRepository(connection);
            //customerRepository.create(new Customer("Agata", "Kłos", "72121250252", LocalDate.of(2025, 02, 20)));
            //customerRepository.update(new Customer("Joanna", "Majer", "60121505698"),4);
            customerRepository.delete(3);
            customerRepository.findAll().forEach(customer -> System.out.println(customer.getId() + " "
                    + customer.getName() + " " + customer.getSurname() + " " + customer.getPesel()));


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
