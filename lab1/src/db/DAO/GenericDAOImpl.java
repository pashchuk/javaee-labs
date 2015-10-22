package db.DAO;

import db.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
    protected Connection connection = ConnectionHandler.getConnectionToDb();
    protected String tableName;

    public T findById(int id) {
        ResultSet resultSet = null;
        try {
            System.out.println(String.format("SELECT * FROM %s WHERE id=%s", tableName, id));
            String query = String.format("SELECT * FROM %s WHERE id=?", tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map(resultSet);
    }

    public void deleteById(int id) {
        try {
            System.out.println(String.format("DELETE FROM %s WHERE id=%s", tableName, id));
            String query = String.format("DELETE FROM %s WHERE id=?", tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error while trying to delete data!");
            e.printStackTrace();
        }
    }

    public List<T> getAll() {
        ResultSet resultSet = null;
        try {
            System.out.println(String.format("SELECT * FROM %s", tableName));
            String query = String.format("SELECT * FROM %s", tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapAll(resultSet);
    }
}
