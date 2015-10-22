import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by mamax on 10/1/2015.
 */
public class DbStuff {

    private static final String jdbcDriver = "com.mysql.jdbc.Driver";
    private static Properties dbProperties;

    public static void main(String[] args){
        dbProperties = new Properties();
        try {
            dbProperties.load(new BufferedReader(new FileReader("./lab1/database.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection conn = connect();
        if(conn==null){
            System.out.println("Connection is null");
            createDb();
            conn = connect();
        }
        try {
            createTables(conn);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection conn) throws IOException, SQLException {
        if (conn==null)
            throw new NullPointerException();
        ScriptRunner runner = new ScriptRunner(conn,false,true);
        runner.runScript(new BufferedReader(new FileReader("./lab1/Db.sql")));
    }


    private static int createDb(){
        int result = -1;
        Connection conn = null;
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(
                    dbProperties.getProperty("url"),
                    dbProperties.getProperty("dbUser"),
                    dbProperties.getProperty("dbPassword"));
        }catch (SQLException ex) {
            System.out.println("Cannot get a connection");
            ex.printStackTrace();
        }catch (Exception ex) {
            System.out.println("Cannot load jdbc driver");
            ex.printStackTrace();
        }
        try {
            Statement s = conn.createStatement();
            result = s.executeUpdate("CREATE DATABASE " + dbProperties.getProperty("dbName"));
            System.out.println(String.format("Database '%s' created successfully",dbProperties.getProperty("dbName")));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Connection connect(){
        Connection conn = null;
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(dbProperties.getProperty("url") +
                    dbProperties.getProperty("dbName"), dbProperties.getProperty("dbUser"),
                    dbProperties.getProperty("dbPassword"));
        }catch (SQLException ex) {
            System.out.println("Cannot get a connection");
            ex.printStackTrace();
        }catch (Exception ex) {
            System.out.println("Cannot load jdbc driver");
            ex.printStackTrace();
        }
        return conn;
    }
}