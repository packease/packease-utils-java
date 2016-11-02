package com.aboutcoder.packease.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 10:30 PM<br />
 * @description <br />
 */
public class PESheetWriter {

    /**
     * Write single sheet and output as Workbook
     *
     * @param dataList
     * @param fileFormat
     * @param callback
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Workbook writeSingleSheetToWorkbook(List<T> dataList, PEFileFormat fileFormat,
                                                          PEISheetWriterCallback callback) throws Exception {
        Workbook workbook = null;
        if (fileFormat.getValue().equals(PEFileFormat.XLSX.getValue())) {
            workbook = new XSSFWorkbook();
        } else if (fileFormat.getValue().equals(PEFileFormat.XLS.getValue())) {
            workbook = new HSSFWorkbook();
        } else {
            throw new Exception("invalid file name, should be xls or xlsx");
        }

        Sheet sheet = workbook.createSheet(callback.getSheetName());

        Iterator<T> iterator = dataList.iterator();
        int rowIndex = 0;
        while (iterator.hasNext()) {
            Row row = sheet.createRow(rowIndex++);
            // Process and collect data of rows.
            callback.process(rowIndex, row, iterator.next());
        }
        return workbook;
    }

    /**
     * Write single sheet and output as FileOutputStream
     *
     * @param filePath
     * @param dataList
     * @param fileFormat
     * @param callback
     * @param <T>
     * @throws Exception
     */
    public static <T> void writeSingleSheetToFileOutputStream(String filePath, List<T> dataList, PEFileFormat fileFormat,
                                                              PEISheetWriterCallback callback) throws Exception {
        Workbook workbook = writeSingleSheetToWorkbook(dataList, fileFormat, callback);
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    /**
     * Write workbook to HttpServletResponse
     *
     * @param fileNameWithoutFormat
     * @param fileFormat
     * @param book
     * @param response
     * @throws IOException
     */
    private static void writeSingleSheetToHttpServletResponse(String fileNameWithoutFormat, PEFileFormat fileFormat,
                                                              Workbook book, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode(fileNameWithoutFormat + "." + fileFormat.getValue(), "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        book.write(response.getOutputStream());
    }
}
