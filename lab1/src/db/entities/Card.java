package db.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class Card {
    int Id;
    Doctor Doctor;
    User User;
    Diagnosis Diagnosis;
    List<Note> Notes;
    String Name;

    public Card(){
        Notes = new ArrayList<>();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public db.entities.Doctor getDoctor() {
        return Doctor;
    }

    public void setDoctor(db.entities.Doctor doctor) {
        Doctor = doctor;
    }

    public db.entities.User getUser() {
        return User;
    }

    public void setUser(db.entities.User user) {
        User = user;
    }

    public db.entities.Diagnosis getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(db.entities.Diagnosis diagnosis) {
        Diagnosis = diagnosis;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void addNote(Note note) {
        note.setCard(this);
        Notes.add(note);
    }

    public Note getNote(int id) {
        return Notes.get(id);
    }

    public List<Note> getNotes(){
        return Notes;
    }
}
