package db.DAO;

import db.entities.Card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class CardDAO extends GenericDAOImpl<Card> {

    public CardDAO(){
        tableName = "cards";
    }
    @Override
    public void insert(Card card) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeQuery(String.format("INSERT INTO cards (doctor_id, user_id, diagnosis_id, name) " +
                            "VALUES (%s,%s,%s,%s)",
                    card.getDoctor().getId(),
                    card.getUser().getId(),
                    card.getDiagnosis().getId(),
                    card.getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Card card) {
        Card target = DAOFactory.getInstance().getCardDAO().findById(card.getId());
        if(target==null){
            System.out.println(String.format("Card with id = %s not exist in database",card.getId()));
            return;
        }
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE cards SET user_id=%s, doctor_id=%s, " +
                            "diagnosis_id=%s, name=%s WHERE id=%s",
                    card.getUser().getId(),
                    card.getDoctor().getId(),
                    card.getDiagnosis().getId(),
                    card.getName(),
                    card.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Card map(ResultSet resultSet) {
        Card card = null;
        try {
            while (resultSet.next()){
                card = mapCard(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    private Card mapCard(ResultSet resultSet)
    {
        Card resultCard = new Card();

        try {
            resultCard.setId(resultSet.getInt("id"));
            resultCard.setName(resultSet.getString("name"));

            int userId = Integer.parseInt(resultSet.getString("user_id"));
            int doctorId = Integer.parseInt(resultSet.getString("doctor_id"));
            int diagnosisId = Integer.parseInt(resultSet.getString("diagnosis_id"));

            resultCard.setUser(DAOFactory.getInstance().getUserDAO().findById(userId));
            resultCard.setDoctor(DAOFactory.getInstance().getDoctorDAO().findById(doctorId));
            resultCard.setDiagnosis(DAOFactory.getInstance().getDiagnosisDAO().findById(diagnosisId));

        } catch (SQLException e) {
            System.out.println("Error while mapping user resultSet");
            e.printStackTrace();
        }
        System.out.println("The result of card getting query is : " + resultCard.toString());
        return resultCard;
    }

    @Override
    public List<Card> mapAll(ResultSet resultSet) {
        List<Card> cards = new ArrayList<>();
        try {
            while (resultSet.next()){
                cards.add(mapCard(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
