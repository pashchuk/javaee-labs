package utils;

import db.ConnectionHandler;
import db.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mamax on 10/22/2015.
 */
public class DbInitializer {

    public static void createDbIfNotExist(){
        Connection conn = ConnectionHandler.getConnectionToDb();

        //return if database exist
        if(conn!=null) return;

        //get connection to database manager (without concrete name of database)
        conn = ConnectionHandler.getConnectionToDbManager();
        int res = createDb(conn);
        ConnectionHandler.closeConnection(conn);

        //get connection to new database
        conn = ConnectionHandler.getConnectionToDb();
        try {
            createTables(conn);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something wrong with Db.sql file");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something wrong with connection");
        }
        ConnectionHandler.closeConnection(conn);

    }

    private static void createTables(Connection conn) throws IOException, SQLException {
        if (conn==null)
            throw new NullPointerException();
        ScriptRunner runner = new ScriptRunner(conn,false,true);
        runner.runScript(new BufferedReader(new FileReader("./lab1/Db.sql")));
    }


    private static int createDb(Connection conn){
        int result = -1;
        try {
            Statement s = conn.createStatement();
            result = s.executeUpdate("CREATE DATABASE " + DatabaseProperties.DB_NAME);
            System.out.println(String.format("Database '%s' created successfully",DatabaseProperties.DB_NAME));
            ConnectionHandler.closeStatement(s);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
