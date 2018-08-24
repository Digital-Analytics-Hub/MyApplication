package com.example.nova10000.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;

/*
 *
 *  subcj. menu activity
 *  maker. ray na
 *  group. da-hub
 *  dates. 2018. 08. 23.
 *
 */

public class MenuActivity extends AppCompatActivity {

    private TextView mText;

    // 1. Create Firebase Analytics Member Variables
    private FirebaseAnalytics mFirebaseAnalytics;
    private Bundle mParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);

        Intent i = getIntent();
        String idKey = i.getStringExtra("idKey");

        mText = (TextView)findViewById(R.id.menu_getUserId);
        mText.setText(idKey.toString() + "님 환영합니다!");

        // 2. Constructor Set up
        mParam = new Bundle();
        mParam.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "Client Num." + idKey.toString()); // UserID value

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // 3. [Important] Record 'Login' Log event to your Analytics
        mFirebaseAnalytics.setCurrentScreen(this, "Before Activity", "After Activity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, mParam);
    }

    // 4. Click Event Listener & Define User Property
    public void menu_movetoActivity(View v) {
        switch (v.getId()) {
            case R.id.menu_basicSetting :
                // 5-1. Record User Property : study
                mFirebaseAnalytics.setUserProperty("Clients", "Basic Study for Setting"); // Set User Property ...
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_eCommerce :
                // 5-2. Record User Property : eCommerce
                mFirebaseAnalytics.setUserProperty("Clients", "eCommerce"); // Set User Property ...
                Intent i = new Intent(MenuActivity.this, eCommerceActivity.class);
                startActivity(i);
                break;
        }
    }
}
