package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import javafx.collections.ObservableList;
import view.tm.ProgramTM;

import java.util.List;

public interface ProgramBO extends SuperBO {
    boolean add(ProgramDTO programDTO);

    ObservableList<ProgramTM> find();

    boolean delete(String id);

    boolean update(ProgramDTO programDTO);

    ObservableList<ProgramTM> search(String value);

    List<String> getAllProgramIds();

    ProgramDTO getProgramDetails(String id);

    public ObservableList<ProgramTM> getStudentPrograms(String value);

}
