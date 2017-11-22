package com.org;

import java.sql.SQLException;

/**
 * Created by Elat on 19.04.2017.
 */
public class MyException extends SQLException {

    private String message;

    public MyException(String s) {
        message = s;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
