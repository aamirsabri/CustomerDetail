package com.pgvcl.cms.pgvclcms.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDBController extends SQLiteOpenHelper {

static final String TABLE_LOCATION = "LOCATIONS",DB_NAME = "pgvclcms";
String DB_DIR,FULL_PATH;
public static final int VERSION = 1;
Context mContext;
SQLiteDatabase myDatabase;


    public MyDBController(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, VERSION);
        mContext = context;
        DB_DIR = "/data/data/" + context.getPackageName() +"/databases/";
        FULL_PATH = DB_DIR + DB_NAME;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!checkDatabase()){
            copyDatabase();
        }
    }

    public boolean checkDatabase(){

        SQLiteDatabase checkDatabase =null;
        try {
            checkDatabase = SQLiteDatabase.openDatabase(FULL_PATH, null, SQLiteDatabase.OPEN_READONLY);
        }catch (Exception e){
            Toast.makeText(mContext,"CheckDatabase:Database could not open",Toast.LENGTH_LONG).show();
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


        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public ArrayList<LocationEntity> getAllCircles(){

        String query = "SELECT * FROM " + TABLE_LOCATION;
        openDatabase();
        Cursor cursor = myDatabase.query(TABLE_LOCATION,null,null,null,null,null,null);
        if(cursor == null){
            Toast.makeText(mContext,"All Circle : No Data in Locations",Toast.LENGTH_LONG).show();
        }else{
            getLocationsFromCursor(cursor);
        }
        return null;
    }

    public ArrayList<LocationEntity> getLocationsFromCursor(Cursor cursor){
        ArrayList<LocationEntity> locations = new ArrayList<>();
        if(cursor != null) {
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

                    locations.add(locationEntity);
                } while (cursor.moveToNext());
                return locations;
            }
            else{
                Toast.makeText(mContext, "ALL LOCATIONS : No Locations found", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }


}
