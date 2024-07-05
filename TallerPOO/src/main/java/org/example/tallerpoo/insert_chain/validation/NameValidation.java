package org.example.tallerpoo.insert_chain.validation;

import org.example.tallerpoo.insert_chain.Handler;

import java.sql.SQLException;

public class NameValidation extends Handler {

    private String name;

    @Override
    public void checkValidation() {
        try {
            if(getName() != null) {
                getPreparedStatement().setString(1, getName());
            } else {
                super.checkValidation();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
