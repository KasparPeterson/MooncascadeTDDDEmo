package com.kasparpeterson.mctdddemo.signup;

import lombok.Data;

/**
 * Created by kaspar on 05/04/2017.
 */
@Data
public class SignUpViewState {

    private final int nameErrorRes;
    private final int passworrdErrorRes;
    private final int stateMessageRes;

    public SignUpViewState(int nameErrorRes, int passworrdErrorRes, int stateMessageRes) {
        this.nameErrorRes = nameErrorRes;
        this.passworrdErrorRes = passworrdErrorRes;
        this.stateMessageRes = stateMessageRes;
    }
}
