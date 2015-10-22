package db.DAO;

/**
 * Created by mamax on 10/22/2015.
 */
public class DAOFactory {
    private static DAOFactory instance;
    private static CardDAO cardDAO;
    private static DiagnosisDAO diagnosisDAO;
    private static DoctorDAO doctorDAO;
    private static NoteDAO noteDAO;
    private static UserDAO userDAO;
    private static Object lockObject = new Object();

    private DAOFactory(){}

    public static DAOFactory getInstance() {
        if(instance==null)
            synchronized (lockObject){
                if(instance==null)
                    instance = new DAOFactory();
            }
        return instance;
    }

    public CardDAO getCardDAO() {
        if(cardDAO==null)
            cardDAO = new CardDAO();
        return cardDAO;
    }

    public DiagnosisDAO getDiagnosisDAO() {
        if(diagnosisDAO==null)
            diagnosisDAO = new DiagnosisDAO();
        return diagnosisDAO;
    }

    public DoctorDAO getDoctorDAO() {
        if(doctorDAO==null)
            doctorDAO = new DoctorDAO();
        return doctorDAO;
    }

    public NoteDAO getNoteDAO() {
        if(noteDAO==null)
            noteDAO = new NoteDAO();
        return noteDAO;
    }

    public UserDAO getUserDAO() {
        if(userDAO==null)
            userDAO = new UserDAO();
        return userDAO;
    }
}
