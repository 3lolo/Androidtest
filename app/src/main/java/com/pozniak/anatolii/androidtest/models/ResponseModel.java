package com.pozniak.anatolii.androidtest.models;

import java.util.List;

/**
 * Created by Владелец on 24.10.2017.
 */

public class ResponseModel<T> {
    private String message;
    private List<T> data;

    public ResponseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
