package utils;

import db.entities.*;
import db.DAO.*;
import db.ConnectionHandler;
import db.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by mamax on 10/22/2015.
 */
public class DbInitializer {

    public static void createDbIfNotExist(){
        Connection conn = ConnectionHandler.getConnectionToDb();

        //return if database exist
        if(conn!=null) return;

        //get connection to database manager (without concrete name of database)
        conn = ConnectionHandler.getConnectionToDbManager();
        int res = createDb(conn);
        ConnectionHandler.closeConnection(conn);

        //get connection to new database
        conn = ConnectionHandler.getConnectionToDb();
        try {
            createTables(conn);
            fillData();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something wrong with Db.sql file");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something wrong with connection");
        }

        ConnectionHandler.closeConnection(conn);

    }

    private static void fillData(){
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        DoctorDAO doctorDAO = DAOFactory.getInstance().getDoctorDAO();
        DiagnosisDAO diagnosisDAO = DAOFactory.getInstance().getDiagnosisDAO();
        CardDAO cardDAO = DAOFactory.getInstance().getCardDAO();
        NoteDAO noteDAO = DAOFactory.getInstance().getNoteDAO();

        // init usert
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();

        user1.setFirstName("Eduard");
        user1.setLastName("Pashchuk");
        user1.setAge(20);
        user2.setFirstName("Dmitro");
        user2.setLastName("Guly");
        user2.setAge(22);
        user3.setFirstName("Vitaliy");
        user3.setLastName("Levitskiy");
        user3.setAge(30);
        user4.setFirstName("Inna");
        user4.setLastName("Prihodko");
        user4.setAge(16);

        userDAO.insert(user1);
        userDAO.insert(user2);
        userDAO.insert(user3);
        userDAO.insert(user4);

        // init doctors
        Doctor doc1 = new Doctor();
        Doctor doc2 = new Doctor();

        doc1.setFirstName("Eduard");
        doc1.setLastName("Shvayuk");
        doc1.setAge(45);
        doc1.setDepartment("Travmatolog");
        doc2.setFirstName("Valeriya");
        doc2.setLastName("Fits");
        doc2.setAge(40);
        doc2.setDepartment("Terapevt");

        doctorDAO.insert(doc1);
        doctorDAO.insert(doc2);

        // init dagnosis
        Diagnosis diagnosis1 = new Diagnosis();
        Diagnosis diagnosis2 = new Diagnosis();

        diagnosis1.setDescription("Perelom");
        diagnosis2.setDescription("Angina");

        diagnosisDAO.insert(diagnosis1);
        diagnosisDAO.insert(diagnosis2);

        //save users, doctors and diagnosis to db
        List<User> users = null;
        List<Doctor> doctors = null;
        List<Diagnosis> diagnosis = null;
        Connection connection = ConnectionHandler.getConnectionToDb();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            users = userDAO.mapAll(result);
            ConnectionHandler.closeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM doctors");
            doctors = doctorDAO.mapAll(result);
            ConnectionHandler.closeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM diagnosis");
            diagnosis = diagnosisDAO.mapAll(result);
            ConnectionHandler.closeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Card card1 = new Card();
        Card card2 = new Card();
        Card card3 = new Card();
        Card card4 = new Card();

        card1.setUser(users.get(0));
        card1.setDoctor(doctors.get(0));
        card1.setDiagnosis(diagnosis.get(0));
        card1.setName("Card of user1");

        card2.setUser(users.get(1));
        card2.setDoctor(doctors.get(0));
        card2.setDiagnosis(diagnosis.get(1));
        card2.setName("Card of user2");

        card3.setUser(users.get(2));
        card3.setDoctor(doctors.get(1));
        card3.setDiagnosis(diagnosis.get(0));
        card3.setName("Card of user3");

        card4.setUser(users.get(3));
        card4.setDoctor(doctors.get(1));
        card4.setDiagnosis(diagnosis.get(1));
        card4.setName("Card of user4");

        cardDAO.insert(card1);
        cardDAO.insert(card2);
        cardDAO.insert(card3);
        cardDAO.insert(card4);

        List<Card> cards = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM cards");
            cards = cardDAO.mapAll(result);
            ConnectionHandler.closeStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Note note1 = new Note();
        Note note2 = new Note();
        Note note3 = new Note();
        Note note4 = new Note();
        Note note5 = new Note();
        Note note6 = new Note();
        Note note7 = new Note();
        Note note8 = new Note();
        Note note9 = new Note();
        Note note10 = new Note();

        note1.setNoteText("first note");
        note1.setDoctor(doctors.get(0));
        note2.setNoteText("Second note");
        note2.setDoctor(doctors.get(1));
        note3.setNoteText("third note");
        note3.setDoctor(doctors.get(0));
        note4.setNoteText("fourth note");
        note4.setDoctor(doctors.get(1));
        note5.setNoteText("fifth note");
        note5.setDoctor(doctors.get(0));
        note6.setNoteText("six note");
        note6.setDoctor(doctors.get(1));
        note7.setNoteText("saventh note");
        note7.setDoctor(doctors.get(0));
        note8.setNoteText("eigths note");
        note8.setDoctor(doctors.get(1));
        note9.setNoteText("ninths note");
        note9.setDoctor(doctors.get(0));
        note10.setNoteText("tenth note");
        note10.setDoctor(doctors.get(1));

        note1.setCard(cards.get(0));
        note2.setCard(cards.get(0));
        note3.setCard(cards.get(0));
        note4.setCard(cards.get(1));
        note5.setCard(cards.get(1));
        note6.setCard(cards.get(1));
        note7.setCard(cards.get(2));
        note8.setCard(cards.get(2));
        note9.setCard(cards.get(3));
        note10.setCard(cards.get(3));

        noteDAO.insert(note1);
        noteDAO.insert(note2);
        noteDAO.insert(note3);
        noteDAO.insert(note4);
        noteDAO.insert(note5);
        noteDAO.insert(note6);
        noteDAO.insert(note7);
        noteDAO.insert(note8);
        noteDAO.insert(note9);
        noteDAO.insert(note10);

        ConnectionHandler.closeConnection(connection);
    }

    private static void createTables(Connection conn) throws IOException, SQLException {
        if (conn==null)
            throw new NullPointerException();
        ScriptRunner runner = new ScriptRunner(conn,false,true);
        runner.runScript(new BufferedReader(new FileReader("./lab1/Db.sql")));
    }


    private static int createDb(Connection conn){
        int result = -1;
        try {
            Statement s = conn.createStatement();
            result = s.executeUpdate("CREATE DATABASE " + DatabaseProperties.DB_NAME);
            System.out.println(String.format("Database '%s' created successfully",DatabaseProperties.DB_NAME));
            ConnectionHandler.closeStatement(s);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
