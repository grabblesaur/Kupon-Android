package ru.bizapp.kuponandroid.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import ru.bizapp.kuponandroid.R;

/**
 * Created by Majo on 17.11.2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    public void setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            int backCount = getSupportFragmentManager().getBackStackEntryCount();
            if (backCount == 0) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(v -> onBackPressed());
            }
        });
    }

    public void hideKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    public void replaceFragment(int idContainer, Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(idContainer, fragment, fragment.getClass().getName().toString()).commit();
    }

    public void addFragment(int idContainer, Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(idContainer, fragment, fragment.getClass().getName().toString()).addToBackStack(fragment.getClass().getName()).commit();
    }


    public void clearBackstack(){
        int stackSize = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < stackSize; i++) {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void showDialogProgress(){
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.setContentView(R.layout.dialog_loading);
    }

    public void dismissDialogProgress(){
        if (dialog!=null)
            dialog.dismiss();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
