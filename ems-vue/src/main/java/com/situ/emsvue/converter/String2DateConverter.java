package com.situ.emsvue.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class String2DateConverter implements Converter<String, Date> {
    private static List<SimpleDateFormat> list = new ArrayList<>();

    static {
        list.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        list.add(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        list.add(new SimpleDateFormat("yyyy-MM-dd"));
        list.add(new SimpleDateFormat("yyyy/MM/dd"));
    }

    @Override
    public Date convert(String source) {
        //遍历所有的格式，如果符合就直接parse之后返回，不符合就抛出异常
        for (SimpleDateFormat simpleDateFormat : list) {
            try {
                return simpleDateFormat.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
