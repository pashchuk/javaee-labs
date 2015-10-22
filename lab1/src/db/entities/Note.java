package db.entities;

/**
 * Created by mamax on 10/22/2015.
 */
public class Note {
    int Id;
    Doctor Doctor;
    Card card;
    String NoteText;

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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getNoteText() {
        return NoteText;
    }

    public void setNoteText(String noteText) {
        NoteText = noteText;
    }
}
