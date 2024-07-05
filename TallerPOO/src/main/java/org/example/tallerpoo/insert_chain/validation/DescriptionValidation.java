package org.example.tallerpoo.insert_chain.validation;

import org.example.tallerpoo.insert_chain.Handler;

import java.sql.SQLException;

public class DescriptionValidation extends Handler {

    private String description;

    @Override
    public void checkValidation() {
        try {
            if (getDescription() != null) {
                getPreparedStatement().setString(2,getDescription());
            } else {
                super.checkValidation();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
