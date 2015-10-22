package db.DAO;

import db.entities.Doctor;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class DoctorDAO extends GenericDAOImpl<Doctor> {
    @Override
    public void insert(Doctor doctor) {

    }

    @Override
    public void update(Doctor doctor) {

    }

    @Override
    public Doctor map(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<Doctor> mapAll(ResultSet resultSet) {
        return null;
    }
}
