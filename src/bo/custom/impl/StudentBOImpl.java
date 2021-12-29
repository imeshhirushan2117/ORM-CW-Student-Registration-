package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dto.StudentDTO;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.StudentTM;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO{
   dao.custom.impl.StudentDAOImpl studentDAO= (dao.custom.impl.StudentDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public boolean add(StudentDTO studentDTO) {
        return studentDAO.add(new Student(
                studentDTO.getRegNo(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getContactNo(),
                studentDTO.getAddress(),
                studentDTO.getEmail(),
                studentDTO.getGender()
        ));
    }

    @Override
    public ObservableList<StudentTM> find(){
        List<Student> list = studentDAO.find();
        ObservableList<StudentTM> dtoArrayList = FXCollections.observableArrayList();
        StudentDTO studentDTO=null;
        for (Student student:list){
            dtoArrayList.add(new StudentTM(
                    student.getRegNo(),
                    student.getName(),
                    student.getAge(),
                    student.getContactNo(),
                    student.getAddress(),
                    student.getEmail(),
                    student.getGender()
            ));
        }
        return dtoArrayList;
    }

    @Override
    public boolean delete(String id) {
        return studentDAO.delete(id);
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        return studentDAO.update(new Student(
                studentDTO.getRegNo(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getContactNo(),
                studentDTO.getAddress(),
                studentDTO.getEmail(),
                studentDTO.getGender()
        ));
    }

    @Override
    public ObservableList<StudentTM> search(String value) {
        List<Student> list = studentDAO.searchStudents(value);
        ObservableList<StudentTM> dtoArrayList = FXCollections.observableArrayList();
        for (Student student : list
        ) {
            dtoArrayList.add(new StudentTM(
                   student.getRegNo(),
                    student.getName(),
                    student.getAge(),
                    student.getContactNo(),
                    student.getAddress(),
                    student.getEmail(),
                    student.getGender()));
        }
        return dtoArrayList;
    }
}
