package db.DAO;

import db.entities.Note;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class NoteDAO extends GenericDAOImpl<Note> {
    @Override
    public void insert(Note note) {

    }

    @Override
    public void update(Note note) {

    }

    @Override
    public Note map(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<Note> mapAll(ResultSet resultSet) {
        return null;
    }
}
