package com.pgvcl.cms.pgvclcms.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MyDBController extends SQLiteOpenHelper {

static final String TABLE_LOCATION = "LOCATIONS",DB_NAME = "pgvclcmsdb";
String DB_DIR,FULL_PATH;
public static final int VERSION = 1;
Context mContext;
    String TAG="APPPGVCLCMS";
SQLiteDatabase myDatabase;
ArrayList<LocationEntity> circleLocationList,divisionLocationList,sdnLocationList;
HashMap<Integer,String> circleMapList,divisionMapList,sdnMapList;
String[] circles,divisions,sdns;

    public MyDBController(Context context) {
        super(context, DB_NAME, null, VERSION);
        mContext = context;
        DB_DIR = "/data/data/" + context.getPackageName() +"/databases/";
        FULL_PATH = DB_DIR + DB_NAME;
    }


    public void createDatabase(){
        boolean checkDB = checkDatabase();
        if(checkDB){

        }else{
            this.getReadableDatabase();
            copyDatabase();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!checkDatabase()){
            copyDatabase();
            Log.d(TAG,"DATABASE CREATED");
        }else{
            Log.d(TAG,"DATABASE ALREADY EXIST");
        }
    }

    public boolean checkDatabase(){

        SQLiteDatabase checkDatabase =null;
        try {
            checkDatabase = SQLiteDatabase.openDatabase(FULL_PATH, null, SQLiteDatabase.OPEN_READONLY);

        }catch (Exception e){
            Log.d(TAG,"EXCEPTION IN CHECKDB");
            //Toast.makeText(mContext,"CheckDatabase:Database could not open",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        if(checkDatabase != null){
            checkDatabase.close();
        }
        return checkDatabase != null ? true : false;

    }

    public void copyDatabase(){
        try {
            InputStream inputStream = mContext.getAssets().open(DB_NAME);
            String outputFileName = FULL_PATH;
            FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[10];
            int length;
            while((length = inputStream.read(buffer))>0){
                fileOutputStream.write(buffer,0,length);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();

            Log.d(TAG,"copy database");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"ERROR WHILE CREATING DATABASE");
        }
    }

    public String[] getDivisionListFromCircle(String cirName){
        String query = "SELECT DISTINCT " + LocationEntity.COL_DVNAME + " FROM " + TABLE_LOCATION  + " WHERE " + LocationEntity.COL_CIR_NAME + " = '" + cirName +"'";
        openDatabase();
        Cursor cursor = myDatabase.rawQuery(query,null);
        if(cursor == null){
            Toast.makeText(mContext,"All Circle : No Data in Locations",Toast.LENGTH_LONG).show();
        }else {
            divisions = new String[cursor.getCount()];
            divisions[0] = "SELECT DIVISION";
            cursor.moveToFirst();
            int i = 1;
            if (cursor.getCount() > 0) {
                do {
                    divisions[i] = cursor.getString(cursor.getColumnIndex(LocationEntity.COL_DVNAME));
                    i++;
                } while (cursor.moveToNext());
                return divisions;
            }
        }
        close();
        return null;
    }

    public String[] getSdnListFromDivision(String divName){
        String query = "SELECT DISTINCT " + LocationEntity.COL_SDNNAME + " FROM " + TABLE_LOCATION  + " WHERE " + LocationEntity.COL_DVNAME + " = '" + divName +"'";
        openDatabase();
        Cursor cursor = myDatabase.rawQuery(query,null);
        if(cursor == null){
            Toast.makeText(mContext,"All Sdn : No Data in Locations",Toast.LENGTH_LONG).show();
        }else {
            sdns = new String[cursor.getCount()];
            sdns[0] = "SELECT SUB DIVISION";
            cursor.moveToFirst();
            int i = 1;
            if (cursor.getCount() > 0) {
                do {
                    sdns[i] = cursor.getString(cursor.getColumnIndex(LocationEntity.COL_SDNNAME));
                    i++;
                } while (cursor.moveToNext());
                return sdns;
            }
        }
        close();
        return null;

    }





    public void openDatabase() throws SQLException {
        myDatabase = SQLiteDatabase.openDatabase(FULL_PATH,null,SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(myDatabase != null){
            myDatabase.close();
        }
        super.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mContext.deleteDatabase(DB_NAME);
        if(!checkDatabase()){
            copyDatabase();
        }
    }

    public void deleteDatabase(){
        mContext.deleteDatabase(DB_NAME);
        Log.d(TAG,"DATABASE DELETED");
    }


    public String[] getAllCircles(){
        String query = "SELECT DISTINCT " + LocationEntity.COL_CIR_NAME + " FROM " + TABLE_LOCATION;
        openDatabase();
        Cursor cursor = myDatabase.rawQuery(query,null);
        if(cursor == null){
            Toast.makeText(mContext,"All Circle : No Data in Locations",Toast.LENGTH_LONG).show();
        }else {
            circles = new String[cursor.getCount()+1];
            circles[0] = "SELECT CIRCLE";

            int i = 1;
            Log.d(TAG,"cursor size " + cursor.getCount());
            if (cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    circles[i] = cursor.getString(cursor.getColumnIndex(LocationEntity.COL_CIR_NAME));
                    Log.d(TAG, "circle name " + circles[i]);
                    i++;
                }
                }
                return circles;
            }

        close();
       return null;
    }



    public ArrayList<LocationEntity> getLocationsFromCursor(Cursor cursor){
        circleLocationList = new ArrayList<>();
        circleMapList = new HashMap<>();

        int i = 0;
        if(cursor != null) {
            circles = new String[cursor.getCount()];
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                do {
                    LocationEntity locationEntity = new LocationEntity();
                    locationEntity.setCirCode(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_CIRCODE)));
                    locationEntity.setCirLocCode(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_CIR_LOCCODE)));
                    locationEntity.setCirName(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_CIR_NAME)));

                    locationEntity.setDvnCode(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_DVNCODE)));
                    locationEntity.setDvnLocCode(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_DVNLOCCODE)));
                    locationEntity.setDvnName(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_DVNAME)));

                    locationEntity.setSdnCode(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_SDNCODE)));
                    locationEntity.setSdnLoc(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_SDNLOC)));
                    locationEntity.setSdnLocCode(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_SDNLOCCODE)));
                    locationEntity.setSdnName(cursor.getString(cursor.getColumnIndex(LocationEntity.COL_SDNNAME)));

                    circleLocationList.add(locationEntity);
                    circleMapList.put(i,locationEntity.getCirCode());
                    circles[i] = locationEntity.getCirName();
                    i++;
                } while (cursor.moveToNext());
                return circleLocationList;
            }
            else{
                Toast.makeText(mContext, "ALL LOCATIONS : No Locations found", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }


}
