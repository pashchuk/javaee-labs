package db;

import utils.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mamax on 10/22/2015.
 */

public class ConnectionHandler {
    private static final String jdbcDriver = "com.mysql.jdbc.Driver";

    private static Connection getConnectionToDb(String concreteDbName){
        Connection connection = null;

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Include in your library path, please");
            e.printStackTrace();
            return null;
        }

        System.out.println("MySQL JDBC Driver Registered!");

        try {
            connection = DriverManager.getConnection(
                    DatabaseProperties.DB_URL + (concreteDbName==null ? "" : concreteDbName),
                    DatabaseProperties.DB_USER,
                    DatabaseProperties.DB_PASSWORD);

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    System.out.println("EXCEPTION DURING CLOSING DB CONNECTION");
                }
            }
            e.printStackTrace();
            System.out.println("Connection was not set. Please, check your db settings!");
        }

        return connection;
    }

    public static Connection getConnectionToDb() {
        return getConnectionToDb(DatabaseProperties.DB_NAME);
    }

    public static Connection getConnectionToDbManager(){
        return getConnectionToDb(null);
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR DURING CLOSING CONNECTION TO DB");
        }
        System.out.println("Connection to DB was closed successfully");
    }

    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR DURING CLOSING STATEMENT");
        }
        System.out.println("Statement closing succeed");
    }


}