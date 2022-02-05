package com.example.common.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author zhuchao
 * @date 2022/2/5 11:41 下午
 */
public class ExecelUtil {

    public static List<Map<String, String>> readExecel(String path, int sheet) throws Exception {
        return readExecel(getClasspathFile(path), sheet);
    }

    public static List<Map<String, String>> readExecel(InputStream inputStream, int sheet) throws Exception {
        List<Map<String, String>> retVal = new ArrayList<>();
        //兼容模式
        Workbook hssfWorkbook = WorkbookFactory.create(inputStream);
        Sheet sheetAt = hssfWorkbook.getSheetAt(sheet);
        Row row = null;
        int i = 1;
        while ((row = sheetAt.getRow(i)) != null) {
            int j = 0;
            Cell cell = null;
            Map<String, String> map = new HashMap<>();
            while ((cell = row.getCell(j)) != null) {
                String cellValue = "";
                switch (cell.getCellType()) {
                    //数字
                    case Cell.CELL_TYPE_NUMERIC:
                        cellValue = String.valueOf(cell.getNumericCellValue());
                        break;
                    //字符串
                    case Cell.CELL_TYPE_STRING:
                        cellValue = String.valueOf(cell.getStringCellValue());
                        break;
                    //Boolean
                    case Cell.CELL_TYPE_BOOLEAN:
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                        break;
                    //公式
                    case Cell.CELL_TYPE_FORMULA:
                        cellValue = String.valueOf(cell.getCellFormula());
                        break;
                    //空值
                    case Cell.CELL_TYPE_BLANK:
                        cellValue = "";
                        break;
                    //故障
                    case Cell.CELL_TYPE_ERROR:
                        cellValue = "非法字符";
                        break;
                    default:
                        cellValue = "未知类型";
                        break;
                }
                map.put(j + "", cellValue);
                j++;
            }
            i++;
            retVal.add(map);
        }
        return retVal;
    }


    private static InputStream getClasspathFile(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

}
