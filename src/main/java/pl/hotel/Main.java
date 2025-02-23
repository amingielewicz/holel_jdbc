package pl.hotel;

import pl.hotel.drivers.DriverFactory;


public class Main {
    public static void main(String[] args) {

        DriverFactory.installJdbcDriver();
        DriverFactory.DbConnection();

    }
}