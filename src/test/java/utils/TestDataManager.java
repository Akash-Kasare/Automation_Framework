package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Test Data Manager
 * Manages test data using HashMap for quick access
 * Supports caching and multiple test data sheets
 */
public class TestDataManager {

    private static Map<String, Map<String, Map<String, String>>> cachedData = new HashMap<>();
    private static final String TEST_DATA_FILE = "src/test/resources/test_data.xlsx";

    /**
     * Load all test data from Excel into memory
     * Uses HashMap for faster access
     * Structure: {SheetName -> {TestcaseName -> {ColumnName -> Value}}}
     */
    public static void loadAllTestData() {
        cachedData = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File(TEST_DATA_FILE));
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            // Load data from all sheets
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                String sheetName = sheet.getSheetName();

                Map<String, Map<String, String>> sheetData = new HashMap<>();

                Row headerRow = sheet.getRow(0);
                if (headerRow == null) continue;

                // Read all test cases
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row == null) continue;

                    Map<String, String> rowData = new HashMap<>();
                    String testcaseName = null;

                    for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
                        Cell headerCell = headerRow.getCell(cellIndex);
                        Cell dataCell = row.getCell(cellIndex);

                        if (headerCell == null) continue;

                        String columnName = headerCell.getStringCellValue();
                        String cellValue = getCellValue(dataCell);

                        if (cellIndex == 0) {
                            testcaseName = cellValue;
                        }

                        rowData.put(columnName, cellValue);
                    }

                    if (testcaseName != null && !testcaseName.isEmpty()) {
                        sheetData.put(testcaseName, rowData);
                    }
                }

                if (!sheetData.isEmpty()) {
                    cachedData.put(sheetName, sheetData);
                }
            }

            TestLogger.info("Loaded test data from " + cachedData.size() + " sheets");

        } catch (IOException e) {
            TestLogger.warning("Test data file not found: " + TEST_DATA_FILE);
        }
    }

    /**
     * Get test data for specific testcase from cache
     * HashMap usage: sheetName -> testcaseName -> all columns
     *
     * @param sheetName Sheet name
     * @param testcaseName Test case name
     * @return Map of column names to values
     */
    public static Map<String, String> getTestData(String sheetName, String testcaseName) {
        if (cachedData.isEmpty()) {
            loadAllTestData();
        }

        Map<String, Map<String, String>> sheetData = cachedData.get(sheetName);
        if (sheetData == null) {
            TestLogger.warning("Sheet not found in cache: " + sheetName);
            return new HashMap<>();
        }

        Map<String, String> testData = sheetData.get(testcaseName);
        if (testData == null) {
            TestLogger.warning("Test case not found: " + testcaseName);
            return new HashMap<>();
        }

        return testData;
    }

    /**
     * Get single value from test data
     * HashMap usage: Get by sheetName -> testcaseName -> columnName
     *
     * @param sheetName Sheet name
     * @param testcaseName Test case name
     * @param columnName Column name
     * @return Value as String
     */
    public static String getTestData(String sheetName, String testcaseName, String columnName) {
        Map<String, String> testData = getTestData(sheetName, testcaseName);
        String value = testData.getOrDefault(columnName, null);

        if (value != null) {
            TestLogger.info("Got test data [" + sheetName + "][" + testcaseName + "][" + columnName + "] = " + value);
        } else {
            TestLogger.warning("No data found for [" + sheetName + "][" + testcaseName + "][" + columnName + "]");
        }

        return value;
    }

    /**
     * Get value with default
     */
    public static String getTestData(String sheetName, String testcaseName, String columnName, String defaultValue) {
        String value = getTestData(sheetName, testcaseName, columnName);
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }

    /**
     * Add test data to cache (useful for parameterized tests)
     */
    public static void addTestData(String sheetName, String testcaseName, Map<String, String> data) {
        Map<String, Map<String, String>> sheetData = cachedData.computeIfAbsent(sheetName, k -> new HashMap<>());
        sheetData.put(testcaseName, data);
        TestLogger.info("Added test data for [" + sheetName + "][" + testcaseName + "]");
    }

    /**
     * Get all testcases from sheet
     */
    public static java.util.Set<String> getTestcaseNames(String sheetName) {
        if (cachedData.isEmpty()) {
            loadAllTestData();
        }

        Map<String, Map<String, String>> sheetData = cachedData.get(sheetName);
        if (sheetData == null) {
            return new java.util.HashSet<>();
        }

        return sheetData.keySet();
    }

    /**
     * Clear cache
     */
    public static void clearCache() {
        cachedData.clear();
        TestLogger.info("Test data cache cleared");
    }

    /**
     * Get cache size
     */
    public static int getCacheSize() {
        return cachedData.size();
    }

    /**
     * Helper to get cell value
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
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    /**
     * Get test data file path
     */
    public static String getTestDataFilePath() {
        return TEST_DATA_FILE;
    }
}


