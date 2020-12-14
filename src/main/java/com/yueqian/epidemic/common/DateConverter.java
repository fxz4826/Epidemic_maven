package com.yueqian.epidemic.common;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component  //将当前类纳入spring容器的管理
public class DateConverter implements Converter<String, Date> {
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date convert(String s) {
        Date date=null;
        if(s!=null && s.length()>0){
            try {
                date = sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
}
