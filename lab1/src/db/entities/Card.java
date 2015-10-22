package db.entities;

/**
 * Created by mamax on 10/22/2015.
 */
public class Card {
    int Id;
    int DoctorId;
    int UserId;
    int DiagnosisId;
    String Name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(int doctorId) {
        DoctorId = doctorId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getDiagnosisId() {
        return DiagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        DiagnosisId = diagnosisId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Card{" +
                "Id=" + Id +
                ", DoctorId=" + DoctorId +
                ", UserId=" + UserId +
                ", DiagnosisId=" + DiagnosisId +
                ", Name='" + Name + '\'' +
                '}';
    }
}
