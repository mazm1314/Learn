package com.clearn.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author Administrator
 * @Date 2018.22:53
 */
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(filePath+fileName);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.write(file);
            out.flush();
            out.close();
        }

    }

}
