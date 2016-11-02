package com.aboutcoder.packease.utils.excel;

import com.aboutcoder.packease.utils.io.PEInputStreamUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 9:40 PM<br />
 * @description <br />
 * @see PEInputStreamUtils#getHttpResourceAsInputStream(String, int, int)
 */
public class PESheetReader {

    /**
     * connectTimeOut of downloading job.
     */
    private static int connectTimeOut = 2000;

    /**
     * readTimeOut of downloading job.
     */
    private static int readTimeOut = 5000;

    /**
     * Get connectTimeOut <br>
     *
     * @return Returns the connectTimeOut. <br>
     */
    public static int getConnectTimeOut() {
        return connectTimeOut;
    }

    /**
     * Set connectTimeOut <br>
     *
     * @param connectTimeOut The connectTimeOut to set. <br>
     */
    public static void setConnectTimeOut(int connectTimeOut) {
        PESheetReader.connectTimeOut = connectTimeOut;
    }

    /**
     * Get readTimeOut <br>
     *
     * @return Returns the readTimeOut. <br>
     */
    public static int getReadTimeOut() {
        return readTimeOut;
    }

    /**
     * Set readTimeOut <br>
     *
     * @param readTimeOut The readTimeOut to set. <br>
     */
    public static void setReadTimeOut(int readTimeOut) {
        PESheetReader.readTimeOut = readTimeOut;
    }

    /**
     * Read single sheet of a workbook via http URL
     *
     * @param httpUrl
     * @param sheetNo
     * @param dataList
     * @param callback
     * @throws Exception
     */
    public static <T> void readSingleSheetViaHttpURL(String httpUrl, int sheetNo, List<T> dataList, PEISheetReaderCallback callback) throws Exception {
        InputStream inputStream = PEInputStreamUtils.getHttpResourceAsInputStream(httpUrl, connectTimeOut, readTimeOut);
        if (null == inputStream) {
            return ;
        }
        readSingleSheetViaInputStream(httpUrl, inputStream, sheetNo, dataList, callback);
    }

    /**
     * Read single sheet of a workbook via file local path
     *
     * @param filePath
     * @param sheetNo
     * @param dataList
     * @param callback
     * @throws Exception
     */
    public static <T> void readSingleSheetViaFilePath(String filePath, int sheetNo, List<T> dataList, PEISheetReaderCallback callback) throws Exception {
        InputStream inputStream = PEInputStreamUtils.getFileContentAsInputStream(filePath);
        if (null == inputStream) {
            return ;
        }
        readSingleSheetViaInputStream(filePath, inputStream, sheetNo, dataList, callback);
    }

    /**
     * Read single sheet of a workbook via path and InputStream
     *
     * @param path
     * @param inputStream
     * @param sheetNo
     * @param dataList
     * @param callback
     * @throws Exception
     */
    public static <T> void readSingleSheetViaInputStream(String path, InputStream inputStream, int sheetNo, List<T> dataList, PEISheetReaderCallback callback) throws Exception {
        Workbook workbook = null;
        if (path.toLowerCase().endsWith(PEFileFormat.XLSX.getValue())) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (path.toLowerCase().endsWith(PEFileFormat.XLS.getValue())) {
            workbook = new HSSFWorkbook(inputStream);
        }

        readSingleSheetViaWorkbook(workbook, sheetNo, dataList, callback);
        inputStream.close();
    }

    /**
     * Read single sheet of a workbook via Workbook
     *
     * @param workbook
     * @param sheetNo
     * @param dataList
     * @param callback
     * @throws Exception
     */
    public static <T> void readSingleSheetViaWorkbook(Workbook workbook, int sheetNo, List<T> dataList, PEISheetReaderCallback callback) throws Exception {
        if (null == workbook) {
            return ;
        }

        Sheet sheet = workbook.getSheetAt(sheetNo);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Process and collect data of rows.
            callback.process(row, dataList);
        }
    }
}
