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
 *  subcj. view popup
 *  maker. ray na
 *  group. da-hub
 *  dates. 2018. 08. 23.
 *
 */

public class PopupActivity extends Activity {

    // 1. Create Firebase Analytics Member Variables
    private FirebaseAnalytics mFirebase;
    private Bundle mParam;
    private Bundle ecommerceBundle;

    // 2. Product Infomation
    private TextView product_name;
    private TextView product_price;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        // Remove Title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        product_name = (TextView)findViewById(R.id.product_name);
        product_price = (TextView)findViewById(R.id.product_price);

        // Define product with relevant parameters
        mParam = new Bundle();

        mParam.putString( FirebaseAnalytics.Param.ITEM_ID, "001");  // ITEM_ID or ITEM_NAME is required
        mParam.putString( FirebaseAnalytics.Param.ITEM_NAME, product_name.getText().toString());
        mParam.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Software/Analytics Tool");
        //               mParam.putString( FirebaseAnalytics.Param.ITEM_VARIANT, "Blue");
        mParam.putString( FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        mParam.putDouble( FirebaseAnalytics.Param.PRICE, 100000 );
        mParam.putString( FirebaseAnalytics.Param.CURRENCY, "KRW" );

        // Prepare ecommerce bundle
        ecommerceBundle = new Bundle();
        ecommerceBundle.putBundle( "HotProduct", mParam );

        // Log view_item event with ecommerce bundle
        mFirebase = FirebaseAnalytics.getInstance(this);
        mFirebase.logEvent( FirebaseAnalytics.Event.VIEW_ITEM, ecommerceBundle );
    }

    public void onPurchase(View v) {

        switch (v.getId()) {
            case R.id.myPurchase :
                mParam.putString( FirebaseAnalytics.Param.ITEM_NAME, product_name.getText().toString());
                mParam.putString( FirebaseAnalytics.Param.ITEM_CATEGORY, "Software/Analytics Tool");
                mParam.putString( FirebaseAnalytics.Param.ITEM_BRAND, "Google");
                mParam.putDouble( FirebaseAnalytics.Param.PRICE, 100000 );
                mParam.putString( FirebaseAnalytics.Param.CURRENCY, "KRW" );
                mParam.putLong( FirebaseAnalytics.Param.INDEX, 1 );

                ArrayList items = new ArrayList();
                items.add(mParam);

                ecommerceBundle.putParcelableArrayList("HotProduct", items);

                ecommerceBundle.putString( FirebaseAnalytics.Param.TRANSACTION_ID, product_name.getText().toString() );
                ecommerceBundle.putString( FirebaseAnalytics.Param.AFFILIATION, "Super Store" );
                ecommerceBundle.putDouble( FirebaseAnalytics.Param.VALUE, 100000 );
                ecommerceBundle.putDouble( FirebaseAnalytics.Param.TAX, 10000 );
                ecommerceBundle.putDouble( FirebaseAnalytics.Param.SHIPPING, 5 );
                ecommerceBundle.putString( FirebaseAnalytics.Param.CURRENCY, "KRW" );
                ecommerceBundle.putString( FirebaseAnalytics.Param.COUPON, "SUMMER2017" );

                mFirebase.logEvent( FirebaseAnalytics.Event.ECOMMERCE_PURCHASE, ecommerceBundle );

                Toast.makeText(getApplicationContext(), "결제가 완료 되었습니다.", Toast.LENGTH_LONG).show();
                finish();
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
