package com.kasparpeterson.mctdddemo.signup;

import com.kasparpeterson.mctdddemo.R;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by kaspar on 05/04/2017.
 */

public class SignUpPresenterTest {

    private static final String VALID_NAME = "Name";
    private static final String VALID_PASSWORD = "P2345678";

    private SignUpMVP.ViewOperations view;
    private SignUpMVP.ModelOperations model;
    private SignUpPresenter presenter;

    @Before
    public void setup() {
        view = mock(SignUpMVP.ViewOperations.class);
        model = mock(SignUpMVP.ModelOperations.class);
        presenter = new SignUpPresenter(view, model);
    }

    @Test
    public void onSignUpClicked_name_null() {
        presenter.onSignUpClicked(null, VALID_PASSWORD);
        verify(view, times(1)).showView(new SignUpViewState(R.string.signup_name_empty, -1, -1));
    }

    @Test
    public void onSignUpClicked_name_empty() {
        presenter.onSignUpClicked("", VALID_PASSWORD);
        verify(view, times(1)).showView(new SignUpViewState(R.string.signup_name_empty, -1, -1));
    }

    @Test
    public void onSignUpClicked_name_blank() {
        presenter.onSignUpClicked("    ", VALID_PASSWORD);
        verify(view, times(1)).showView(new SignUpViewState(R.string.signup_name_empty, -1, -1));
    }

    @Test
    public void onSignUpClicked_name_tooShort() {
        presenter.onSignUpClicked("Nam", VALID_PASSWORD);
        verify(view, times(1)).showView(new SignUpViewState(R.string.signup_name_too_short, -1, -1));
    }

    @Test
    public void onSignUpClicked_password_null() {
        presenter.onSignUpClicked(VALID_NAME, null);
        verify(view, times(1)).showView(new SignUpViewState(-1, R.string.signup_password_too_short, -1));
    }

    @Test
    public void onSignUpClicked_password_tooShort() {
        presenter.onSignUpClicked(VALID_NAME, "P123456");
        verify(view, times(1)).showView(new SignUpViewState(-1, R.string.signup_password_too_short, -1));
    }

    @Test
    public void onSignUpClicked_valid() {
        presenter.onSignUpClicked(VALID_NAME, VALID_PASSWORD);
        verify(view, times(1)).showView(new SignUpViewState(-1, -1, R.string.signup_signup_successful));
    }

}
