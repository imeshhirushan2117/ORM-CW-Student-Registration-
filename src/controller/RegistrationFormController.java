package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class RegistrationFormController {
    public RadioButton genderMale;
    public RadioButton genderFemale;
    public JFXComboBox cmdProgramId1;
    public JFXTextField txtProgramName1;
    public JFXTextField txtDuration1;
    public JFXTextField txtFee1;
    public JFXComboBox cmdProgramId2;
    public JFXTextField txtProgramName2;
    public JFXTextField txtDuration2;
    public JFXTextField txtFee2;
    public JFXComboBox cmdProgramId3;
    public JFXTextField txtProgramName3;
    public JFXTextField txtDuration3;
    public JFXTextField txtFee3;
    public JFXTextField txtName;
    public JFXTextField txtAge;
    public JFXTextField txtContactNo;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtSearch;
    public TableView tblReg;
    public TableColumn colRegNo;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colContactNo;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public JFXTextField txtRegNo;
    public ToggleGroup gender;

    public void btnRegisterOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }
}
