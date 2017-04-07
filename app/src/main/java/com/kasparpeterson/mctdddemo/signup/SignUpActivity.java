package com.kasparpeterson.mctdddemo.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.kasparpeterson.mctdddemo.R;
import com.kasparpeterson.simplemvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kaspar on 01/04/2017.
 */

public class SignUpActivity extends MVPBaseActivity<SignUpMVP.PresenterViewOperations>
        implements SignUpMVP.ViewOperations {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    @Override
    protected void callSetupPresenter() {
        setupPresenter(this, TAG, SignUpMVP.PresenterViewOperations.TAG);
    }

    @Override
    protected SignUpMVP.PresenterViewOperations initialisePresenter() {
        return new SignUpPresenter(this, new SignUpModel());
    }

    @BindView(R.id.signup_name_edit_text) EditText nameEditText;
    @BindView(R.id.signup_password_edit_text) EditText passwordEditText;
    @BindView(R.id.signup_state_text_view) TextView stateTextView;

    @OnClick(R.id.signup_button)
    public void onSignUpClicked() {
        getPresenter().onSignUpClicked(nameEditText.getText().toString(),
                passwordEditText.getText().toString());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_view);
        ButterKnife.bind(this);
    }

    @Override
    public void showView(@NonNull SignUpViewState viewState) {
        if (viewState.getNameErrorRes() > 0) {
            nameEditText.setError(getString(viewState.getNameErrorRes()));
        }

        if (viewState.getPassworrdErrorRes() > 0) {
            passwordEditText.setError(getString(viewState.getPassworrdErrorRes()));
        }

        if (viewState.getStateMessageRes() > 0) {
            stateTextView.setText(viewState.getStateMessageRes());
        }
    }
}
