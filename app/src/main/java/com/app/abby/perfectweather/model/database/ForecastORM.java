package com.app.abby.perfectweather.model.database;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.lang.annotation.Retention;

/**
 * Created by Abby on 8/18/2017.
 */

public class ForecastORM {

    private String code;
    private String weekText;
    private String dateText;
    private String weatherText;
    private String tempmaxText;
    private String tempminText;

    public ForecastORM(String week,String date,String weather,String max,String min,String codetxt){
        weekText=week;
        dateText=date;
        weatherText=weather;
        tempmaxText=max;
        tempminText=min;
        code=codetxt;
    }

    public String getWeekText(){
        return weekText;
    }

    public String getDateText(){
        return dateText;
    }

    public String getWeatherText(){
        return weatherText;
    }

    public String getTempmaxText(){
        return tempmaxText;
    }

    public String getTempminText(){
        return tempminText;
    }

    public String getCode(){
        return code;
    }
}
