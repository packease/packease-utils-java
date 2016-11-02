package com.aboutcoder.packease.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/9/16 11:41 PM<br />
 * @description <br />
 */
public class PESheetWriterTest {

    /**
     * This test case will validate functions below:
     *
     * @see PESheetWriter#writeSingleSheetToFileOutputStream(String, List, PEFileFormat, PEISheetWriterCallback)
     * @see PESheetWriter#writeSingleSheetToWorkbook(List, PEFileFormat, PEISheetWriterCallback)
     */
    @Test
    public void testWriteSingleSheetToFileOutputStream() {
        try {
            String targetWritableFileLocalPath = PESheetWriterTest.class.getClassLoader()
                    .getResource("excel/Workbook1.xlsx").getPath();
            Assert.assertNotNull(targetWritableFileLocalPath);

            // Prepare dataList for writing.
            List<DataRow> dataRowList = new ArrayList<DataRow>(){{
                add(new DataRow("HEADER-NEW-1", "HEADER-NEW-2"));
                add(new DataRow("112", "222"));
                add(new DataRow("113", "223"));
                add(new DataRow("114", "224"));
                add(new DataRow("115", "225"));
                add(new DataRow("116", "226"));
            }};

            PESheetWriter.writeSingleSheetToFileOutputStream(targetWritableFileLocalPath, dataRowList,
                    PEFileFormat.XLSX, new PEISheetWriterCallback<DataRow>() {
                @Override
                public String getSheetName() {
                    return "SheetNo-01";
                }

                @Override
                public void process(int rowIndex, Row row, DataRow dataItem) {
                    // Do a special contents' format for the first row.
                    if (rowIndex == 1) {
                        Cell cell0 = row.createCell(0);
                        Cell cell1 = row.createCell(1);
                        cell0.setCellValue(dataItem.getCol1() + "-SPECIAL");
                        cell1.setCellValue(dataItem.getCol2() + "-SPECIAL");
                        return;
                    }

                    // Render the others.
                    Cell cell0 = row.createCell(0);
                    Cell cell1 = row.createCell(1);
                    cell0.setCellValue(dataItem.getCol1());
                    cell1.setCellValue(dataItem.getCol2());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * An excel row entity
     */
    private class DataRow {

        /**
         * Constructor for DataRow.
         * To take an easy way to initial DataRow Object.
         *
         * @param col1
         * @param col2
         */
        public DataRow(String col1, String col2) {
            this.col1 = col1;
            this.col2 = col2;
        }

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
