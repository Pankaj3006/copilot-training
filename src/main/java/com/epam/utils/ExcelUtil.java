package com.epam.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public final class ExcelUtil {

    private ExcelUtil() {
    }

    public static void writeSearchResults(int busCount, int totalSeats, String fromLocation, String toLocation) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Search Results");

            sheet.createRow(0).createCell(0).setCellValue("Metric");
            sheet.getRow(0).createCell(1).setCellValue("Value");

            sheet.createRow(1).createCell(0).setCellValue("From Location");
            sheet.getRow(1).createCell(1).setCellValue(fromLocation);

            sheet.createRow(2).createCell(0).setCellValue("To Location");
            sheet.getRow(2).createCell(1).setCellValue(toLocation);

            sheet.createRow(3).createCell(0).setCellValue("Number of Buses");
            sheet.getRow(3).createCell(1).setCellValue(busCount);

            sheet.createRow(4).createCell(0).setCellValue("Total Available Seats");
            sheet.getRow(4).createCell(1).setCellValue(totalSeats);

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            String downloadsPath = System.getProperty("user.home") + "\\Downloads\\output.xlsx";
            try (FileOutputStream fileOut = new FileOutputStream(downloadsPath)) {
                workbook.write(fileOut);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to write Excel file.", exception);
        }
    }
}
