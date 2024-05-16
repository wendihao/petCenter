package com.huang.controller;

import com.huang.entity.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileUploadController {
    private String imgString;
    // 设置保存地址（这里是转义字符）
    private  String filePath="/image";
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file){
        // 判断文件是否为空
        if(file.isEmpty()){
            return new Result(false,"上传文件不能为空");
        }
        // 获取传过来的文件名字
        String OriginalFilename=file.getOriginalFilename();
        //判断文件类型
        String type = OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        if (!type.equals("jpg")&&!type.equals("png")){
            return new Result(false,"不是jpg或png");
        }
        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String fileName=System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        File dest=new File(new File(filePath).getAbsolutePath()+"/"+fileName);
        imgString = "http://119.29.250.12:8081/myimg/"+fileName;
        // 判断文件是否存在
        if(!dest.getParentFile().exists()){
            // 不存在就创建一个
            dest.getParentFile().mkdirs();
        }
        try {
            // 上传
            file.transferTo(dest);
           // System.out.println(imgString);
           // System.out.println(dest);
            return new Result(true,imgString);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"上传失败");
        }
    }
    @PostMapping("/uploads")
    public Result uploadFiles(@RequestParam("files") MultipartFile[] files){
        String img = "";
        File fileDir = new File(filePath);
        if (!fileDir.exists() && !fileDir.isDirectory()){
            fileDir.mkdirs();
        }
        if (files !=null && files.length>0){
            for (int i =0;i<files.length;i++){
                String OriginalFilename=files[i].getOriginalFilename();
               // System.out.println("文件名为:"+OriginalFilename);
                String fileName=System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
                File dest=new File(new File(filePath).getAbsolutePath()+"/"+fileName);
                imgString = "http://119.29.250.12:8081/myimg/"+fileName;
                try {
                    files[i].transferTo(dest);
                    if (img.equals("")){
                        img=imgString;
                    }else {
                        img = img+";"+imgString;
                    }
                }catch (IOException e){
                    e.printStackTrace();
                    return new Result(false,e.getMessage());
                }
            }
        }else {
           // System.out.println(files);
            return new Result(false,"文件为0");
        }
        return new Result(true,img);
    }
}

