package com.kasparpeterson.mctdddemo.signup;

import android.support.annotation.NonNull;

import com.kasparpeterson.simplemvp.MVPBaseModel;
import com.kasparpeterson.simplemvp.MVPBasePresenter;
import com.kasparpeterson.simplemvp.MVPBasePresenterModelOperations;
import com.kasparpeterson.simplemvp.MVPBaseViewOperations;

/**
 * Created by kaspar on 01/04/2017.
 */

public interface SignUpMVP {

    // Presenter -> View
    interface ViewOperations extends MVPBaseViewOperations {
        void showView(@NonNull SignUpViewState viewState);
    }

    // View -> Presenter
    abstract class PresenterViewOperations extends MVPBasePresenter<ViewOperations, ModelOperations> {
        public static final String TAG = PresenterViewOperations.class.getSimpleName();

        public PresenterViewOperations(ViewOperations view, ModelOperations model) {
            super(view, model);
        }

        abstract void onSignUpClicked(String name, String password);
    }

    // Model -> Presenter
    interface PresenterModelOperations extends MVPBasePresenterModelOperations {
    }

    // Presenter -> Model
    abstract class ModelOperations extends MVPBaseModel<PresenterModelOperations> {
    }

}
