package dao.custom.impl;


import dao.custom.StudentDAO;
import entity.Program;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Student entity) {
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
        Student student = session.get(Student.class, s);
        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Student> find() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Student");

        List<Student> list = query.list();
        transaction.commit();
        session.close();
        return list;

    }

    @Override
    public List<String> getAllProgramIds() {
        return null;
    }

    @Override
    public List<Student> searchStudents(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Student s WHERE s.regNo LIKE ?1");
        query.setParameter(1,'%'+value+'%');
        List list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean register(Student student, String cmb1, String cmb2, String cmb3) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        if (cmb1 != null && !cmb1.trim().isEmpty()) {
            Program program1 = session.get(Program.class, cmb1);
            student.getProgramList().add(program1);
        } else {
            return false;
        }

        if (cmb2 != null && !cmb2.trim().isEmpty()) {
            Program program2 = session.get(Program.class, cmb2);
            student.getProgramList().add(program2);
        }

        if (cmb3 != null && !cmb3.trim().isEmpty()) {
            Program program3 = session.get(Program.class, cmb3);
            student.getProgramList().add(program3);
        }
        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteRegister(Student student, String cmb1, String cmb2, String cmb3) {
       return false;
    }

    @Override
    public boolean updateRegister(String studentRegNo, String cmb1) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        int i = session.createNativeQuery("INSERT INTO student_program VALUES(?,?)").setParameter(1, studentRegNo).setParameter(2, cmb1).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
}
