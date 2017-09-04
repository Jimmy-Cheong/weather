package com.app.abby.perfectweather.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.app.abby.perfectweather.R;
import com.app.abby.perfectweather.base.WeatherApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Abby on 8/29/2017.
 */

public class DBManager {

    public static final String DB_NAME="china_city.db";
    public static final String PACKAGE_NAME="com.app.abby.perfectweather";
    public static final String DB_PATH="/data"+ Environment.getDataDirectory()
            .getAbsolutePath()+"/"+PACKAGE_NAME;

    private SQLiteDatabase database;
    private Context context;
    public DBManager(){

        openDatabase();
    }




    private void openDatabase()
    {
        this.database=this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    @Nullable
    private SQLiteDatabase openDatabase(String dbfile)
    {
        try{
            if(!(new File(dbfile).exists()))
            {
                InputStream is= WeatherApplication.getAppContext().getResources().openRawResource(R.raw.china_city);
                FileOutputStream fos=new FileOutputStream(dbfile);
                int BUFFER_SIZE=40000;
                byte[] buffer=new byte[BUFFER_SIZE];
                int count;
                while((count=is.read(buffer))>0){
                    fos.write(buffer,0,count);
                }
                fos.close();
                is.close();

            }

            return SQLiteDatabase.openOrCreateDatabase(dbfile,null);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void closeDatabase() {
        if (this.database != null) {
            this.database.close();
        }
    }

    public SQLiteDatabase getDatabase(){return database;}

}
