package com.app.abby.perfectweather.model.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.abby.perfectweather.model.data.City;
import com.app.abby.perfectweather.model.data.Province;
import com.app.abby.perfectweather.util.Util;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abby on 8/29/2017.
 */

public class WeatherDB {

    public WeatherDB(){}

    public static List<Province> loadProvinces(SQLiteDatabase database){
        List<Province>list=new ArrayList<>();
        Cursor cursor=database.query("T_Province", null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                Province province=new Province();
                province.ProSort=cursor.getInt(cursor.getColumnIndex("ProSort"));
                province.ProName = cursor.getString(cursor.getColumnIndex("ProName"));
                list.add(province);
            }while (cursor.moveToNext());
        }

        closeQuietly(cursor);
        return list;
    }

    public static List<City> loadCities(SQLiteDatabase db,int ProID)
    {
        List<City>list=new ArrayList<>();
        Cursor cursor = db.query("T_City", null, "ProID = ?", new String[] { String.valueOf(ProID) }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.CityName = cursor.getString(cursor.getColumnIndex("CityName"));
                city.ProID = ProID;
                city.CitySort = cursor.getInt(cursor.getColumnIndex("CitySort"));
                list.add(city);
            } while (cursor.moveToNext());
        }

       closeQuietly(cursor);
        return list;
    }
    public static List<Integer>getPortNum(SQLiteDatabase db){

        List<Integer> pro=new ArrayList<>();
        Cursor cursor = db.query("T_Province", null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {

                pro.add(cursor.getInt(cursor.getColumnIndex("ProSort")));
            }while (cursor.moveToNext());
        }

        closeQuietly(cursor);
        return pro;

    }



    public  static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
