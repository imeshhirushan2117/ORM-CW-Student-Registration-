package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;

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
    public List<StudentDTO> find() {
        List<Student> list = studentDAO.find();
        ArrayList<StudentDTO>  dtoArrayList= new ArrayList<>();
        StudentDTO studentDTO=null;
        for (Student student:list){
            dtoArrayList.add(new StudentDTO(
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
}
