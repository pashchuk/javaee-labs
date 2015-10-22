package db.entities;

/**
 * Created by mamax on 10/22/2015.
 */
public class Note {
    int Id;
    int DoctorId;
    int CardId;
    String NoteText;

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

    public int getCardId() {
        return CardId;
    }

    public void setCardId(int cardId) {
        CardId = cardId;
    }

    public String getNoteText() {
        return NoteText;
    }

    public void setNoteText(String noteText) {
        NoteText = noteText;
    }

    @Override
    public String toString() {
        return "Note{" +
                "Id=" + Id +
                ", DoctorId=" + DoctorId +
                ", CardId=" + CardId +
                ", NoteText='" + NoteText + '\'' +
                '}';
    }
}
