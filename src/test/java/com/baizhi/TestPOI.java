package com.baizhi;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.UserEcharts;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestPOI {
    @Resource
    private UserDAO userDAO;
    @Resource
    private UserService userService;
    @Test
    public void test1() throws Exception {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("这是我做的表");
        ((HSSFWorkbook) workbook).write(new FileOutputStream(new File("D:/testpio.xls")));
        workbook.close();
    }

    @Test
    public void test2() throws Exception {
        Admin admin = new Admin("1", "xiao1", "123455");
        Admin admin1 = new Admin("2", "xiao2", "123455");
        Admin admin2= new Admin("3", "xiao3", "123455");
        Admin admin3 = new Admin("4", "xiao4", "123455");
        Admin admin4 = new Admin("5", "xiao5", "123455");
        Admin admin5 = new Admin("6", "xiao6", "123455");
        List<Admin> list = new ArrayList<>();
        list.add(admin);
        list.add(admin1);
        list.add(admin2);
        list.add(admin3);
        list.add(admin4);
        list.add(admin5);
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setUnderline(FontFormatting.U_SINGLE);
        font.setColor(Font.COLOR_RED);
        font.setFontHeight((short) 20);
        //创建样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);     //将字体样式引入
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        String[] strings={"id","adminname","password"};
        for (int i = 0; i < strings.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(strings[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            Admin admin6 = list.get(i);
            Cell cell = row1.createCell(0);
            cell.setCellValue(admin6.getId());
            Cell cell1 = row1.createCell(1);
            cell1.setCellValue(admin6.getAdminname());
            Cell cell2 = row1.createCell(2);
            cell2.setCellValue(admin6.getPassword());
        }
        ((HSSFWorkbook) workbook).write(new FileOutputStream(new File("D:/testpio.xls")));
        workbook.close();
    }


    @Test
    public void selectBySex(){
        List<UserEcharts> list = userDAO.selectBySex("男");
        for (UserEcharts userEcharts : list) {
            System.out.println(userEcharts);
        }

    }
    @Test
    public void TestService(){
        Map<String, Object> map = userService.userEcharts();

    }
}
