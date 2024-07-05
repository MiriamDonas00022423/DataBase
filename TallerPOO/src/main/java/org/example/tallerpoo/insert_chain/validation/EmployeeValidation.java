package org.example.tallerpoo.insert_chain.validation;

import org.example.tallerpoo.db.DB;
import org.example.tallerpoo.insert_chain.Handler;

import java.sql.SQLException;

public class EmployeeValidation extends Handler {

    private String employeeName;
    private String employeeLastName;

    @Override
    public void checkValidation() {
        DB db = DB.getDBInstance();
        try {
            if (db.getEmployeeByName(getEmployeeName(),getEmployeeLastName()) != -1) {
                getPreparedStatement().setString(3,String.valueOf(db.getEmployeeByName(getEmployeeName(),getEmployeeLastName())));
            } else {
                super.checkValidation();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }
}
