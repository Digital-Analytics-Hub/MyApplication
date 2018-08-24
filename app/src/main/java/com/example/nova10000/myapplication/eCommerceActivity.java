package com.example.nova10000.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.w3c.dom.Text;

import java.util.ArrayList;

/*
 *
 *  subcj. ecommerce activity
 *  maker. ray na
 *  group. da-hub
 *  dates. 2018. 08. 23.
 *
 */

public class eCommerceActivity extends AppCompatActivity {

    private TextView mProduct_name;

    // 1. Firebase Analytics Member Variables
    private FirebaseAnalytics mFirebaseAnalytics;
    private Bundle mParam;
    private Bundle mEcommerceBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ecommerce);

        mProduct_name = (TextView)findViewById(R.id.product_promotion_name);

        // 2. Constructor Set up &  Prepare Ecommerce
        mParam = new Bundle();
        mEcommerceBundle = new Bundle();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    // 3. Click Event Listener & previous Product Purchase, Add to Cart check to value
    public void onCommerce(View v) {
        switch (v.getId()) {
            case R.id.checkout :
                Intent i = new Intent(eCommerceActivity.this, PopupActivity.class);
                startActivity(i);
                break;

            case R.id.addcart : // 4. Add to Cart Event
                // 5. Define Product Information
                mParam.putString( FirebaseAnalytics.Param.ITEM_NAME, "Cart" + mProduct_name.getText().toString()); // Custom Product Name
                mParam.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Software/Analytics Tool"); // Product Category
                mParam.putString( FirebaseAnalytics.Param.ITEM_BRAND, "Google"); // Product Brand
                mParam.putDouble( FirebaseAnalytics.Param.PRICE, 100000 ); // Product Price
                mParam.putString( FirebaseAnalytics.Param.CURRENCY, "KRW" ); // Product Currency

                // 6. Category names shown in Analytics
                mEcommerceBundle.putBundle( "HotProduct", mParam );

                // 7. [Important] Record Log Event to your Analytics
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, mEcommerceBundle);

                Toast.makeText(getApplicationContext(), "장바구니에 담겼습니다!", Toast.LENGTH_LONG).show();
                break;
        }
    }
}