package com.zealep.ventasbackend.util;

import org.apache.poi.ss.usermodel.*;

import java.util.Date;

public class ExcelUtil {

    public static CellStyle headersStyle(Workbook wb){
        Font fontTitle = wb.createFont();
        fontTitle.setFontHeightInPoints((short)9);
        fontTitle.setFontName("Arial");
        fontTitle.setBold(true);
        fontTitle.setColor(IndexedColors.WHITE.getIndex());

        CellStyle styleTitle = wb.createCellStyle();
        styleTitle.setAlignment(HorizontalAlignment.CENTER);
        styleTitle.setFont(fontTitle);
        styleTitle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        styleTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return styleTitle;
    }

    public static CellStyle rowsStyle(Workbook wb){

        Font fontColumn = wb.createFont();
        fontColumn.setFontHeightInPoints((short)9);
        fontColumn.setFontName("Arial");
        fontColumn.setBold(true);

        CellStyle styleColumn = wb.createCellStyle();
        styleColumn.setFont(fontColumn);
        styleColumn.setWrapText(true);
        return styleColumn;
    }

    public static void createStringCell(String value, Row row, int colIndex, CellStyle styleCellValue){
        if (value != null){
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(value);
            cell.setCellStyle(styleCellValue);
        }
    }

    public static void createDateCell(Date value, Row row, int colIndex, CellStyle styleDateCellValue){
        if (value != null){
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(value);
            cell.setCellStyle(styleDateCellValue);
        }
    }

    public static void createIntegerCell(Integer value, Row row, int colIndex, CellStyle styleCellValue){
        if (value != null){
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(value);
            cell.setCellStyle(styleCellValue);
        }
    }

    public static void createLongCell(Long value, Row row, int colIndex, CellStyle styleCellValue){
        if (value != null){
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(value);
            cell.setCellStyle(styleCellValue);
        }
    }

    public static void createDoubleCell(Double value, Row row, int colIndex, CellStyle styleCellValue){
        if (value != null){
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(value);
            cell.setCellStyle(styleCellValue);
        }
    }
}
