package db.entities;

/**
 * Created by mamax on 10/22/2015.
 */
public class Diagnosis {
    int Id;
    String Description;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "Id=" + Id +
                ", Description='" + Description + '\'' +
                '}';
    }
}
