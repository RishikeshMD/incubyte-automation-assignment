package com.rishikesh.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TestData {
    private static Map<String, Map<String, String>> testDataCache = new HashMap<>();
    private static String currentTestCaseId;

    static {
        loadTestData();
    }

    private static void loadTestData() {
        String excelFilePath = "testdata.xlsx";
        try (InputStream is = TestData.class.getClassLoader().getResourceAsStream(excelFilePath)) {
            if (is == null) {
                System.err.println("Excel file not found at " + excelFilePath + ". Using default test data.");
                loadDefaultTestData();
                return;
            }
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            Map<Integer, String> columnMap = new HashMap<>();

            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    columnMap.put(i, cell.getStringCellValue());
                    System.out.println("Excel column " + i + ": " + cell.getStringCellValue());
                }
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                String testCaseId = row.getCell(0).getStringCellValue();
                rowData.put("Test Case ID", testCaseId);

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String columnName = columnMap.get(j);
                    String cellValue = (cell != null) ? cell.toString() : "";

                    if (columnName.equals("Test Data")) {
                        // Parse the Test Data column
                        String[] keyValuePairs = cellValue.split("\n");
                        for (String pair : keyValuePairs) {
                            String[] keyValue = pair.split(": ", 2);
                            if (keyValue.length == 2) {
                                rowData.put(keyValue[0].trim(), keyValue[1].trim());
                            }
                        }
                    } else {
                        rowData.put(columnName, cellValue);
                    }
                }
                testDataCache.put(testCaseId, rowData);
                System.out.println("Loaded test data for " + testCaseId + ": " + rowData);
            }
        } catch (Exception e) {
            System.err.println("Failed to load test data from Excel file: " + excelFilePath + ". Using default test data.");
            e.printStackTrace();
            loadDefaultTestData();
        }
    }

    private static void loadDefaultTestData() {
        String uniqueEmail = "testuser_" + System.currentTimeMillis() + "@demoincubytetesting.com";

        Map<String, String> tc1Data = new HashMap<>();
        tc1Data.put("Test Case ID", "TC_ID_01");
        tc1Data.put("Test Case Name", "Sign Up");
        tc1Data.put("First Name", "Rishikesh");
        tc1Data.put("Last Name", "D");
        tc1Data.put("email", uniqueEmail);
        tc1Data.put("password", "Rishikesh@123");
        tc1Data.put("confirm password", "Rishikesh@123");
        tc1Data.put("Browser", "Chrome");
        testDataCache.put("TC_ID_01", tc1Data);
        System.out.println("Default test data for TC_ID_01: " + tc1Data);

        Map<String, String> tc2Data = new HashMap<>();
        tc2Data.put("Test Case ID", "TC_ID_02");
        tc2Data.put("Test Case Name", "Sign In");
        tc2Data.put("First Name", "Rishikesh");
        tc2Data.put("Last Name", "D");
        tc2Data.put("email", uniqueEmail);
        tc2Data.put("password", "Rishikesh@123");
        tc2Data.put("confirm password", "Rishikesh@123");
        tc2Data.put("Browser", "Chrome");
        testDataCache.put("TC_ID_02", tc2Data);
        System.out.println("Default test data for TC_ID_02: " + tc2Data);
    }

    public static String getBrowser() {
        Map<String, String> data = testDataCache.get(currentTestCaseId);
        return data != null ? data.get("Browser") : null;
    }

    public static Map<String, String> getTestData(String testCaseId) {
        currentTestCaseId = testCaseId;
        Map<String, String> data = testDataCache.get(testCaseId);
        if (data == null) {
            System.err.println("No test data found for test case: " + testCaseId);
        } else {
            System.out.println("Returning test data for " + testCaseId + ": " + data);
        }
        return data;
    }
}