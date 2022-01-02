package dao.custom.impl;

import dao.QueryDAO;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import util.FactoryConfiguration;

import java.util.List;

public class QueryDAOIMPL implements QueryDAO {

    @Override
    public List<Program> getStudentProgram(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select p.* from student_program sp inner join program p ON sp.programList_programId=p.programId where sp.students_regNo=?1";
        NativeQuery nativeQuery = session.createNativeQuery(sql).addEntity(Program.class);
        nativeQuery.setParameter(1,value);
        List list = nativeQuery.list();

        transaction.commit();
        session.close();
        return list;

    }
}
