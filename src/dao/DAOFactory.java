package dao;


import dao.custom.impl.ProgramDAOIMPL;
import dao.custom.impl.QueryDAOIMPL;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public SuperDAOUltra getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOIMPL();
            case Query:
                return new QueryDAOIMPL();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT, PROGRAM,Query
    }
}