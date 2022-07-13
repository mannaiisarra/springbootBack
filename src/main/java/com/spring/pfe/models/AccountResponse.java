package com.spring.pfe.models;

public class AccountResponse {

    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public AccountResponse() {

    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "result=" + result +
                '}';
    }


}
