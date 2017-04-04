package com.kasparpeterson.mctdddemo.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;

import com.kasparpeterson.mctdddemo.R;
import com.kasparpeterson.mctdddemo.main.MainActivity;
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

    @BindView(R.id.signup_name_text_view) EditText nameEditText;
    @BindView(R.id.signup_password_text_view) EditText passwordEditText;

    @OnClick(R.id.signup_button)
    public void onSignUp() {
        getPresenter().onSignUp(nameEditText.getText().toString(),
                passwordEditText.getText().toString());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void callSetupPresenter() {
        setupPresenter(this, TAG, SignUpMVP.PresenterViewOperations.TAG);
    }

    @Override
    protected SignUpMVP.PresenterViewOperations initialisePresenter() {
        return new SignUpPresenter(this, new SignUpModel());
    }

    @Override
    public void showView(@NonNull SignUpViewState viewState) {
        if (viewState.getNameErrorRes() > 0) {
            nameEditText.setError(getString(viewState.getNameErrorRes()));
        }

        if (viewState.getPasswordErrorRes() > 0) {
            passwordEditText.setError(getString(viewState.getPasswordErrorRes()));
        }
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.getIntent(this));
    }
}
