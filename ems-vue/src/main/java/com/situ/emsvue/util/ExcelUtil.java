package com.situ.emsvue.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtil {
    public static <T> void exportExcel(HttpServletResponse resp, List<T> list, Class<T> clazz, String fileName) {
        exportExcel(resp, list, clazz, fileName, fileName);
    }

    /**
     * 导出excel
     * @param list      导出数据集合
     * @param fileName  文件的名称
     * @return 结果
     */
    public static <T> void exportExcel(HttpServletResponse resp, List<T> list, Class<T> clazz, String fileName, String sheetName ) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            resp.reset();
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            resp.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            ServletOutputStream os = resp.getOutputStream();
            EasyExcel.write(os, clazz)
                    .autoCloseStream(false)
                    // 自动适配
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    // 大数值自动转换 防止失真
                    //.registerConverter(new ExcelBigNumberConvert())
                    .sheet(sheetName).doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }
}
