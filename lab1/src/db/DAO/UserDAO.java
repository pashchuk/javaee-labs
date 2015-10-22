package db.DAO;

import db.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class UserDAO extends GenericDAOImpl<User> {
    public UserDAO(){
        tableName = "users";
    }
    @Override
    public void insert(User user) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO users (first_name, last_name, age) " +
                            "VALUES (?,?,?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        User target = DAOFactory.getInstance().getUserDAO().findById(user.getId());
        if(target==null){
            System.out.println(String.format("User with id = %s not exist in database",user.getId()));
            return;
        }
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("UPDATE users SET first_name=?, last_name=?, age=? WHERE id=?");
            statement.setString(1,user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());
            statement.setInt(4, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User map(ResultSet resultSet) {
        User user = null;
        try {
            while (resultSet.next()){
                user = mapUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> mapAll(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while (resultSet.next()){
                users.add(mapUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User mapUser(ResultSet resultSet)
    {
        User resultUser= new User();

        try {
            resultUser.setId(resultSet.getInt("id"));
            resultUser.setFirstName(resultSet.getString("first_name"));
            resultUser.setLastName(resultSet.getString("last_name"));
            resultUser.setAge(Integer.parseInt(resultSet.getString("age")));

        } catch (SQLException e) {
            System.out.println("Error while mapping user resultSet");
            e.printStackTrace();
        }
        System.out.println("The result of user getting query is : " + resultUser.toString());
        return resultUser;
    }
}
