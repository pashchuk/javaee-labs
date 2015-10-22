package db.DAO;

/**
 * Created by mamax on 10/22/2015.
 */
public class DAOFactory {
    static DAOFactory instance;
    static CardDAO cardDAO;
    static DiagnosisDAO diagnosisDAO;
    static DoctorDAO doctorDAO;
    static NoteDAO noteDAO;
    static UserDAO userDAO;
    static Object lockObject = new Object();

    public static DAOFactory getInstance() {
        if(instance==null)
            synchronized (lockObject){
                if(instance==null)
                    instance = new DAOFactory();
            }
        return instance;
    }

    public static CardDAO getCardDAO() {
        if(cardDAO==null)
            cardDAO = new CardDAO();
        return cardDAO;
    }

    public static DiagnosisDAO getDiagnosisDAO() {
        if(diagnosisDAO==null)
            diagnosisDAO = new DiagnosisDAO();
        return diagnosisDAO;
    }

    public static DoctorDAO getDoctorDAO() {
        if(doctorDAO==null)
            doctorDAO = new DoctorDAO();
        return doctorDAO;
    }

    public static NoteDAO getNoteDAO() {
        if(noteDAO==null)
            noteDAO = new NoteDAO();
        return noteDAO;
    }

    public static UserDAO getUserDAO() {
        if(userDAO==null)
            userDAO = new UserDAO();
        return userDAO;
    }
}
