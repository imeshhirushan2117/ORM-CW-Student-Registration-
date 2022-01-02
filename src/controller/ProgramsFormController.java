package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;
import util.Validation;
import view.tm.ProgramTM;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ProgramsFormController {
    public JFXTextField txtProgramId;
    public JFXTextField txtProgram;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public JFXTextField txtSearch;
    public TableView<ProgramTM> tblPrograms;
    public TableColumn colProgramId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFee;
    public JFXButton btnRegisterId;

    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.PROGRAM);

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern courserIdPattern = Pattern.compile("^(P)[-]?[0-9]{3}$");
    Pattern courserNamePattern = Pattern.compile("^[A-z ]{1,30}$");
    Pattern courserDurationPattern = Pattern.compile("^[A-z 0-9 ]{1,10}$");
    Pattern courserFeePattern = Pattern.compile("^[0-9 ]{1,30}$");



    public void initialize(){
        showProgramsOnTable();
        storeValidations();
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        ProgramDTO programDTO = new ProgramDTO(
                txtProgramId.getText(),
                txtProgram.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );
        if (programBO.add(programDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "ProgramDTO Add To Database").show();
            showProgramsOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    private void showProgramsOnTable() {
        ObservableList<ProgramTM> list = programBO.find();

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        tblPrograms.setItems(list);

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        ProgramTM selectedItem = tblPrograms.getSelectionModel().getSelectedItem();
        String programId = selectedItem.getProgramId();
        ProgramDTO program = new ProgramDTO(
                txtProgramId.getText(),
                txtProgram.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );
        if (programBO.update(program)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Program Updated").show();
            showProgramsOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        ProgramTM selectedItem = tblPrograms.getSelectionModel().getSelectedItem();
        String programId = selectedItem.getProgramId();
        if (programBO.delete(programId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            showProgramsOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void tblProgramsOnMouseClick(MouseEvent mouseEvent) {
        try {
            ProgramTM selectedProgram = tblPrograms.getSelectionModel().getSelectedItem();
            txtProgramId.setText(selectedProgram.getProgramId());
            txtProgram.setText(selectedProgram.getProgramName());
            txtDuration.setText(selectedProgram.getDuration());
            txtFee.setText("" + selectedProgram.getFee());

        } catch (Exception e) {

        }
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
        ObservableList<ProgramTM> search = programBO.search(txtSearch.getText());
        tblPrograms.setItems(search);
    }
    public void programeKeyReleased(KeyEvent keyEvent) {
        btnRegisterId.setDisable(true);
        Object response = Validation.validate(map,btnRegisterId,"Green");
        if (keyEvent.getCode()== KeyCode.ENTER) {
            if (response instanceof TextField){
                TextField error  = (TextField) response;
                error.requestFocus();
            }else if (response instanceof Boolean){
                new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            }
        }
    }
    private void storeValidations() {
        map.put(txtProgramId,courserIdPattern);
        map.put(txtProgram,courserNamePattern);
        map.put(txtDuration,courserDurationPattern);
        map.put(txtFee,courserFeePattern);
    }

}
