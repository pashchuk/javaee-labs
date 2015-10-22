package db.DAO;

import db.ConnectionHandler;
import db.entities.Note;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class NoteDAO extends GenericDAOImpl<Note> {
    public NoteDAO(){
        tableName = "notes";
    }
    @Override
    public void insert(Note note) {
        CallableStatement statement;
        try {
            statement = connection.prepareCall("INSERT INTO notes (doctor_id, card_id, note_text) VALUES (?,?,?)");
            statement.setInt(1,note.getDoctor().getId());
            statement.setInt(2, note.getCard().getId());
            statement.setString(3, note.getNoteText());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Note note) {
        Note target = DAOFactory.getInstance().getNoteDAO().findById(note.getId());
        if(target==null){
            System.out.println(String.format("Note with id = %s not exist in database",note.getId()));
            return;
        }
        CallableStatement statement;
        try {
            statement = connection.prepareCall("UPDATE notes SET doctor_id=?, card_id=?, note_text=? WHERE id=?");
            statement.setInt(1,note.getDoctor().getId());
            statement.setInt(2, note.getCard().getId());
            statement.setString(3, note.getNoteText());
            statement.setInt(4, note.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Note map(ResultSet resultSet) {
        Note note = null;
        try {
            while (resultSet.next()){
                note = mapNote(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    @Override
    public List<Note> mapAll(ResultSet resultSet) {
        List<Note> notes = new ArrayList<>();
        try {
            while (resultSet.next()){
                notes.add(mapNote(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    private Note mapNote(ResultSet resultSet)
    {
        Note resultNote = new Note();

        try {
            resultNote.setId(resultSet.getInt("id"));
            resultNote.setNoteText(resultSet.getString("note_text"));

            int doctorId = Integer.parseInt(resultSet.getString("doctor_id"));
            int cardId = Integer.parseInt(resultSet.getString("card_id"));

            resultNote.setDoctor(DAOFactory.getInstance().getDoctorDAO().findById(doctorId));
            resultNote.setCard(DAOFactory.getInstance().getCardDAO().findById(cardId));

        } catch (SQLException e) {
            System.out.println("Error while mapping note resultSet");
            e.printStackTrace();
        }
        System.out.println("The result of note getting query is : " + resultNote.toString());
        return resultNote;
    }
}
