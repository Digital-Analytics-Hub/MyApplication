package com.example.nova10000.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

/*
 *
 *  subcj. login activity
 *  maker. ray na
 *  group. da-hub
 *  dates. 2018. 08. 24.
 *
 */

public class MainActivity extends AppCompatActivity {

    private saveClientID mSaveClientID;
    private EditText mUserID;

    // 1. Firebase Analytics Member Variables
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mSaveClientID = new saveClientID();
        mUserID = (EditText)findViewById(R.id.main_guestNumber);

        // 2. Constructor Set up
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    // 4. Click Event Listener & 'mUserID' check to value
    public void onClick(View v) {
        // UserID is Null ...
        if (mUserID.getText().toString().length() == 0) {
            mFirebaseAnalytics.setUserProperty("Clients", "Not Client"); // Set User Property ...

            Toast.makeText(getApplicationContext(), "It is not correct", Toast.LENGTH_SHORT).show();
        }

        // UserID is not Null ... Login Succeed ...
        if (mUserID.getText().toString().equals(mSaveClientID.getClientID().toString())) {
            mFirebaseAnalytics.setUserProperty("Clients", "Login Succeed");  // Set User Property ...

            Toast.makeText(getApplicationContext(), "Login Succeed", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            i.putExtra("idKey", mUserID.getText().toString());
            startActivity(i);

            finish();

        } else { // UserID is not Null ... Login Fail ...
            mFirebaseAnalytics.setUserProperty("Clients", "Login Fail");  // Set User Property ...
            Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
