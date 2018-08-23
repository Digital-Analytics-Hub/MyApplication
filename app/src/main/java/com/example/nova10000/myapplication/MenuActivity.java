package com.example.nova10000.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;

/*
 *
 *  subcj. view guideline menu
 *  maker. ray na
 *  group. da-hub
 *  dates. 2018. 08. 23.
 *
 */

public class MenuActivity extends AppCompatActivity {

    private TextView mTxt;

    // 1. Create Firebase Analytics Member Variables
    private FirebaseAnalytics mFirebase;
    private Bundle mParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent i = getIntent();
        String idKey = i.getStringExtra("idKey");

        mTxt = (TextView)findViewById(R.id.menu_getUserId);
        mTxt.setText(idKey.toString() + "님 환영합니다!");

        // 2. Add Firebase Analytics to your App
        mFirebase = FirebaseAnalytics.getInstance(this);

        // 3. Record Log event to your Analytics
        mParam = new Bundle();
        mParam.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "Client Num." + idKey.toString());
        mFirebase.setAnalyticsCollectionEnabled(true);
        mFirebase.setCurrentScreen(this, "Before Activity", "After Activity");
        mFirebase.logEvent(FirebaseAnalytics.Event.SIGN_UP, mParam);
    }

    public void menu_movetoActivity(View v) {

        switch (v.getId()) {
            case R.id.menu_basicSetting :
                // 4. Record User Property : study
                mFirebase.setUserProperty("Clients", "Basic Study for Setting");
                break;

            case R.id.menu_eCommerce :
                // 4. Record User Property : eCommerce
                mFirebase.setUserProperty("Clients", "eCommerce");
                Intent i = new Intent(MenuActivity.this, eCommerceActivity.class);
                startActivity(i);
                break;
        }

    }
}
