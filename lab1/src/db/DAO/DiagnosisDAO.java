package db.DAO;

import db.entities.Diagnosis;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class DiagnosisDAO extends GenericDAOImpl<Diagnosis> {
    @Override
    public void insert(Diagnosis diagnosis) {

    }

    @Override
    public void update(Diagnosis diagnosis) {

    }

    @Override
    public Diagnosis map(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<Diagnosis> mapAll(ResultSet resultSet) {
        return null;
    }
}
