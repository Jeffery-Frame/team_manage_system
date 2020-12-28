package com.wanxi.springboot.team.manage.system.util;

import com.wanxi.springboot.team.manage.system.api.CommonResult;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Upload {

    /**
     * @param request 根据请求解析请求中的参数(文件与非文件)返回Map集合,并将文件上传至服务器
     * @return
     */
    public static CommonResult fileUpload(HttpServletRequest request, String startPath, String targetPath, String url) {

        FileItemFactory factory = new DiskFileItemFactory();

        // 文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 解析请求信息
        FileItem item = null;
        try {
            item = (FileItem) upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
            CommonResult.failed("解析请求信息出错");
        }

        System.out.println(item);
        return writeFileItem(request, item, startPath, targetPath, url);
    }

    //将图片从起止文件夹复制一份到目标文件夹
    public static CommonResult uploadOut(String urlFileName, String startPath, String targetPath) {
        File file = new File(targetPath, urlFileName);
        try {
            //读取源地址文件的字节流
            FileInputStream in = new FileInputStream(startPath + urlFileName);
            FileOutputStream out = new FileOutputStream(file);
            byte[] bs = new byte[1026];
            int count = 0;
            while ((count = in.read(bs, 0, bs.length)) != -1) {
                //把读取到的字节流写入到目的地址的文件里面
                out.write(bs, 0, count);

            }
            //刷新下输出流
            out.flush();
            // 关闭输入流和输出流
            out.close();
            out.close();
            System.out.println("图片上传成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            CommonResult.failed("未找到目标文件");
        } catch (IOException e) {
            e.printStackTrace();
            return CommonResult.failed("文件写入失败");
        }
        return CommonResult.success("图片上传成功！");
    }

    public static void mutilpleFileUpload(HttpServletRequest request, String startPath,String targetPath, String url) {

        FileItemFactory factory = new DiskFileItemFactory();

        // 文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 解析请求信息
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        System.out.println(items);
        // 对请求信息进行判断
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            writeFileItem(request, item, startPath,targetPath, url);
        }
        return;
    }

    public static CommonResult writeFileItem(HttpServletRequest request, FileItem item, String startPath, String targetPath, String url) {
        // 信息为普通的格式
        if (item.isFormField()) {
            String fieldName = item.getFieldName();
            String value = item.getString();
            request.setAttribute(fieldName, value);
            return CommonResult.forbidden("该文件不是图片格式");
        }
        // 信息为文件格式
        else {
            String fileName = item.getName();//获得上传图片的名称
            int index = fileName.lastIndexOf("/");
            fileName = new Date().getTime() + "-" + fileName.substring(index + 1);
            String fieldName = item.getFieldName();
            request.setAttribute(fieldName, url + fileName);
            System.out.println(startPath + url + fileName);//打印当前位置
            File file = new File(startPath, url + fileName);
            try {
                item.write(file);
                return uploadOut(url + fileName, startPath, targetPath);
            } catch (Exception e) {
                e.printStackTrace();
                return CommonResult.failed("写入目标文件失败");
            }
        }
    }
}

