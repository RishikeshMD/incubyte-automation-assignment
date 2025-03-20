package com.rishikesh.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private static final String FILE_PATH = "src/test/resources/data/testdata.xlsx";

    public static Map<String, String> getTestData(String testCaseId) throws IOException {
        // Debug: Verify the file path
        File file = new File(FILE_PATH);
        System.out.println("Excel file path: " + file.getAbsolutePath());
        if (!file.exists()) {
            throw new IOException("Excel file not found at: " + file.getAbsolutePath());
        }

        Map<String, String> testData = new HashMap<>();
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet

        // Debug: Print the total number of rows in the sheet
        System.out.println("Total rows in Excel sheet: " + sheet.getPhysicalNumberOfRows());

        // Debug: Print all Test Case IDs in the sheet
        System.out.println("Listing all Test Case IDs in the sheet:");
        for (Row row : sheet) {
            Cell testCaseCell = row.getCell(0); // Test Case ID is in the first column
            if (testCaseCell != null) {
                String testCaseIdInExcel = testCaseCell.getStringCellValue().trim();
                System.out.println("Row " + row.getRowNum() + ": Test Case ID = '" + testCaseIdInExcel + "'");
            } else {
                System.out.println("Row " + row.getRowNum() + ": Test Case ID cell is null");
            }
        }

        // Find the row with the matching Test Case ID
        boolean found = false;
        for (Row row : sheet) {
            Cell testCaseCell = row.getCell(0); // Test Case ID is in the first column
            if (testCaseCell != null) {
                String testCaseIdInExcel = testCaseCell.getStringCellValue().trim();
                System.out.println("Comparing Test Case ID: '" + testCaseIdInExcel + "' with expected: '" + testCaseId + "'");
                if (testCaseIdInExcel.equals(testCaseId)) {
                    System.out.println("Found row for Test Case ID: " + testCaseId);
                    found = true;
                    testData.put("Test Case Name", row.getCell(1).getStringCellValue());

                    // Read the Test Data column (column index 3)
                    Cell testDataCell = row.getCell(3);
                    if (testDataCell != null) {
                        String rawTestData = testDataCell.getStringCellValue();
                        System.out.println("Raw Test Data: " + rawTestData);
                        String[] pairs = rawTestData.split("\n"); // Split by newline
                        for (String pair : pairs) {
                            System.out.println("Parsing pair: " + pair);
                            // Handle spaces before and after the colon
                            String[] keyValue = pair.split("\\s*:\\s*");
                            if (keyValue.length == 2) {
                                String key = keyValue[0].trim();
                                String value = keyValue[1].trim();
                                testData.put(key, value);
                                System.out.println("Added to map: " + key + " = " + value);
                            } else {
                                System.out.println("Invalid key-value pair: " + pair);
                            }
                        }
                    } else {
                        System.out.println("Test Data cell is empty for Test Case ID: " + testCaseId);
                    }

                    // Read the Browser column (column index 4)
                    Cell browserCell = row.getCell(4);
                    if (browserCell != null) {
                        testData.put("Browser", browserCell.getStringCellValue());
                        System.out.println("Browser: " + browserCell.getStringCellValue());
                    } else {
                        System.out.println("Browser cell is empty for Test Case ID: " + testCaseId);
                    }
                    break;
                }
            } else {
                System.out.println("Test Case ID cell is null for row: " + row.getRowNum());
            }
        }

        if (!found) {
            System.out.println("Test Case ID " + testCaseId + " not found in Excel sheet");
        }

        workbook.close();
        fis.close();
        return testData;
    }
}