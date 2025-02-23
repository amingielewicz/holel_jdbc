package pl.hotel.customer;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private Connection connection;

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Customer> findAll(){
        List<Customer> customers = new ArrayList<>();

        try {
            String query = "SELECT * FROM customer WHERE 1=1 AND delete_date IS NULL";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("pesel"),
                        resultSet.getDate("create_date").toLocalDate(),
                        resultSet.getTime("update_date") != null ? resultSet.getTimestamp("update_date").toLocalDateTime() : null,
                        resultSet.getTime("delete_date") != null ? resultSet.getTimestamp("delete_date").toLocalDateTime() : null
                ));
            }
            return customers;
        } catch (SQLException e) {
            System.err.println("Błąd pobierania danych: " + e.getMessage());
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return null;
        //TODO stworzyć metodę do while
    }

    public void create(Customer customer) {
        try{
            String query = "INSERT INTO customer(name, surname, pesel, create_date) values(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getPesel());
            preparedStatement.setDate(4, Date.valueOf(customer.getCreateDate()));
            preparedStatement.executeUpdate();

        } catch(SQLException e) {
            System.err.println("Błąd przy dodawaniu klienta: " + e.getMessage());
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    public void update(Customer customer, int id){
        try{
            String query = "UPDATE customer SET name = ?, surname = ?, pesel = ?, update_date = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getPesel());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Błąd przy aktualizacji klienta: " + e.getMessage());
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    public void delete(int id){
        try{
            String query = "UPDATE customer SET delete_date = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Nie usunięto wartości z tablicy customer.");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }


    private void closePreparedStatement(PreparedStatement preparedStatement) {
        try{
            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException e){
            e.getSQLState();
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        try{
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e){
            e.getSQLState();
        }
    }


}
