package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Excel Utility for Reading and Writing Test Data
 * Supports:
 * - Reading data from Excel by testcase name and column name
 * - Writing data to Excel (results, status, pass/fail)
 * - Color coding (Green for PASS, Red for FAIL)
 * - HashMap-based data management
 */
public class ExcelUtils {

    private static final String RESULTS_FILE = "src/test/resources/test_results.xlsx";
    private static final int GREEN_COLOR = 0x92D050; // Green color code
    private static final int RED_COLOR = 0xFF0000;   // Red color code

    /**
     * Read data from Excel file
     * Returns a HashMap with all test data
     *
     * @param filePath Path to Excel file
     * @param sheetName Name of sheet to read from
     * @return HashMap with test data (TestcaseName -> {ColumnName -> Value})
     */
    public static Map<String, Map<String, String>> readExcelData(String filePath, String sheetName) {
        Map<String, Map<String, String>> testData = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                TestLogger.error("Sheet '" + sheetName + "' not found in file: " + filePath);
                return testData;
            }

            // Get header row (column names)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                TestLogger.warning("No header row found in sheet: " + sheetName);
                return testData;
            }

            // Read all rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                String testcaseName = null;

                // Read all cells in row
                for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
                    Cell headerCell = headerRow.getCell(cellIndex);
                    Cell dataCell = row.getCell(cellIndex);

                    if (headerCell == null) continue;

                    String columnName = headerCell.getStringCellValue();
                    String cellValue = getCellValue(dataCell);

                    // First column is testcase name
                    if (cellIndex == 0) {
                        testcaseName = cellValue;
                    }

                    rowData.put(columnName, cellValue);
                }

                if (testcaseName != null && !testcaseName.isEmpty()) {
                    testData.put(testcaseName, rowData);
                }
            }

            TestLogger.info("Read " + testData.size() + " test cases from Excel: " + filePath);

        } catch (FileNotFoundException e) {
            TestLogger.warning("Excel file not found: " + filePath + ". Creating new file.");
        } catch (IOException e) {
            TestLogger.error("Error reading Excel file: " + e.getMessage());
        }

        return testData;
    }

    /**
     * Get single cell value from Excel
     *
     * @param filePath Path to Excel file
     * @param sheetName Sheet name
     * @param testcaseName Name of test case (row header)
     * @param columnName Column name to read
     * @return Cell value as String
     */
    public static String readCellValue(String filePath, String sheetName, String testcaseName, String columnName) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                TestLogger.error("Sheet not found: " + sheetName);
                return null;
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                TestLogger.error("Header row not found");
                return null;
            }

            // Find column index
            int columnIndex = -1;
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null && cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = i;
                    break;
                }
            }

            if (columnIndex == -1) {
                TestLogger.warning("Column not found: " + columnName);
                return null;
            }

            // Find row with testcase name
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                Cell firstCell = row.getCell(0);
                if (firstCell != null && firstCell.getStringCellValue().equalsIgnoreCase(testcaseName)) {
                    Cell dataCell = row.getCell(columnIndex);
                    String value = getCellValue(dataCell);
                    TestLogger.info("Read [" + testcaseName + "][" + columnName + "] = " + value);
                    return value;
                }
            }

            TestLogger.warning("Test case not found: " + testcaseName);

        } catch (IOException e) {
            TestLogger.error("Error reading Excel: " + e.getMessage());
        }

        return null;
    }

    /**
     * Write test result to Excel
     * Creates result file if not exists
     *
     * @param testcaseName Name of test case
     * @param columnName Column name to write to
     * @param value Value to write
     * @param status Test status (PASS or FAIL)
     */
    public static void writeTestResult(String testcaseName, String columnName, String value, String status) {
        writeTestResult(RESULTS_FILE, "TestResults", testcaseName, columnName, value, status);
    }

    /**
     * Write test result to Excel with full details
     *
     * @param filePath Path to Excel file
     * @param sheetName Sheet name
     * @param testcaseName Test case name
     * @param columnName Column name
     * @param value Value to write
     * @param status Test status (PASS or FAIL)
     */
    public static void writeTestResult(String filePath, String sheetName, String testcaseName,
                                      String columnName, String value, String status) {
        Workbook workbook = null;
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try {
            // Load existing workbook or create new
            if (fileExists) {
                FileInputStream fileInputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(fileInputStream);
                fileInputStream.close();
            } else {
                workbook = new XSSFWorkbook();
            }

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            // Create header row if not exists
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                headerRow = sheet.createRow(0);
                // Add default columns
                String[] defaultColumns = {"TestCase", "Data Used", "Status", "Timestamp"};
                for (int i = 0; i < defaultColumns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(defaultColumns[i]);
                    cell.setCellStyle(createHeaderStyle(workbook));
                }
            }

            // Find or create column
            int columnIndex = getColumnIndex(headerRow, columnName);
            if (columnIndex == -1) {
                columnIndex = (int) headerRow.getLastCellNum();
                Cell headerCell = headerRow.createCell(columnIndex);
                headerCell.setCellValue(columnName);
                headerCell.setCellStyle(createHeaderStyle(workbook));
            }

            // Find or create row for testcase
            int rowIndex = getRowIndex(sheet, testcaseName);
            Row dataRow;
            if (rowIndex == -1) {
                rowIndex = sheet.getLastRowNum() + 1;
                dataRow = sheet.createRow(rowIndex);
                Cell testcaseCell = dataRow.createCell(0);
                testcaseCell.setCellValue(testcaseName);
            } else {
                dataRow = sheet.getRow(rowIndex);
            }

            // Write value
            Cell dataCell = dataRow.createCell(columnIndex);
            dataCell.setCellValue(value);

            // Apply color based on status
            CellStyle cellStyle = createStatusStyle(workbook, status);
            dataCell.setCellStyle(cellStyle);

            // Write status in Status column (index 2)
            Cell statusCell = dataRow.createCell(2);
            statusCell.setCellValue(status);
            statusCell.setCellStyle(createStatusStyle(workbook, status));

            // Write timestamp (index 3)
            Cell timestampCell = dataRow.createCell(3);
            timestampCell.setCellValue(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));

            // Auto-adjust column widths
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            TestLogger.info("Write [" + testcaseName + "][" + columnName + "] = " + value + " | Status: " + status);

        } catch (IOException e) {
            TestLogger.error("Error writing to Excel: " + e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                TestLogger.error("Error closing workbook: " + e.getMessage());
            }
        }
    }

    /**
     * Write multiple test results at once
     *
     * @param testcaseName Test case name
     * @param testDataMap Map of column names to values
     * @param status Test status (PASS or FAIL)
     */
    public static void writeMultipleResults(String testcaseName, Map<String, String> testDataMap, String status) {
        for (Map.Entry<String, String> entry : testDataMap.entrySet()) {
            writeTestResult(testcaseName, entry.getKey(), entry.getValue(), status);
        }
    }

    /**
     * Update test status (useful at end of test)
     *
     * @param testcaseName Test case name
     * @param status PASS or FAIL
     */
    public static void updateTestStatus(String testcaseName, String status) {
        writeTestResult(testcaseName, "Status", status, status);
    }

    /**
     * Get cell value as String
     * Handles different cell types
     */
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    /**
     * Create header cell style
     */
    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    /**
     * Create status cell style (Green for PASS, Red for FAIL)
     */
    private static CellStyle createStatusStyle(Workbook workbook, String status) {
        CellStyle style = workbook.createCellStyle();

        if ("PASS".equalsIgnoreCase(status)) {
            // Green background
            style.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 146, (byte) 208, (byte) 80}));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Dark green font
            Font font = workbook.createFont();
            font.setBold(true);
            font.setColor(IndexedColors.DARK_GREEN.getIndex());
            style.setFont(font);
        } else if ("FAIL".equalsIgnoreCase(status)) {
            // Red background
            style.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 255, (byte) 0, (byte) 0}));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // White font
            Font font = workbook.createFont();
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBold(true);
            style.setFont(font);
        }

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        return style;
    }

    /**
     * Get column index by name
     */
    private static int getColumnIndex(Row headerRow, String columnName) {
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            if (cell != null && cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get row index by testcase name
     */
    private static int getRowIndex(Sheet sheet, String testcaseName) {
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) continue;

            Cell firstCell = row.getCell(0);
            if (firstCell != null && firstCell.getStringCellValue().equalsIgnoreCase(testcaseName)) {
                return rowIndex;
            }
        }
        return -1;
    }

    /**
     * Clear all results (for fresh start)
     */
    public static void clearResults() {
        File file = new File(RESULTS_FILE);
        if (file.exists()) {
            file.delete();
            TestLogger.info("Results file cleared");
        }
    }

    /**
     * Get results file path
     */
    public static String getResultsFilePath() {
        return RESULTS_FILE;
    }
}



