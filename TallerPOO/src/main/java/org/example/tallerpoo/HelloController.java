package org.example.tallerpoo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.tallerpoo.db.DB;

import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    final DB db = DB.getDBInstance();

    @FXML
    private ComboBox<String> category;

    @FXML
    private ComboBox<String> category1;

    @FXML
    private TextField date;

    @FXML
    private TextArea description;

    @FXML
    private TextField employee;

    @FXML
    private TextField employeeLastName;

    @FXML
    private TextField employeeName;

    @FXML
    private TextField firstDate;

    @FXML
    private ListView<String> firstList;

    @FXML
    private TextField lastDate;

    @FXML
    private TextField name;

    @FXML
    private TextField newCategory;

    @FXML
    private TextField newEmployeeLastName;

    @FXML
    private TextField newEmployeeName;

    @FXML
    private TextField price;

    @FXML
    private ListView<String> secondList;

    @FXML
    void submit(ActionEvent event) {

        db.insertRegistry(name.getText(),description.getText(), employeeName.getText(), employeeLastName.getText(),category.getValue(),date.getText(),price.getText());
        name.setText("");
        description.setText("");
        employeeName.setText("");
        employeeLastName.setText("");
        category.getItems().removeAll(db.getAllCategories());
        category.getItems().addAll(db.getAllCategories());
        date.setText("");
        price.setText("");

    }

    @FXML
    void submitNewCategory(ActionEvent event) {
        String categoryName = newCategory.getText();
        db.insertCategory(categoryName);
        newCategory.setText("");
        updateCategory();
    }

    @FXML
    void submitNewEmployee(ActionEvent event) {
        String employeeName = newEmployeeName.getText();
        String employeeLastName = newEmployeeLastName.getText();

        db.insertEmployee(employeeName, employeeLastName);
        newEmployeeLastName.setText("");
        newEmployeeName.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCategory();
        category1.getItems().removeAll(db.getAllCategories());
        category1.getItems().addAll(db.getAllCategories());
    }


    @FXML
    void watchList(ActionEvent event) {

        firstList.getItems().addAll(db.getChoresByEmployee(employee.getText(),firstDate.getText(),lastDate.getText()));
        employee.setText("");
        firstDate.setText("");
        lastDate.setText("");
    }

    @FXML
    void watchList1(ActionEvent event) {

        secondList.getItems().addAll(db.getChoresByCategory(category1.getValue()));

        category1.getItems().removeAll(db.getAllCategories());
        category1.getItems().addAll(db.getAllCategories());

    }

    private void updateCategory(){
        category.getItems().removeAll(db.getAllCategories());
        category.getItems().addAll(db.getAllCategories());

    }



}


