package dao.custom.impl;


import dao.custom.ProgramDAO;
import dto.ProgramDTO;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class ProgramDAOIMPL implements ProgramDAO {
    @Override
    public boolean add(Program entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Program program = session.get(Program.class, s);
        session.delete(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Program> find() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Program");

        List<Program> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<Program> searchPrograms(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Program p WHERE p.programId LIKE ?1");
        query.setParameter(1, '%' + value + '%');
        List list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<String> getAllProgramIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT p.programId FROM Program p");
        List<String> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public ProgramDTO getProgramList(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Program p WHERE p.programId = ?1");
        query.setParameter(1, id);
        List<Program> resultList = query.getResultList();

        transaction.commit();
        session.close();
        ProgramDTO programDTO = null;
        if (!resultList.isEmpty()) {
            for (Program list : resultList
            ) {
                programDTO = new ProgramDTO(
                        list.getProgramName(),
                        list.getDuration(),
                        list.getFee()
                );
            }
            return programDTO;
        } else {
            return null;
        }
    }
}
