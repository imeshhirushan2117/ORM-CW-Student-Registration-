package dao;


import dao.custom.impl.ProgramDAOIMPL;
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
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOIMPL();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT, PROGRAM
    }
}