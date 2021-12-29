package dao.custom;

import dao.SuperDAO;
import dto.ProgramDTO;
import entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDAO<Student,String> {  List<String> getAllProgramIds();
    List<Student> searchStudents(String value);
}
