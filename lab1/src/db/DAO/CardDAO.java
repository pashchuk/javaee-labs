package db.DAO;

import db.entities.Card;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class CardDAO extends GenericDAOImpl<Card> {
    @Override
    public void insert(Card card) {

    }

    @Override
    public void update(Card card) {

    }

    @Override
    public Card map(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<Card> mapAll(ResultSet resultSet) {
        return null;
    }
}
