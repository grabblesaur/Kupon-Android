package ru.bizapp.kuponandroid.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Majo on 17.11.2016.
 */
public abstract class BaseFragment extends Fragment {

    protected abstract void initViews();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    public void hideKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager)  getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    public void setTitle(String title){
        ((BaseActivity)getActivity()).getSupportActionBar().setTitle(title);
    }

    public void replaceFragment(int idContainer, Fragment fragment){
        getFragmentManager().beginTransaction().replace(idContainer, fragment, fragment.getClass().getName().toString()).commit();
    }

    public void replaceFragmentWithBackstack(int idContainer, Fragment fragment){
        getFragmentManager().beginTransaction()
                .replace(idContainer, fragment, fragment.getClass().getName().toString())
                .addToBackStack(fragment.getClass().getName().toString())
                .commit();
    }

    public void showDialogProgress(){
        if (getActivity()!=null)
            ((BaseActivity)getActivity()).showDialogProgress();
    }

    public void dismissDialogProgress(){
        if (getActivity()!=null)
            ((BaseActivity)getActivity()).dismissDialogProgress();

    }

}
