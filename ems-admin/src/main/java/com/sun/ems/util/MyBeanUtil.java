package com.sun.ems.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class MyBeanUtil {
    public static <T> T copyToBean(HttpServletRequest request, Class<T> clazz) {
        try {
            //注册不抛出异常、Integer默认值为null，否则为0
            boolean throwException=false;
            boolean defaultNull=true;
            int defaultArraySize=0;
            BeanUtilsBean.getInstance().getConvertUtils().register(throwException,  defaultNull, defaultArraySize);
            // （注册日期类型转换器）
            DateConverter converter = new DateConverter(null);
            converter.setPattern(new String("yyyy-MM-dd HH:mm:ss"));
            ConvertUtils.register(converter, Date.class);
            // 创建对象
            T t = clazz.newInstance();
            BeanUtils.populate(t, request.getParameterMap());
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
