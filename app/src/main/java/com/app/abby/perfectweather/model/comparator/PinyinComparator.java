package com.app.abby.perfectweather.model.comparator;

import com.app.abby.perfectweather.model.data.City;
import com.app.abby.perfectweather.model.data.Province;
import com.github.promeg.pinyinhelper.Pinyin;

import java.util.Comparator;

/**
 * Created by Abby on 8/30/2017.
 */

public class PinyinComparator implements Comparator<Object> {

    @Override
    public int compare(Object s1,Object s2){

        if(s1 instanceof City && s2 instanceof City){

            String pinyin1=Pinyin.toPinyin(((City) s1).CityName,"").substring(0,1).toUpperCase();
            String pinyin2=Pinyin.toPinyin(((City) s2).CityName,"").substring(0,1).toUpperCase();
            return pinyin1.compareTo(pinyin2);

        } else
            if (s1 instanceof Province && s2 instanceof Province){
            String pinyin1=Pinyin.toPinyin(((Province) s1).ProName,"").substring(0,1).toUpperCase();
            String pinyin2=Pinyin.toPinyin(((Province) s2).ProName,"").substring(0,1).toUpperCase();

            return pinyin1.compareTo(pinyin2);
        }

        return 0;
    }


}
