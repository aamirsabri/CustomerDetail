package com.pgvcl.cms.pgvclcms;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
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

import java.util.ArrayList;

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


Menu navigationMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
        populateAllSpinner();
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void populateAllSpinner(){
        spCircle = (Spinner) findViewById(R.id.spCircle);
        adapterCircle = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,coNames);
        adapterCircle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCircle.setAdapter(adapterCircle);
        spCircle.setPrompt("Select Circle");

        spCircle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(isFirstCircleEvent){
//                    isFirstCircleEvent = false;
//                    isFirstDivisionEvent = true;
//
//                }
                if(position == 0 ){
                    spDivision.setEnabled(false);
                    spSdn.setEnabled(false);
                    return;
                }

                isFirstDivisionEvent = true;
                adapterDivision = new ArrayAdapter<>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item,divNames);
                adapterDivision.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spDivision.setEnabled(true);
                spDivision.setAdapter(adapterDivision);

                spSdn.setEnabled(false);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDivision = (Spinner) findViewById(R.id.spDivision);
        spDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spSdn.setEnabled(true);
                if(position ==0 ){
                    spSdn.setEnabled(false);
                    return;
                }
//                if(isFirstDivisionEvent){
//                    isFirstDivisionEvent = false;
//                    return;
//                }
                adapterSdn = new ArrayAdapter<>(HomeActivity.this,android.R.layout.simple_spinner_dropdown_item,sdnNames);
                adapterSdn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSdn.setAdapter(adapterSdn);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spSdn = (Spinner) findViewById(R.id.spSdn);
        //  spSdn.setOnItemSelectedListener(this);
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
        coNames = getResources().getStringArray(R.array.circle_list);
        //divCodes = new ArrayList<>();
        divNames = getResources().getStringArray(R.array.division_list);
        //sdnCodes = new ArrayList<>();
        sdnNames = getResources().getStringArray(R.array.sdn_list);

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
