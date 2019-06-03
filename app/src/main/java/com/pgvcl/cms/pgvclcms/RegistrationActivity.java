package com.pgvcl.cms.pgvclcms;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.pgvcl.cms.pgvclcms.database.MyDBController;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
EditText etEmpNo,etEmpName,etEmpMobile;
CheckBox cbAdmin;
String TAG="APPPGVCLCMS";
ProgressBar pbRegistration;
String empNo,empName,empMobile;
boolean isAdmin = false;
String imei;
TelephonyManager mTelephonyManager;
int ALREADY_REGISTERED=101,REGISTRATION_SUCCESSFULL=100,USER_ADMIN=201,USER_NORMAL=200;
    String RESPONSE_REGISTRATION_STATUS = "REGISTRATION_STATUS",RESPONSE_USERTYPE = "USER_TYPE",selectedCircle;
    int registrationStatus = 0,userType = USER_NORMAL;
    Spinner spRegCircle;
    String[] circles = new String[]{"SELECT CIRCLE"},divisions = new String[] {"SELECT DIVISION"},sdns= new String[] {"SELECT SUB DIVISION"};
    MyDBController myDBController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if(isActiveUser()){
            Intent intent = new Intent(RegistrationActivity.this,HomeActivity.class);
            intent.putExtra("isAdmin",isAdmin);
            startActivity(intent);
        }
        myDBController = new MyDBController(this);
        //myDBController.deleteDatabase();
        myDBController.createDatabase();
        etEmpNo = (EditText) findViewById(R.id.etEmpNo);
        etEmpName = (EditText) findViewById(R.id.etName);
        etEmpMobile = (EditText) findViewById(R.id.etMobile);
        cbAdmin = (CheckBox) findViewById(R.id.cbAdmin);
        spRegCircle = (Spinner) findViewById(R.id.spRegCircle);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,circles);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRegCircle.setAdapter(arrayAdapter);
        spRegCircle.setOnItemSelectedListener(this);

        pbRegistration = (ProgressBar) findViewById(R.id.pbRegistration);

        //showProgressBar();
        fetchCircleList();
        arrayAdapter  = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,circles);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRegCircle.setAdapter(arrayAdapter);

        Log.d(TAG,"Size of circles fetched " + circles.length);

    }

    public void fetchCircleList(){
        circles = myDBController.getAllCircles();

    }

    public boolean isActiveUser(){
        return false;
    }

    public void registerUser(View view){
        if(isValidData()){
            showProgressBar();
            submitRegistrationData();


        }else{
            Toast.makeText(this,"Please Try again!",Toast.LENGTH_LONG).show();
        }

    }

    public String getImei(){
        if(!checkPermission()){
            askForPermission(new String[] {Manifest.permission.READ_PHONE_STATE});
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            imei = mTelephonyManager.getImei();

        }else {
            imei = mTelephonyManager.getDeviceId();

        }
        return imei;
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
            imei = getImei();

        }
    }


    public void submitRegistrationData(){

                    Toast.makeText(RegistrationActivity.this,"Registration done successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegistrationActivity.this,HomeActivity.class);
                    intent.putExtra("isAdmin",isAdmin);
                    hideProgressBar();
                    startActivity(intent);
    }

    public void hideProgressBar(){
        pbRegistration.setVisibility(View.INVISIBLE);
    }

    public void showProgressBar(){
        pbRegistration.setVisibility(View.VISIBLE);

    }

    public void getFormDetail(){
        empNo = etEmpNo.getText().toString();
        empName = etEmpName.getText().toString();
        empMobile = etEmpMobile.getText().toString();
        isAdmin = cbAdmin.isChecked();
    }

    public void clearError(){
        etEmpName.setError(null);
        etEmpMobile.setError(null);
        etEmpNo.setError(null);
    }

    public boolean isValidData(){
        boolean isValid = true;
        getFormDetail();
        clearError();
        if(empNo == null || empNo.length() <= 3){
            Toast.makeText(this,"Employee Number is not valid",Toast.LENGTH_LONG).show();
            etEmpNo.setError("Not Valid Employee Number");
            isValid = false;
        }
        if(empName == null || empName.length() <= 3){
            Toast.makeText(this,"Employee Name is not valid",Toast.LENGTH_LONG).show();
            etEmpName.setError("Not Valid Employee Name");
            isValid = false;
        }
        if(empMobile == null || empMobile.length() < 10){
            Toast.makeText(this,"Mobile Number is not valid",Toast.LENGTH_LONG).show();
            etEmpMobile.setError("Not Valid Mobile Number");
            isValid = false;
        }
        if(spRegCircle.getSelectedItemPosition() == 0){
            Toast.makeText(this,"Please select Circle",Toast.LENGTH_LONG).show();
            isValid = false;
        }

        return isValid;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
            Toast.makeText(this,"Please select any circle",Toast.LENGTH_LONG).show();
            return;
        }
        selectedCircle = new String(circles[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
