package com.nicole.web;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io

        .*;

/**
 * @author xiads
 * @date 11/01/2018
 * @since
 */
public class ExcelUtil {

    private File excelFile;

    public XSSFWorkbook createWorkBook() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        return workbook;
    }

    public XSSFWorkbook createWorkBook(File file) throws IOException {
        this.excelFile = file;
        FileInputStream input = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(new BufferedInputStream(input));
        return workbook;
    }

    public XSSFSheet getSheet(XSSFWorkbook workbook, int index) {
        return workbook.getSheetAt(index);
    }

    public XSSFSheet createXXSFSheet(XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        return sheet;
    }

    public XSSFCell writeCell(XSSFSheet sheet, int rowNum, int cellNum, String cellValue) throws Exception {
        XSSFRow row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        XSSFCell cell = row.createCell(cellNum);
        cell.setCellValue(cellValue);
        return cell;
    }

    public void write(XSSFWorkbook workbook) throws IOException {
        FileOutputStream fos = new FileOutputStream(excelFile);
        workbook.write(fos);
        fos.close();
    }

    public File getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(File excelFile) {
        this.excelFile = excelFile;
    }
}

