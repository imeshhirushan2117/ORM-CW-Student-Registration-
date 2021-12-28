package bo.custom.impl;

import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.custom.impl.ProgramDAOIMPL;
import dto.ProgramDTO;
import entity.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.ProgramTM;

import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    ProgramDAOIMPL programDAO = (ProgramDAOIMPL) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean add(ProgramDTO programDTO) {
        return programDAO.add(new Program(
                programDTO.getProgramId(),
                programDTO.getProgramName(),
                programDTO.getDuration(),
                programDTO.getFee()
        ));
    }

    @Override
    public ObservableList<ProgramTM> find() {
        List<Program> list = programDAO.find();
        ObservableList<ProgramTM> dtoArrayList = FXCollections.observableArrayList();
        // ProgramDTO programDTO=null;

        for (Program program : list) {
            dtoArrayList.add(new ProgramTM(
                    program.getProgramId(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee()
            ));
        }
        return dtoArrayList;
    }

    @Override
    public boolean delete(String id) {
        return programDAO.delete(id);
    }

    @Override
    public boolean update(ProgramDTO programDTO) {
        return programDAO.update(new Program(
                programDTO.getProgramId(),
                programDTO.getProgramName(),
                programDTO.getDuration(),
                programDTO.getFee()
        ));
    }
}
