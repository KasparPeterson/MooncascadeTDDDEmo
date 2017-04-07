package com.kasparpeterson.mctdddemo.signup;

import com.kasparpeterson.mctdddemo.R;

/**
 * Created by kaspar on 05/04/2017.
 */

public class SignUpPresenter extends SignUpMVP.PresenterViewOperations
        implements SignUpMVP.PresenterModelOperations {

    public SignUpPresenter(SignUpMVP.ViewOperations view, SignUpMVP.ModelOperations model) {
        super(view, model);
    }

    @Override
    void onSignUpClicked(String name, String password) {
        int nameErrorRes = -1;
        int passwordErrorRes = -1;
        int stateMessageRes = R.string.signup_signup_successful;

        if (name == null || name.length() == 0 || name.trim().length() == 0) {
            nameErrorRes = R.string.signup_name_empty;
        } else if (name.trim().length() < 4) {
            nameErrorRes = R.string.signup_name_too_short;
        }

        if (password == null || password.trim().length() < 8) {
            passwordErrorRes = R.string.signup_password_too_short;
        }

        if (nameErrorRes > 0 || passwordErrorRes > 0) {
            stateMessageRes = -1;
        }
        SignUpViewState viewState = new SignUpViewState(nameErrorRes, passwordErrorRes, stateMessageRes);
        onView(view -> view.showView(viewState));
    }
}
