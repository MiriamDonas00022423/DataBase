package org.example.tallerpoo.insert_chain.validation;

import org.example.tallerpoo.db.DB;
import org.example.tallerpoo.insert_chain.Handler;

import java.sql.SQLException;

public class CategoryValidation extends Handler {

    private String name;

    @Override
    public void checkValidation() {
        DB db = DB.getDBInstance();
        try {
            if(db.getCategoryByName(getName()) != -1){
                getPreparedStatement().setString(4, String.valueOf(db.getCategoryByName(getName())));
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
