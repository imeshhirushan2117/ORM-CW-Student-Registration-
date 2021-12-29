package dao.custom;

import dao.SuperDAO;
import dto.ProgramDTO;
import entity.Program;

import java.util.List;

public interface ProgramDAO extends SuperDAO<Program,String> {
    List<Program> searchPrograms(String value);
    List<String> getAllProgramIds();
    ProgramDTO getProgramList(String id);

}
