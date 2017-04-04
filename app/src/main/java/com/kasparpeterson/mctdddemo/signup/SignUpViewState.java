package com.kasparpeterson.mctdddemo.signup;

import lombok.Data;

/**
 * Created by kaspar on 01/04/2017.
 */
@Data
public class SignUpViewState {

    private final int nameErrorRes;
    private final int passwordErrorRes;

    private final String backendError;
}
