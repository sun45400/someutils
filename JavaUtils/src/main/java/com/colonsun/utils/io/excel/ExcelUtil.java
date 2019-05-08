package com.colonsun.utils.io.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private ExcelUtil(){

    }

    public static void main(String[] args) throws IOException {
        tsetInput();

    }

    public static void testOutput() throws IllegalAccessException {
        String path = ExcelUtil.class.getResource("").getPath();
        String file = path + "test.xlsx";
        List<OutputTestObj> list = new LinkedList<OutputTestObj>();
        OutputTestObj obj = new OutputTestObj();
        obj.setField1("123");
        obj.setField2("1324");
        obj.setField3("12345");
        OutputTestObj obj1 = new OutputTestObj();
        obj1.setField1("222");
        obj1.setField3("324");
        list.add(obj);
        list.add(obj1);
        exportExcelByHeader(file,list);
    }

    public static void tsetInput() throws IOException {
        String filePath = "/D:/git/sunbangchao/JavaUtils/someutils/JavaUtils/target/classes/com/colonsun/utils/io/excel/test.xlsx";
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet0 = workbook.getSheetAt(0);
        for(Row r : sheet0){
            for(Cell c : r){
                System.out.print(c.getStringCellValue() + "   ");
                continue;
            }
            System.out.println();
        }
        inputStream.close();


    }

    public static void exportExcelByStringList(String filePath, List<List<String>> list){
        HSSFWorkbook workbook = new HSSFWorkbook(); //workbook 工作簿
        HSSFSheet sheet = workbook.createSheet(); //单个sheet
        //内容
        for(int i = 0;i< list.size();i++){
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell;
            List<String> listDetails = list.get(i);
            for(int j = 0;j < listDetails.size();j++){
                cell = row.createCell(j);
                cell.setCellValue(listDetails.get(j));
            }
        }
        //创建文件
        File file = new File(filePath);
        try{
            file.createNewFile();
            FileOutputStream outputStream = FileUtils.openOutputStream(file);
            workbook.write(outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //打印有@Header注释的属性
    public static void exportExcelByHeader(String filePath,List list) throws IllegalAccessException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        Map<String,Integer> titleMap = new HashMap<String, Integer>();
        //表头
        for(int i = 0 ;i < fields.length;i++){
            Field field = fields[i];
            Header header = field.getAnnotation(Header.class);
            if(header == null) {
                fields[i] = null;
                continue;
            }
            String title = header.value().equals("") ? field.getName() : header.value();
            cell = row.createCell(header.index());
            cell.setCellValue(title);
        }
        //内容
        for(int i = 0;i < list.size();i++){
            row = sheet.createRow(i+1);
            for(Field field : fields){
                if(field == null){continue;}
                if(!field.isAccessible()){field.setAccessible(true);}
                cell = row.createCell(field.getAnnotation(Header.class).index());
                String cellString = field.get(list.get(i)) == null ? "" : field.get(list.get(i)).toString();
                cell.setCellValue(cellString);
            }
        }
        File file = new File(filePath);
        try{
            file.createNewFile();
            FileOutputStream outputStream = FileUtils.openOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //导入
    public static void importExcelBy (String filePath,Class clazz) throws IOException{
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = new HSSFWorkbook(inputStream);
    }
}
