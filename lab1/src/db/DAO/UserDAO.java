package db.DAO;

import db.entities.User;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class UserDAO extends GenericDAOImpl<User> {
    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User map(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<User> mapAll(ResultSet resultSet) {
        return null;
    }
}
