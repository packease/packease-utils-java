package com.aboutcoder.packease.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 9:41 PM<br />
 * @description <br />
 */
public class PESheetReaderTest {

    /**
     * This test case will validate functions below:
     *
     * @see PESheetReader#readSingleSheetViaFilePath(String, int, List, PEISheetReaderCallback)
     * @see PESheetReader#readSingleSheetViaInputStream(String, InputStream, int, List, PEISheetReaderCallback)
     * @see PESheetReader#readSingleSheetViaWorkbook(Workbook, int, List, PEISheetReaderCallback)
     */
    @Test
    public void testReadSingleSheetViaFilePath() {
        String localFilePath = PESheetWriter.class.getResource("/excel/Workbook1.xlsx").getPath();
        List<DataRow> dataList = new ArrayList<DataRow>();
        try {
            PESheetReader.readSingleSheetViaFilePath(localFilePath, 0, dataList, new PEISheetReaderCallback<DataRow>() {
                @Override
                public void process(Row row, List<DataRow> dataList) {
                    DataRow rowData = new DataRow();

                    for (Cell cell : row) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                String stringCellValue = cell.getStringCellValue();
                                rowData.setCol1(stringCellValue);
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                int digitCellValue = (int) cell.getNumericCellValue();
                                String digitStr = String.valueOf(digitCellValue);
                                rowData.setCol1(digitStr);
                                break;
                            default:
                                break;
                        }
                    }

                    // This is the other way to query each of columns.
                    //
                    // Iterator<Cell> cellIterator = row.cellIterator();
                    // while (cellIterator.hasNext()) {
                    //     Cell cell = cellIterator.next();
                    //     // ... Do something and render what you want here.
                    // }

                    dataList.add(rowData);
                }
            });
            Assert.assertTrue(dataList.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * An excel row entity
     */
    private class DataRow {
        private String col1;
        private String col2;

        /**
         * Get col1 <br>
         *
         * @return Returns the col1. <br>
         */
        public String getCol1() {
            return col1;
        }

        /**
         * Set col1 <br>
         *
         * @param col1 The col1 to set. <br>
         */
        public void setCol1(String col1) {
            this.col1 = col1;
        }

        /**
         * Get col2 <br>
         *
         * @return Returns the col2. <br>
         */
        public String getCol2() {
            return col2;
        }

        /**
         * Set col2 <br>
         *
         * @param col2 The col2 to set. <br>
         */
        public void setCol2(String col2) {
            this.col2 = col2;
        }
    }
}
