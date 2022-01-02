package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.impl.StudentDAOImpl;
import dto.ProgramDTO;
import dto.StudentDTO;
import entity.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.Validation;
import view.tm.StudentTM;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

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
    public JFXButton btnRegister;

    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.STUDENT);
    ProgramBOImpl programBO = (ProgramBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoTypes.PROGRAM);
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    String cmb1, cmb2, cmb3;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern studentIdPattern = Pattern.compile("^(R)[-]?[0-9]{3}$");
    Pattern studentNamePattern = Pattern.compile("^[A-z ]{1,30}$");
    Pattern studentAddressPattern = Pattern.compile("^[A-z0-9/]{6,30}$");
    Pattern studentTeleNumberPattern = Pattern.compile("^[0-9]{10}$");
    Pattern studentAgePattern = Pattern.compile("^[0-9]{2}$");
    Pattern studentEmailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");


    private void storeValidations() {
        map.put(txtRegNo,studentIdPattern);
        map.put(txtName,studentNamePattern);
        map.put(txtAge,studentAgePattern);
        map.put(txtContactNo,studentTeleNumberPattern);
        map.put(txtAddress,studentAddressPattern);
        map.put(txtEmail,studentEmailPattern);
    }





    public void initialize() {
        showStudentOnTable();
        loadProgramId();
        storeValidations();
        cmdProgramId1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName1, txtDuration1, txtFee1, newValue);
            cmb1=newValue;
        });
        cmdProgramId2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName2, txtDuration2, txtFee2, newValue);
            cmb2=newValue;
        });

        cmdProgramId3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramName3, txtDuration3, txtFee3, newValue);
            cmb3=newValue;
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
        Student student1 = new Student();
        student1.setRegNo(txtRegNo.getText());
        student1.setName(txtName.getText());
        student1.setAge(Integer.parseInt(txtAge.getText()));
        student1.setContactNo(txtContactNo.getText());
        student1.setAddress(txtAddress.getText());
        student1.setEmail(txtEmail.getText());
        student1.setGender(SelectGender());

        if (studentDAO.register(student1, cmb1, cmb2, cmb3)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Student Register").show();
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

        if (studentDAO.updateRegister(txtRegNo.getText(), cmb1)) {
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

            if(selectedProgram.getGender().equals("Male")){
                genderMale.setSelected(true);
            }else if (selectedProgram.getGender().equals("Female")){
                genderFemale.setSelected(true);
            }
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

    public void registrationKeyReleased(KeyEvent keyEvent) {
        btnRegister.setDisable(true);
        Object response = Validation.validate(map,btnRegister,"Green");
        if (keyEvent.getCode()== KeyCode.ENTER) {
            if (response instanceof TextField){
                TextField error  = (TextField) response;
                error.requestFocus();
            }else if (response instanceof Boolean){
                new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            }
        }

    }
}
