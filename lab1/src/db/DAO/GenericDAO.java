package db.DAO;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public interface GenericDAO<T> {
    void insert(T t);
    T findById(int id);
    void update(T t);
    void deleteById(int id);
    T map(ResultSet resultSet);
    List<T> mapAll(ResultSet resultSet);
}
