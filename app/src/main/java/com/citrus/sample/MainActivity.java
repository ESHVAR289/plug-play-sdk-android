package com.citrus.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.citrus.sdk.Constants;
import com.citrus.sdk.TransactionResponse;
import com.citrus.sdk.ui.fragments.ResultFragment;
import com.citrus.sdk.ui.utils.CitrusFlowManager;
import com.citrus.sdk.ui.utils.ResultModel;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends BaseActivity {

    AppEnvironment appEnvironment;
    public static final String returnUrlLoadMoney = "https://salty-plateau-1529.herokuapp" +
            ".com/redirectUrlLoadCash.php";
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appEnvironment = AppEnvironment.SANDBOX;

        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
        setupCitrusConfigs();
        findViewById(R.id.quick_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitrusFlowManager.startShoppingFlow(MainActivity.this,
                        "developercitrus@mailinator.com", "9769507476", "5", false);
            }
        });
        findViewById(R.id.custom_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomDetailsActivity.class));
            }
        });
        findViewById(R.id.wallet_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitrusFlowManager.startWalletFlow(MainActivity.this, "developercitrus@mailinator" +
                        ".com", "9769507476");
            }
        });
        findViewById(R.id.pink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitrusFlowManager.startShoppingFlowStyle(MainActivity.this,
                        "developercitrus@mailinator.com", "9769507476", "5", R.style.AppTheme_pink, true);
                Toast.makeText(MainActivity.this, "Result Screen will Override", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitrusFlowManager.startShoppingFlowStyle(MainActivity.this,
                        "developercitrus@mailinator.com", "9769507476", "5", R.style.AppTheme_blue, false);
            }
        });
        findViewById(R.id.green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitrusFlowManager.startShoppingFlowStyle(MainActivity.this,
                        "developercitrus@mailinator.com", "9769507476", "5", R.style
                                .AppTheme_Green, false);
            }
        });
        findViewById(R.id.purple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitrusFlowManager.startShoppingFlowStyle(MainActivity.this,
                        "developercitrus@mailinator.com", "9769507476", "5", R.style
                                .AppTheme_purple, false);
            }
        });
        findViewById(R.id.logout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitrusFlowManager.logoutUser(MainActivity.this);
            }
        });
    }

    private void setupCitrusConfigs() {
        CitrusFlowManager.initCitrusConfig(appEnvironment.getSignUpId(),
                appEnvironment.getSignUpSecret(), appEnvironment.getSignInId(),
                appEnvironment.getSignInSecret(), ContextCompat.getColor(this, R.color.white),
                MainActivity.this, appEnvironment.getEnvironment(), appEnvironment.getVanity(), appEnvironment.getBillUrl(),
                returnUrlLoadMoney,true);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MainActivity", "request code " + requestCode + " resultcode " + resultCode);
        if (requestCode == Constants.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
                null) {
            // You will get data here if transaction flow is started through pay options other than wallet
            TransactionResponse transactionResponse = data.getParcelableExtra(Constants
                    .INTENT_EXTRA_TRANSACTION_RESPONSE);
            // You will get data here if transaction flow is started through wallet
            ResultModel resultModel = data.getParcelableExtra(ResultFragment.ARG_RESULT);
            // Check which object is non-null
            if (transactionResponse != null && transactionResponse.getJsonResponse() != null) {
                // Decide what to do with this data
                Log.d(TAG, "transaction response" + transactionResponse.getJsonResponse());
            } else if (resultModel != null && resultModel.getTransactionResponse() != null) {
                // Decide what to do with this data
                Log.d(TAG, "result response" + resultModel.getTransactionResponse().getTransactionId());
                Log.d(TAG, "result response Json" + resultModel.getTransactionResponse().getJsonResponse());
            } else {
                Log.d(TAG, "Both objects are null!");
            }
        }
    }

}
