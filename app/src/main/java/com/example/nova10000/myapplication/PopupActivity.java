package com.example.nova10000.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.w3c.dom.Text;

import java.util.ArrayList;

/*
 *
 *  subcj. product purchase popup activity
 *  maker. ray na
 *  group. da-hub
 *  dates. 2018. 08. 23.
 *
 */

public class PopupActivity extends Activity {

    private TextView mProduct_name;
    private TextView mProduct_price;

    // 1. Create Firebase Analytics Member Variables
    private FirebaseAnalytics mFirebaseAnalytics;
    private Bundle mParam;
    private Bundle mEcommerceBundle;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        mProduct_name = (TextView)findViewById(R.id.product_name);
        mProduct_price = (TextView)findViewById(R.id.product_price);

        // 2. Constructor Set up
        mParam = new Bundle();

        // 3. Define Product Information
        mParam.putString( FirebaseAnalytics.Param.ITEM_NAME, "Cart" + mProduct_name.getText().toString()); // Custom Name
        mParam.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Software/Analytics Tool"); // Product Category
        mParam.putString( FirebaseAnalytics.Param.ITEM_BRAND, "Google"); // Product Brand
        mParam.putDouble( FirebaseAnalytics.Param.PRICE, 100000 ); // Product Price
        mParam.putString( FirebaseAnalytics.Param.CURRENCY, "KRW" ); // Product Currency

        // 4. Prepare Ecommerce
        mEcommerceBundle = new Bundle();
        mEcommerceBundle.putBundle( "HotProduct", mParam );

        // 5. Record View Item Event
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.VIEW_ITEM, mEcommerceBundle );
    }

    // 6. Click Event Listener & Product Purchase
    public void onPurchase(View v) {
        switch (v.getId()) {
            case R.id.myPurchase :
                // 7. Define Product Information
                mParam.putString( FirebaseAnalytics.Param.ITEM_NAME, "Cart" + mProduct_name.getText().toString()); // Custom Name
                mParam.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Software/Analytics Tool"); // Product Category
                mParam.putString( FirebaseAnalytics.Param.ITEM_BRAND, "Google"); // Product Brand
                mParam.putDouble( FirebaseAnalytics.Param.PRICE, 100000 ); // Product Price
                mParam.putString( FirebaseAnalytics.Param.CURRENCY, "KRW" ); // Product Currency
                mParam.putLong( FirebaseAnalytics.Param.INDEX, 1 ); // Product Private Index number

                // 8-1. Multiple Items ...
                ArrayList items = new ArrayList();
                items.add(mParam);

                // 8-2. Single Items ...
                // mEcommerceBundle.putBundle( "HotProduct", mParam );

                mEcommerceBundle.putParcelableArrayList("HotProduct", items);

                // 9. Record Ecommerce Product Information
                mEcommerceBundle.putString( FirebaseAnalytics.Param.TRANSACTION_ID, mProduct_name.getText().toString() ); // Custom Product Name
                mEcommerceBundle.putString( FirebaseAnalytics.Param.AFFILIATION, "Super Store" ); // Arriliation Store Name
                mEcommerceBundle.putDouble( FirebaseAnalytics.Param.VALUE, 100000 ); // Product Price
                mEcommerceBundle.putDouble( FirebaseAnalytics.Param.TAX, 10000 ); // Product Tax
                mEcommerceBundle.putDouble( FirebaseAnalytics.Param.SHIPPING, 1 ); // Shipping Yes(1) / No(0)
                mEcommerceBundle.putString( FirebaseAnalytics.Param.CURRENCY, "KRW" ); // Product currency
                mEcommerceBundle.putString( FirebaseAnalytics.Param.COUPON, "SUMMER2017" ); // Product Coupon

                // 10. [Important] Record Log Event to your Analytics
                mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.ECOMMERCE_PURCHASE, mEcommerceBundle );

                Toast.makeText(getApplicationContext(), "결제가 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
