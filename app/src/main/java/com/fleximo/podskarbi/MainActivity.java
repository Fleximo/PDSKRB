package com.fleximo.podskarbi;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    private final int REQUEST_PERMISSION_SMS_RECEIVE = 1;

    private static final String SPEND_TAG = "SPEND_TAG";
    private static final String INCOME_TAG = "INCOME_TAG";
    private static final String BALANCE_TAG = "BALANCE_TAG";

    private ImageButton btn_MainActivity_spend = null;
    private ImageButton btn_MainActivity_income = null;
    private ImageButton btn_MainActivity_balance = null;

    DBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Init
        btn_MainActivity_spend = (ImageButton)findViewById(R.id.btn_MainActivity_spend);
        btn_MainActivity_income = (ImageButton)findViewById(R.id.btn_MainActivity_income);
        btn_MainActivity_balance = (ImageButton)findViewById(R.id.btn_MainActivity_balance);
        //Tag set
        btn_MainActivity_spend.setTag(SPEND_TAG);
        btn_MainActivity_income.setTag(INCOME_TAG);
        btn_MainActivity_balance.setTag(BALANCE_TAG);
        //Set listeners
        btn_MainActivity_spend.setOnClickListener(this);
        btn_MainActivity_income.setOnClickListener(this);
        btn_MainActivity_balance.setOnClickListener(this);
        //Create Instance of DB Helper
        dbHelper = DBHelper.getInstance(this);
    }

    @Override
    protected void onResume() {
        showRequestSMSReceivePermission();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btn_MainActivity_spend: {
                intent = new Intent(this, SpendActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_MainActivity_income: {
                break;
            }
            case R.id.btn_MainActivity_balance: {
                break;
            }
        }
    }

    private void showRequestSMSReceivePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
                showExplanation("Permission Needed", "Rationale", Manifest.permission.RECEIVE_SMS, REQUEST_PERMISSION_SMS_RECEIVE);
            }
            else{
                requestPerm(Manifest.permission.RECEIVE_SMS, REQUEST_PERMISSION_SMS_RECEIVE);
            }
        }
    }

    private void requestPerm(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionName}, permissionRequestCode);
    }

    private void showExplanation(String title, String message, final String permission, final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPerm(permission, permissionRequestCode);
                    }
                } );
        builder.create().show();
    }
}
