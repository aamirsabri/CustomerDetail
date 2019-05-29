package com.pgvcl.cms.pgvclcms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TelephonyManager mTelephonyManager;
String mobileNo;
String imei;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        mobileNo = getPhoneNumber();
        showToast("Mobile No " + mobileNo + " imei " + getImei());
    }

    public String getImei(){
        if(!checkPermission()){
            askForPermission(new String[] {Manifest.permission.READ_PHONE_STATE});
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String imei = mTelephonyManager.getImei();

        } else {
            String imei = mTelephonyManager.getDeviceId();

        }
        return imei;
    }

    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
    public String getPhoneNumber(){
        if(!checkPermission()){
            askForPermission(new String[]{Manifest.permission.READ_PHONE_STATE});
        }
        mobileNo = new String(mTelephonyManager.getLine1Number());
        imei = new String(mTelephonyManager.getDeviceId());
        return mobileNo;
    }

    public void askForPermission(String[] permissions){
        ActivityCompat.requestPermissions(this,permissions,1000);
    }

    public boolean checkPermission(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(!checkPermission()){
            askForPermission(new String[]{Manifest.permission.READ_PHONE_STATE});
        }else{
            mobileNo = getPhoneNumber();
            showToast("Mobile No " + mobileNo + " imei " + imei);
        }
    }
}
