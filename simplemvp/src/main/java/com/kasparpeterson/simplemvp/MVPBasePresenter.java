package com.kasparpeterson.simplemvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaspar on 01/09/16.
 */
public abstract class MVPBasePresenter<V extends MVPBaseViewOperations, M extends MVPBaseModel>
        implements MVPBasePresenterModelOperations {

    public interface ViewAction<V> {
        void onAction(V view);
    }

    private WeakReference<V> view;
    private M model;

    private List<ViewAction<V>> unCalledViewActions = new ArrayList<>();

    public MVPBasePresenter(V view, M model) {
        this.view = new WeakReference<>(view);
        this.model = model;
        model.setPresenter(this);
    }

    void onConfigurationChanged(V view) {
        this.view = new WeakReference<>(view);
    }

    public void onStart() {
        model.onStart();
        // For overriding

        while (!unCalledViewActions.isEmpty() && getView() != null) {
            ViewAction<V> viewAction = unCalledViewActions.remove(0);
            viewAction.onAction(getView());
        }
    }

    public void onResume() {
        model.onResume();
        // For overriding
    }

    void onDestory(boolean isChangingConfig) {
        view = null;
        if (!isChangingConfig) {
            model.onDestroy();
        }
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        model.onSaveInstanceState(outState);
        // For overriding
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        model.onRestoreInstanceState(savedInstanceState);
        // For overriding
    }

    // For overriding. Call super.onBackPressed() when not handling back button in child Presenter
    // class
    public void onBackPressed() {
        if (getView() != null) {
            getView().handleOnBackPressed();
        }
    }

    @Nullable
    // This should be made private once it is not used from outside anymore
    private V getView() {
        if (view != null) return view.get();
        return null;
    }

    protected void onView(ViewAction<V> viewAction) {
        if (getView() != null) {
            viewAction.onAction(getView());
        } else {
            Log.d("MVPBasePresenter", "onView action failed because getView() returned null!");
            unCalledViewActions.add(viewAction);
        }
    }

    @NonNull protected M getModel() {
        return model;
    }
}
