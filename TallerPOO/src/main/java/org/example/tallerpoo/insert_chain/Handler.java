package org.example.tallerpoo.insert_chain;

import java.sql.PreparedStatement;

public class Handler implements IHandler{

    PreparedStatement preparedStatement;

    @Override
    public void checkValidation() {
        System.out.println("ERROR MALO");
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }
}
