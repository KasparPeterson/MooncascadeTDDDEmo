package com.kasparpeterson.mctdddemo.signup;

import android.support.annotation.NonNull;

import com.kasparpeterson.mctdddemo.R;
import com.kasparpeterson.mctdddemo.api.FailedReason;
import com.kasparpeterson.mctdddemo.api.SignUpResult;
import com.kasparpeterson.mctdddemo.utils.Utils;

/**
 * Created by kaspar on 01/04/2017.
 */

public class SignUpPresenter extends SignUpMVP.PresenterViewOperations
        implements SignUpMVP.PresenterModelOperations {

    private static final int NO_ERROR = -1;

    public SignUpPresenter(SignUpMVP.ViewOperations view, SignUpMVP.ModelOperations model) {
        super(view, model);
    }

    @Override
    void onSignUp(String name, String password) {
        int nameErrorRes = NO_ERROR;
        int passwordErrorRes = NO_ERROR;

        if (Utils.isStringEmpty(name)) {
            nameErrorRes = R.string.signup_name_empty;
        } else if (name.length() < 4) {
            nameErrorRes = R.string.signup_name_has_to_be_4_characters;
        }

        if (Utils.isStringEmpty(password) || password.length() < 8) {
            passwordErrorRes = R.string.signup_password_too_short;
        } else if (!Utils.hasCapitalLetter(password)) {
            passwordErrorRes = R.string.signup_password_no_capital_letter;
        } else if (!Utils.hasNumberInIt(password)) {
            passwordErrorRes = R.string.signup_password_no_number;
        }

        if (nameErrorRes == NO_ERROR && passwordErrorRes == NO_ERROR) {
            getModel().signUp(name, password);
        } else {
            int finalNameErrorRes = nameErrorRes;
            int finalPasswordErrorRes = passwordErrorRes;
            onView(view -> view.showView(
                    new SignUpViewState(finalNameErrorRes, finalPasswordErrorRes, null)));
        }
    }

    @Override
    public void onSignUpSucceeded(@NonNull SignUpResult signUpResult) {
        if (signUpResult.getResult() == SignUpResult.Result.SUCESS) {
            onView(SignUpMVP.ViewOperations::openMainActivity);
        } else {
            onView(view -> view.showView(new SignUpViewState(R.string.signup_name_exists, -1, null)));
        }
    }

    @Override
    public void onSignUpFailed(@NonNull FailedReason failedReason) {
        String reason = "Sign up failed!";
        if (failedReason == FailedReason.NO_CONNECTION) {
            reason = "No Internet!";
        } else if (failedReason == FailedReason.SERVER_FAULT){
            reason = "Server Fault!";
        }

        String finalReason = reason;
        onView(view -> view.showView(new SignUpViewState(NO_ERROR, NO_ERROR, finalReason)));
    }
}
