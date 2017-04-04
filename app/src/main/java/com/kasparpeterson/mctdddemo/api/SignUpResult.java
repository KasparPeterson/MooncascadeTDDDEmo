package com.kasparpeterson.mctdddemo.api;

import lombok.Data;

/**
 * Created by kaspar on 03/04/2017.
 */
@Data
public class SignUpResult {

    public enum Result {
        SUCESS,
        USER_EXISTS
    }

    private Result result;

    public SignUpResult(Result result) {
        this.result = result;
    }
}
