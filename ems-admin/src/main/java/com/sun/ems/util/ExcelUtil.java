package com.sun.ems.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    public static <T> void exportExcel(HttpServletResponse resp, List<T> list,Class<T> clazz, String fileName, String sheetName ) {
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

    /**
     *  easyExcel处理上传的文件
     * @param request 请求实体
     * @param analysisEventListener  上传处理监听
     * @param clazz  上传处理excel类
     * @return
     * @throws Exception
     */
    public static void importExcel(HttpServletRequest request, AnalysisEventListener analysisEventListener, Class clazz) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(10 * 1024 * 1024);
        String tempPath = request.getServletContext().getRealPath("temp");
        factory.setRepository(new File(tempPath));
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        servletFileUpload.setHeaderEncoding("UTF-8");
        List<FileItem> fileItems = null;
        try {
            fileItems = servletFileUpload.parseRequest(request);
            if (fileItems != null && fileItems.size() > 0) {
                FileItem fileItem = fileItems.get(0);
                if (!fileItem.isFormField()) {
                    String name = fileItem.getName();
                    InputStream inputStream = fileItem.getInputStream();
                    EasyExcel.read(inputStream, clazz, analysisEventListener).sheet().doRead();
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
