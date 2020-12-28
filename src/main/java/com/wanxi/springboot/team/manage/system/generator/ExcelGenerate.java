package com.wanxi.springboot.team.manage.system.generator;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelGenerate {
    /*public void writeExcel1() throws Exception {
        //文件输出位置
        OutputStream out = new FileOutputStream("D:/writeExcel/test.xlsx");

        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        //写仅有一个Sheet的Excel文件，此场景较为通用
        Sheet sheet = new Sheet(1, 0, WriteModel.class);
        //第一个sheet名称
        sheet.setSheetName("第一个sheet");
        //写数据到write上下文中
        //参数1。创建要写入的模型数据0/参数2。要写入的目标sheet
        writer.write(
                createModelList(), sheet);
        //将上下文中的最终outputStream写入到指定文件中
        writer.finish();
        //关闭流
        out.close();

    }*/
}
