package db.DAO;

import db.entities.Diagnosis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class DiagnosisDAO extends GenericDAOImpl<Diagnosis> {

    public DiagnosisDAO(){
        tableName = "diagnosis";
    }
    @Override
    public void insert(Diagnosis diagnosis) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeQuery(String.format("INSERT INTO diagnosis (description) VALUES (%s)",
                    diagnosis.getDescription()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Diagnosis diagnosis) {
        Diagnosis target = DAOFactory.getInstance().getDiagnosisDAO().findById(diagnosis.getId());
        if(target==null){
            System.out.println(String.format("Diagnosis with id = %s not exist in database",diagnosis.getId()));
            return;
        }
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE diagnosis SET description=%s WHERE id=%s",
                    diagnosis.getDescription(), diagnosis.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Diagnosis map(ResultSet resultSet) {
        Diagnosis diagnosis = null;
        try {
            while (resultSet.next()){
                diagnosis = mapDiagnosis(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosis;
    }

    @Override
    public List<Diagnosis> mapAll(ResultSet resultSet) {
        List<Diagnosis> diagnosis = new ArrayList<>();
        try {
            while (resultSet.next()){
                diagnosis.add(mapDiagnosis(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosis;
    }

    private Diagnosis mapDiagnosis(ResultSet resultSet)
    {
        Diagnosis resultDiagnosis = new Diagnosis();

        try {
            resultDiagnosis.setId(resultSet.getInt("id"));
            resultDiagnosis.setDescription(resultSet.getString("description"));

        } catch (SQLException e) {
            System.out.println("Error while mapping user resultSet");
            e.printStackTrace();
        }
        System.out.println("The result of diagnosis getting query is : " + resultDiagnosis.toString());
        return resultDiagnosis;
    }
}
