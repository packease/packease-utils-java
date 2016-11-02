PE.utils.excel
=============

> 集成了apache poi框架,对Microsoft的excel表格文件进行解析,实现读取和写入的功能.

* PEFileFormat: 定义本Wrapper所支持的excel文件格式。

* PESheetReader/PEISheetReaderCallback: 定义excel reader,实现对excel内容的读取。

```java
<T> void readSingleSheetViaHttpURL(String httpUrl, int sheetNo, List<T> dataList, PEISheetReaderCallback callback)
<T> void readSingleSheetViaFilePath(String filePath, int sheetNo, List<T> dataList, PEISheetReaderCallback callback)
<T> void readSingleSheetViaInputStream(String path, InputStream inputStream, int sheetNo, List<T> dataList, PEISheetReaderCallback callback)
<T> void readSingleSheetViaWorkbook(Workbook workbook, int sheetNo, List<T> dataList, PEISheetReaderCallback callback)
```

```java
// PESheetReader demos
String localFilePath = PESheetWriter.class.getResource("/excel/Workbook1.xlsx").getPath();
List<DataRow> dataList = new ArrayList<DataRow>();
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
```

* PESheetWriter/PEISheetWriterCallback: 定义excel writer,实现对excel内容的文件写入、HTTP Response输出。

```java
<T> Workbook writeSingleSheetToWorkbook(List<T> dataList, PEFileFormat fileFormat, PEISheetWriterCallback callback)
<T> void writeSingleSheetToFileOutputStream(String filePath, List<T> dataList, PEFileFormat fileFormat, PEISheetWriterCallback callback)
void writeSingleSheetToHttpServletResponse(String fileNameWithoutFormat, PEFileFormat fileFormat, Workbook book, HttpServletResponse response)
```

```java
// PESheetWriter demos
String targetWritableFileLocalPath = PESheetWriterTest.class.getClassLoader()
        .getResource("excel/Workbook1.xlsx").getPath();

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
```