package com.akl.ahmed.videochatakl.activities;

import android.os.Bundle;

import com.akl.ahmed.videochatakl.R;
import com.akl.ahmed.videochatakl.services.CallService;
import com.quickblox.akl.core.ui.activity.CoreSplashActivity;
import com.quickblox.akl.core.utils.SharedPrefsHelper;
import com.quickblox.users.model.QBUser;

public class SplashActivity extends CoreSplashActivity {

    private SharedPrefsHelper sharedPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefsHelper = SharedPrefsHelper.getInstance();

        if (sharedPrefsHelper.hasQbUser()) {
            startLoginService(sharedPrefsHelper.getQbUser());
            startOpponentsActivity();
            return;
        }

        if (checkConfigsWithSnackebarError()) {
            proceedToTheNextActivityWithDelay();
        }
    }

    @Override
    protected String getAppName() {
        return getString(R.string.splash_app_title);
    }

    @Override
    protected void proceedToTheNextActivity() {
        LoginActivity.start(this);
        finish();
    }

    protected void startLoginService(QBUser qbUser) {
        CallService.start(this, qbUser);
    }

    private void startOpponentsActivity() {
        OpponentsActivity.start(SplashActivity.this, false);
        finish();
    }
}