package com.rishikesh.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    public static Map<String, String> getTestData(String testCaseId) throws IOException {
        String filePath = "src/test/resources/data/testdata.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, String> testData = new HashMap<>();

        for (Row row : sheet) {
            Cell testCaseCell = row.getCell(0);
            if (testCaseCell != null && testCaseCell.getStringCellValue().equals(testCaseId)) {
                Cell testDataCell = row.getCell(3);
                if (testDataCell != null) {
                    String[] dataEntries = testDataCell.getStringCellValue().split("\n");
                    for (String entry : dataEntries) {
                        String[] keyValue = entry.split(": ");
                        if (keyValue.length == 2) {
                            testData.put(keyValue[0].trim(), keyValue[1].trim());
                        }
                    }
                }
                Cell browserCell = row.getCell(4);
                if (browserCell != null) {
                    testData.put("Browser", browserCell.getStringCellValue().trim());
                }
                break;
            }
        }

        workbook.close();
        fis.close();
        return testData;
    }
}