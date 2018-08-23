package com.example.nova10000.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.w3c.dom.Text;

/*
 *
 *  subcj. connect ecommerce
 *  maker. ray na
 *  group. da-hub
 *  dates. 2018. 08. 23.
 *
 */

public class eCommerceActivity extends AppCompatActivity {

    // 1. Create Firebase Analytics Member Variables
    private FirebaseAnalytics mFirebase;
    private Bundle mParam;
    private Bundle ecommerceBundle;

    // 2. Product Infomation
    private TextView product_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);

        mParam = new Bundle();
        ecommerceBundle = new Bundle();
        mFirebase = FirebaseAnalytics.getInstance(this);
        product_name = (TextView)findViewById(R.id.product_name);
    }

    public void onCommerce(View v) {

        switch (v.getId()) {
            case R.id.checkout :
                Intent i = new Intent(eCommerceActivity.this, PopupActivity.class);
                startActivity(i);
                break;

            case R.id.addcart :
                // Define product with relevant parameters
                mParam.putString( FirebaseAnalytics.Param.ITEM_ID, "001");
                mParam.putString( FirebaseAnalytics.Param.ITEM_NAME, product_name.getText().toString());
                mParam.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Software/Analytics Tool");
                mParam.putString( FirebaseAnalytics.Param.ITEM_BRAND, "Google");
                mParam.putDouble( FirebaseAnalytics.Param.PRICE, 100000 );
                mParam.putString( FirebaseAnalytics.Param.CURRENCY, "KRW" );

                ecommerceBundle.putBundle( "HotProduct", mParam );
                mFirebase.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, ecommerceBundle);

                Toast.makeText(getApplicationContext(), "장바구니에 담겼습니다!", Toast.LENGTH_LONG).show();
                break;
        }

    }
}