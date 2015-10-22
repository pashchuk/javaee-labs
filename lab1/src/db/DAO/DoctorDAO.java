package db.DAO;

import db.entities.Doctor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class DoctorDAO extends GenericDAOImpl<Doctor> {
    @Override
    public void insert(Doctor doctor) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO doctors (first_name, last_name, department, age) " +
                            "VALUES (?,?,?,?)");
            statement.setString(1,doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3,doctor.getDepartment());
            statement.setInt(4, doctor.getAge());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Doctor doctor) {
        Doctor target = DAOFactory.getInstance().getDoctorDAO().findById(doctor.getId());
        if(target==null){
            System.out.println(String.format("Doctor with id = %s not exist in database",doctor.getId()));
            return;
        }
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("UPDATE cards SET first_name=?, last_name=?, " +
                    "department=?, age=? WHERE id=?");
            statement.setString(1,doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3,doctor.getDepartment());
            statement.setInt(4, doctor.getAge());
            statement.setInt(5, doctor.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor map(ResultSet resultSet) {
        Doctor doctor = null;
        try {
            while (resultSet.next()){
                doctor = mapDoctor(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public List<Doctor> mapAll(ResultSet resultSet) {
        List<Doctor> doctors = new ArrayList<>();
        try {
            while (resultSet.next()){
                doctors.add(mapDoctor(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    private Doctor mapDoctor(ResultSet resultSet)
    {
        Doctor resultDoctor = new Doctor();

        try {
            resultDoctor.setId(resultSet.getInt("id"));
            resultDoctor.setFirstName(resultSet.getString("first_name"));
            resultDoctor.setLastName(resultSet.getString("last_name"));
            resultDoctor.setDepartment(resultSet.getString("department"));
            resultDoctor.setAge(Integer.parseInt(resultSet.getString("age")));

        } catch (SQLException e) {
            System.out.println("Error while mapping doctor resultSet");
            e.printStackTrace();
        }
        System.out.println("The result of doctor getting query is : " + resultDoctor.toString());
        return resultDoctor;
    }
}
