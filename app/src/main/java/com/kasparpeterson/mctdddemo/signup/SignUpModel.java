package com.kasparpeterson.mctdddemo.signup;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.kasparpeterson.mctdddemo.api.SignUpResult;

/**
 * Created by kaspar on 01/04/2017.
 */

public class SignUpModel extends SignUpMVP.ModelOperations {

    private final Handler handler;

    public SignUpModel() {
        handler = new Handler();
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    void signUp(@NonNull String name, @NonNull String password) {
        handler.postDelayed(() -> getPresenter().onSignUpSucceeded(new SignUpResult(SignUpResult.Result.SUCESS)), 3000);
    }
}
