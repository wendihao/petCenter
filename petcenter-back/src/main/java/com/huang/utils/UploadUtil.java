package com.huang.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtil {
//    把图片上传到的位置
    private static final String BASE_PATH="C:\\Users\\laohuang\\Desktop\\uploade_image";
//    访问图片的路径
    private static final String SERVER_PATH="";

    public static String upload(MultipartFile file) throws IOException {
//        获取文件名
        String filename = file.getOriginalFilename();
//        用uuid进行改写，来防止文件重名
        String uuid = UUID.randomUUID().toString().replace("-","");
//        将uud与filename拼接
        String newFileName = uuid+"-"+filename;
//        创建一个实例对象
        File image = new File(BASE_PATH, newFileName);
//        对这个文件进行上传操作
        try {
            file.transferTo(image);
        }catch (IOException e){
            e.printStackTrace();
        }
        return SERVER_PATH+newFileName;
    }
}
