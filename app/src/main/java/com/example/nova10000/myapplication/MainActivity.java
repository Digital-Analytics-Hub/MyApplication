package com.example.nova10000.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private saveClientID mSaveID;
    private EditText mUserID;

    // 1. Create Firebase Analytics Member Variables
    private FirebaseAnalytics mFirebase;
    private Bundle mParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSaveID = new saveClientID();
        mUserID = (EditText)findViewById(R.id.main_guestNumber);

        // 2. Add Firebase Analytics to your App
        mFirebase = FirebaseAnalytics.getInstance(this);
    }

    public void onClick(View v) {

        if (mUserID.getText().toString().length() == 0) {
            mFirebase.setUserProperty("Clients", "Not Client");
            Toast.makeText(getApplicationContext(), "올바르지 않습니다.", Toast.LENGTH_SHORT).show();
        }

        if (mUserID.getText().toString().equals(mSaveID.getClientID().toString())) {
            mFirebase.setUserProperty("Clients", "Login Succeed");
            Toast.makeText(getApplicationContext(), "로그인 완료", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            i.putExtra("idKey", mUserID.getText().toString());
            startActivity(i);
            finish();

        } else {
            mFirebase.setUserProperty("Clients", "Login Fail");
            Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
