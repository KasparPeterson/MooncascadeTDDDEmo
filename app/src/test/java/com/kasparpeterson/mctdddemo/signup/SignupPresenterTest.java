package com.kasparpeterson.mctdddemo.signup;

import com.kasparpeterson.mctdddemo.R;
import com.kasparpeterson.mctdddemo.api.FailedReason;
import com.kasparpeterson.mctdddemo.api.SignUpResult;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by kaspar on 01/04/2017.
 */

public class SignupPresenterTest {

    private static final String validName = "MockName";
    private static final String validPassword = "TopSecret123";

    private SignUpPresenter presenter;
    private SignUpMVP.ViewOperations view;
    private SignUpMVP.ModelOperations model;

    @Before
    public void setup() {
        view = mock(SignUpMVP.ViewOperations.class);
        model = mock(SignUpMVP.ModelOperations.class);
        presenter = new SignUpPresenter(view, model);
    }

    // View -> Presenter
    @Test
    public void onSignUp() {
        presenter.onSignUp(validName, validPassword);
        verify(model).signUp(validName, validPassword);
    }

    @Test
    public void onSignUp_nameHasToBeFilled_nameNull() {
        presenter.onSignUp(null, validPassword);
        verify(view).showView(new SignUpViewState(R.string.signup_name_empty, -1, null));
    }

    @Test
    public void onSignUp_nameHasToBeFilled_nameEmpty() {
        presenter.onSignUp("", validPassword);
        verify(view).showView(new SignUpViewState(R.string.signup_name_empty, -1, null));
    }

    @Test
    public void onSignUp_nameHasToBeFilled_nameBlank() {
        presenter.onSignUp(" ", validPassword);
        verify(view).showView(new SignUpViewState(R.string.signup_name_empty, -1, null));
    }

    @Test
    public void onSignUp_nameHasToBeAtLeast4Chars_isLess() {
        presenter.onSignUp("a", validPassword);
        verify(view).showView(new SignUpViewState(R.string.signup_name_has_to_be_4_characters, -1, null));
    }

    @Test
    public void onSignUp_nameHasToBeAtLeast4Chars_isLess2() {
        presenter.onSignUp("abc", validPassword);
        verify(view).showView(new SignUpViewState(R.string.signup_name_has_to_be_4_characters, -1, null));
    }

    @Test
    public void onSignUp_atLeast8Characters_null() {
        presenter.onSignUp(validName, null);
        verify(view).showView(new SignUpViewState(-1, R.string.signup_password_too_short, null));
    }

    @Test
    public void onSignUp_atLeast8Characters_empty() {
        presenter.onSignUp(validName, "");
        verify(view).showView(new SignUpViewState(-1, R.string.signup_password_too_short, null));
    }

    @Test
    public void onSignUp_atLeast8Characters_blank() {
        presenter.onSignUp(validName, " ");
        verify(view).showView(new SignUpViewState(-1, R.string.signup_password_too_short, null));
    }

    @Test
    public void onSignUp_atLeast8Characters_tooShort() {
        presenter.onSignUp(validName, "a");
        verify(view).showView(new SignUpViewState(-1, R.string.signup_password_too_short, null));
    }

    @Test
    public void onSignUp_atLeast8Characters_tooShort2() {
        presenter.onSignUp(validName, "abcdefg");
        verify(view).showView(new SignUpViewState(-1, R.string.signup_password_too_short, null));
    }

    @Test
    public void onSignUp_atLeastOneCapitaLetter() {
        presenter.onSignUp(validName, "abcdefghijk");
        verify(view).showView(new SignUpViewState(-1,
                R.string.signup_password_no_capital_letter, null));
    }

    @Test
    public void onSignUp_atLeastOneCapitaLetter2() {
        presenter.onSignUp(validName, "abcdefghijk123123");
        verify(view).showView(new SignUpViewState(-1,
                R.string.signup_password_no_capital_letter, null));
    }

    @Test
    public void onSignUp_atLeastOneNumber() {
        presenter.onSignUp(validName, "abcdefghijkABC");
        verify(view).showView(new SignUpViewState(-1,
                R.string.signup_password_no_number, null));
    }

    @Test
    public void onSignUp_atLeastOneNumber2() {
        presenter.onSignUp(validName, "AbcabABCasd");
        verify(view).showView(new SignUpViewState(-1,
                R.string.signup_password_no_number, null));
    }

    // Model -> Presenter
    @Test
    public void onSignUpSucceeded_SUCCESS() {
        presenter.onSignUpSucceeded(new SignUpResult(SignUpResult.Result.SUCESS));
        verify(view).openMainActivity();
    }

    @Test
    public void onSignUpSucceeded_USER_EXISTS() {
        presenter.onSignUpSucceeded(new SignUpResult(SignUpResult.Result.USER_EXISTS));
        verify(view).showView(new SignUpViewState(R.string.signup_name_exists, -1, null));
    }

    @Test
    public void onSignUpFailed_NO_CONNECTION() {
        presenter.onSignUpFailed(FailedReason.NO_CONNECTION);
        verify(view).showView(new SignUpViewState(-1, -1, "No Internet!"));
    }

    @Test
    public void onSignUpFailed_SERVER_FAULT() {
        presenter.onSignUpFailed(FailedReason.SERVER_FAULT);
        verify(view).showView(new SignUpViewState(-1, -1, "Server Fault!"));
    }

}
