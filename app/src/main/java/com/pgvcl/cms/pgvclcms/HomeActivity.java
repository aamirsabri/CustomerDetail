package com.pgvcl.cms.pgvclcms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pgvcl.cms.pgvclcms.database.MyDBController;

import java.awt.font.TextAttribute;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,RadioGroup.OnCheckedChangeListener {
Spinner spCircle,spDivision,spSdn;
boolean isFirstCircleEvent = true,isFirstDivisionEvent =true,isFirstSdnEvent= true;
RadioButton rbConsumerno,rbMeterNo;
RadioGroup rgSearchType;
ArrayAdapter<String> adapterCircle,adapterDivision,adapterSdn;
EditText etSearch;
TextView tvSearch,tvCircle,tvDivision,tvSdn;
String[] coNames,coCodes,divNames,divCodes,sdnNames,sdnCodes;
MyDBController myDBController;
String selectedCircle,selectedDivision,selectedSdn;
int preSelectedCircleIndex=-1,preSelectedDivisionIndex=-1,preSelectedSdnIndex=-1;
String TAG="APPPGVCLCMS";
SharedPreferences sharedPreferences;
String SEL_CIR_INDEX = "selectedCircleIndex",SEL_DIV_INDEX = "selectedDivIndex",SEL_SDN_INDEX = "selectedSdnIndex";

Menu navigationMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDBController = new MyDBController(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        sharedPreferences = getSharedPreferences("locations",MODE_PRIVATE);
        //sharedPreferences.edit().clear().commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationMenu = navigationView.getMenu();

        setAdminMenu();

        fillUpOfficeList();
        tvSearch = (TextView) findViewById(R.id.tvSearchType);
        tvDivision = (TextView) findViewById(R.id.tvDivision);
        tvSdn = (TextView) findViewById(R.id.tvSdn);

    }

    public void fillUpOfficeList(){
        fetchOfficeDataFromDB();
        initializeAllSpinner();
        setLastSelectedLocations();
       // populateAllSpinner();

    }

    public void setLastSelectedLocations(){
        if(preSelectedCircleIndex != -1){
            spCircle.setSelection(preSelectedCircleIndex);
            spDivision.setSelection(0);
        }
        if(preSelectedDivisionIndex !=-1){
            spDivision.setSelection(preSelectedDivisionIndex);
            spSdn.setSelection(0);
        }
        if(preSelectedSdnIndex != -1){
            spSdn.setSelection(preSelectedSdnIndex);
        }
    }


    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void initializeAllSpinner(){
        spCircle = (Spinner) findViewById(R.id.spCircle);
        spDivision = (Spinner) findViewById(R.id.spDivision);
        spSdn = (Spinner) findViewById(R.id.spSdn);

        adapterCircle = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        adapterCircle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterCircle.addAll(coNames);

        adapterDivision = new ArrayAdapter<>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item);
        adapterDivision.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(divNames != null){
            adapterDivision.addAll(divNames);
        }

        adapterSdn = new ArrayAdapter<>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item);
        adapterSdn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(sdnNames != null){
            adapterSdn.addAll(sdnNames);
        }

        spCircle.setAdapter(adapterCircle);
        spDivision.setAdapter(adapterDivision);
        spSdn.setAdapter(adapterSdn);


    }


    public void populateAllSpinner(){
        spCircle = (Spinner) findViewById(R.id.spCircle);

        adapterCircle = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        adapterCircle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterCircle.addAll(coNames);
        //spCircle.setPrompt("Select Circle");

        spCircle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(isFirstCircleEvent){
//                    isFirstCircleEvent = false;
//                    isFirstDivisionEvent = true;
//
//                }

                Log.d(TAG," START CIRCLE SELECTION EVENT");
                if(position == 0 ){
                    Log.d(TAG,"PRE CIRCLE POSITION 0 " + preSelectedCircleIndex );
                    spDivision.setEnabled(false);
                    spSdn.setEnabled(false);
                    preSelectedCircleIndex = -1;
                    return;
                }
                Log.d(TAG,"CIRCLE SELECTION EVENT");
                selectedCircle = coNames[position];
                //if(preSelectedCircleIndex <= 0){
                    preSelectedCircleIndex = position;
                    divNames = myDBController.getDivisionListFromCircle(selectedCircle);
                    isFirstDivisionEvent = true;
                    adapterDivision = new ArrayAdapter<>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item,divNames);
                    adapterDivision.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spDivision.setEnabled(true);
                    spDivision.setSelection(0);
                    spDivision.setAdapter(adapterDivision);
                    spSdn.setEnabled(false);
                    Log.d(TAG,"in circle SELECTION EVENT before dondigion divison index" + preSelectedDivisionIndex);
                    if(preSelectedDivisionIndex > 0){
                        Log.d(TAG,"BEFOR DIVISION SELECTION IN CIRCLE EVENT");

                        spDivision.setSelection(preSelectedDivisionIndex);
                        preSelectedDivisionIndex = -1;
                    }


              //  }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDivision = (Spinner) findViewById(R.id.spDivision);
//        if(preSelectedCircleIndex != -1){
//            adapterDivision = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,divNames);
//            adapterDivision.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spDivision.setAdapter(adapterDivision);
//        }
        spDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spSdn.setEnabled(true);
                if(position ==0 ){
                    spSdn.setEnabled(false);
                   // preSelectedDivisionIndex = -1;
                    return;
                }
                selectedDivision = divNames[position];

                //if(preSelectedDivisionIndex<=0){
                    preSelectedDivisionIndex = position;
                    sdnNames = myDBController.getSdnListFromDivision(selectedDivision);
                    adapterSdn = new ArrayAdapter<>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item,sdnNames);
                    adapterSdn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spSdn.setAdapter(adapterSdn);
                    if(preSelectedSdnIndex > 0){
                        spSdn.setSelection(preSelectedSdnIndex);
                    }
               // }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spSdn = (Spinner) findViewById(R.id.spSdn);
//        if(preSelectedDivisionIndex != -1 && preSelectedDivisionIndex != 0){
//            adapterSdn = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,sdnNames);
//            adapterSdn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spSdn.setAdapter(adapterSdn);
//            spSdn.setEnabled(true);
//        }
        spSdn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position ==0){
                   // preSelectedSdnIndex = -1;
                    return;
                }
                preSelectedSdnIndex = position;
                selectedSdn = sdnNames[position];
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(SEL_CIR_INDEX,spCircle.getSelectedItemPosition());
                editor.putInt(SEL_DIV_INDEX,spDivision.getSelectedItemPosition());
                editor.putInt(SEL_SDN_INDEX,position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Log.d(TAG,"BEFOR SET CIRCLE ADAPTER");
        spCircle.setAdapter(adapterCircle);
        Log.d(TAG,"BEFOR SET SELECTION CIRCLE");
        spCircle.setSelection(preSelectedCircleIndex);
        if(preSelectedCircleIndex != -1 && preSelectedDivisionIndex !=1 && preSelectedSdnIndex != -1){
            Log.d(TAG, "BEFORE CIR SEL" );
            spCircle.setSelection(preSelectedCircleIndex);
            //spDivision.setSelection(preSelectedDivisionIndex);
            //spSdn.setSelection(preSelectedSdnIndex);
//            preSelectedCircleIndex = -1;
//            preSelectedDivisionIndex = -1;
//            preSelectedSdnIndex = -1;
            Log.d(TAG,"AFTER SET PRE -1");
        }

        etSearch = (EditText) findViewById(R.id.etSearch);
        rgSearchType = (RadioGroup) findViewById(R.id.rgSearchId);
        rgSearchType.setOnCheckedChangeListener(this);
    }

    public void clearError(){
        etSearch.setError(null);
        tvSearch.setTextColor(Color.parseColor("#413d3d"));

        tvSearch.setError(null);
        etSearch.setError(null);
        tvDivision.setError(null);
        tvSdn.setError(null);
        tvDivision.setTextColor(Color.parseColor("#413d3d"));
        tvSdn.setTextColor(Color.parseColor("#413d3d"));
    }


    public boolean isValidData(){
        clearError();
        boolean isValid = true;
        if(spSdn.getSelectedItem() == null){
            isValid = false;
            tvSdn.setTextColor(Color.parseColor("#ff0000"));
            tvSdn.setError("Select any Search type");
            Toast.makeText(this,"Please Select Sub-division",Toast.LENGTH_LONG).show();
        }
        if(spDivision.getSelectedItem() == null){
            isValid = false;
            tvDivision.setTextColor(Color.parseColor("#ff0000"));
            tvDivision.setError("Select any Search type");
            Toast.makeText(this,"Please Select Sub-division",Toast.LENGTH_LONG).show();
        }
        if(spSdn.getSelectedItem() == null || spSdn.getSelectedItemPosition() == 0){
            isValid = false;
            tvSdn.setTextColor(Color.parseColor("#ff0000"));
            tvSdn.setError("Select any Search type");
            Toast.makeText(this,"Please Select Sub-division",Toast.LENGTH_LONG).show();
        }

        if(rgSearchType.getCheckedRadioButtonId() == -1){
            isValid = false;
            tvSearch.setTextColor(Color.parseColor("#ff0000"));
            tvSearch.setError("Select any Search type");
            Toast.makeText(this,"Please select any Radio Button",Toast.LENGTH_LONG).show();
        }
        if(etSearch.getText() == null){
            isValid = false;
            etSearch.setError("Enter Valid Value");
        }
        return isValid;
    }

    public void fetchOfficeDataFromDB(){
        coNames = myDBController.getAllCircles();
        preSelectedCircleIndex = sharedPreferences.getInt(SEL_CIR_INDEX,-1);
        preSelectedDivisionIndex = sharedPreferences.getInt(SEL_DIV_INDEX,-1);
        preSelectedSdnIndex = sharedPreferences.getInt(SEL_SDN_INDEX,-1);
        //divCodes = new ArrayList<>();
        Log.d(TAG,"PRE CIRLE  " + preSelectedCircleIndex);
        Log.d(TAG,"PRE DIVISION  " + preSelectedDivisionIndex);
        Log.d(TAG,"PRE SDN  " + preSelectedSdnIndex);
        if(preSelectedCircleIndex > 0){
            divNames = myDBController.getDivisionListFromCircle(coNames[preSelectedCircleIndex]);
        }
        if(preSelectedDivisionIndex > 0){
            sdnNames = myDBController.getSdnListFromDivision(divNames[preSelectedDivisionIndex]);
        }
        //sdnCodes = new ArrayList<>();


    }



    public void setAdminMenu(){
        Intent i = getIntent();
        if(i.getExtras()!=null && i.getExtras().containsKey("isAdmin")){
            if(i.getExtras().getBoolean("isAdmin")){
                navigationMenu.add("Manage User");
                navigationMenu.getItem(3).setIcon(ContextCompat.getDrawable(this,R.drawable.manageuser));
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sync) {
            // Handle the camera action
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
        switch (checkedRadioButton.getId()){
            case R.id.rbConsumerNo:{
                etSearch.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            }
            case R.id.rbMeterNo:{
                etSearch.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            }
            case R.id.rbConsumerName:{
                etSearch.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            }
        }
    }

    public void searchConsumer(View view){
        if(isValidData()){
            Intent i = new Intent(HomeActivity.this,ConsumerDetailActivity.class);
            startActivity(i);
        }
    }
}
