package dao.custom;

import dao.SuperDAO;
import dto.ProgramDTO;
import entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDAO<Student,String> {  List<String> getAllProgramIds();
    List<Student> searchStudents(String value);
    boolean register(Student student, String cmb1,String cmb2,String cmb3);
    boolean deleteRegister(Student student, String cmb1,String cmb2,String cmb3);
    boolean updateRegister(String studentRegNo, String cmb1);


}
