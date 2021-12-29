package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import dto.StudentDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import view.tm.StudentTM;

import java.util.List;

public class RegistrationFormController {
    public RadioButton genderMale;
    public RadioButton genderFemale;
    public JFXComboBox<String> cmdProgramId1;
    public JFXTextField txtProgramName1;
    public JFXTextField txtDuration1;
    public JFXTextField txtFee1;
    public JFXComboBox<String> cmdProgramId2;
    public JFXTextField txtProgramName2;
    public JFXTextField txtDuration2;
    public JFXTextField txtFee2;
    public JFXComboBox<String> cmdProgramId3;
    public JFXTextField txtProgramName3;
    public JFXTextField txtDuration3;
    public JFXTextField txtFee3;
    public JFXTextField txtName;
    public JFXTextField txtAge;
    public JFXTextField txtContactNo;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtSearch;
    public TableView<StudentTM> tblReg;
    public TableColumn colRegNo;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colContactNo;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colGender;

    public JFXTextField txtRegNo;
    public ToggleGroup gender;
    public JFXCheckBox checkBox1;
    public JFXCheckBox checkBox2;
    public JFXCheckBox checkBox3;

    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.STUDENT);
    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.PROGRAM);

    public void initialize() {
        showStudentOnTable();
        loadProgramId();
        cmdProgramId1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName1, txtDuration1, txtFee1, newValue);
        });
        cmdProgramId2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName2, txtDuration2, txtFee2, newValue);
        });
        cmdProgramId3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName3, txtDuration3, txtFee3, newValue);
        });
        setDisable();
    }

    public String SelectGender() {
        String selectGender = null;
        if (genderMale.isSelected()) {
            selectGender = "Male";
        } else if (genderFemale.isSelected()) {
            selectGender = "Female";
        }
        return selectGender;
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO(
                txtRegNo.getText(),
                txtName.getText(),
                Integer.parseInt(txtAge.getText()),
                txtContactNo.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                SelectGender()
        );
        if (studentBO.add(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Programme Added").show();
            showStudentOnTable();
            clearTexts();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    private void showStudentOnTable() {
        ObservableList<StudentTM> list = studentBO.find();
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblReg.setItems(list);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblReg.getSelectionModel().getSelectedItem();
        String studentId = selectedItem.getRegNo();
        StudentDTO studentDTO = new StudentDTO(
                txtRegNo.getText(),
                txtName.getText(),
                Integer.parseInt(txtAge.getText()),
                txtContactNo.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                SelectGender()
        );
        if (studentBO.update(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Program Updated").show();
            showStudentOnTable();
            clearTexts();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblReg.getSelectionModel().getSelectedItem();
        String studentId = selectedItem.getRegNo();
        if (studentBO.delete(studentId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            showStudentOnTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void regOnMouseClicked(MouseEvent mouseEvent) {
        try {
            StudentTM selectedProgram = tblReg.getSelectionModel().getSelectedItem();
            txtRegNo.setText(selectedProgram.getRegNo());
            txtName.setText(selectedProgram.getName());
            txtAge.setText("" + selectedProgram.getAge());
            txtContactNo.setText(selectedProgram.getContactNo());
            txtAddress.setText(selectedProgram.getAddress());
            txtEmail.setText(selectedProgram.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearTexts() {
        txtRegNo.clear();
        txtName.clear();
        txtAge.clear();
        txtContactNo.clear();
        txtAddress.clear();
        txtEmail.clear();

        txtRegNo.requestFocus();
    }

    private void loadProgramId() {
        List<String> allProgramIds = programBO.getAllProgramIds();
        cmdProgramId1.getItems().addAll(allProgramIds);
        cmdProgramId2.getItems().addAll(allProgramIds);
        cmdProgramId3.getItems().addAll(allProgramIds);
    }

    private void setProgramData(JFXTextField enterProgram, JFXTextField enterDuration, JFXTextField enterFee, String ProgramID) {
        ProgramDTO programDetails = programBO.getProgramDetails(ProgramID);

        if (programDetails == null) {
        } else {
            enterProgram.setText(programDetails.getProgramName());
            enterDuration.setText(programDetails.getDuration());
            enterFee.setText(programDetails.getFee() + "");
        }
    }

    public void searchOnKeyReleased(KeyEvent keyEvent) {
        ObservableList<StudentTM> search = studentBO.search(txtSearch.getText());
        tblReg.setItems(search);
    }

    public void OnMouseClicked(MouseEvent mouseEvent) {
        if (checkBox2.isSelected()) {
            cmdProgramId2.setDisable(false);
            txtProgramName2.setDisable(false);
            txtDuration2.setDisable(false);
            txtFee2.setDisable(false);
        } else {
            cmdProgramId2.setDisable(true);
            txtProgramName2.setDisable(true);
            txtDuration2.setDisable(true);
            txtFee2.setDisable(true);
        }

        if (checkBox3.isSelected()) {
            cmdProgramId3.setDisable(false);
            txtProgramName3.setDisable(false);
            txtDuration3.setDisable(false);
            txtFee3.setDisable(false);
        } else {
            cmdProgramId3.setDisable(true);
            txtProgramName3.setDisable(true);
            txtDuration3.setDisable(true);
            txtFee3.setDisable(true);
        }
    }

    private void setDisable() {
        cmdProgramId2.setDisable(true);
        txtProgramName2.setDisable(true);
        txtDuration2.setDisable(true);
        txtFee2.setDisable(true);

        cmdProgramId3.setDisable(true);
        txtProgramName3.setDisable(true);
        txtDuration3.setDisable(true);
        txtFee3.setDisable(true);
    }
}
