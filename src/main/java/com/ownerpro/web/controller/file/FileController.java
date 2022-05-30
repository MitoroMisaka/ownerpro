package com.ownerpro.web.controller.file;

import com.ownerpro.web.common.Result;
import com.ownerpro.web.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.ownerpro.web.entity.Files;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upLoadFiles(MultipartFile multipartFile){
        if (multipartFile.isEmpty()){
            return Result.fail("文件为空");
        }
        return fileService.upLoadFiles(multipartFile);
    }

    @RequestMapping(value = "/download/{id}",method = RequestMethod.GET)
    public void downloadFiles(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response){
        OutputStream outputStream=null;
        InputStream inputStream=null;
        BufferedInputStream bufferedInputStream=null;
        byte[] bytes=new byte[1024];
        Files files = fileService.getFileById(id);
        String fileName = files.getFileName();
        // 获取输出流
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" +  new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setContentType("application/force-download");
            inputStream=fileService.getFileInputStream(files);
            bufferedInputStream=new BufferedInputStream(inputStream);
            outputStream = response.getOutputStream();
            int i=bufferedInputStream.read(bytes);
            while (i!=-1){
                outputStream.write(bytes,0,i);
                i=bufferedInputStream.read(bytes);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream!=null){
                    inputStream.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
                if (bufferedInputStream!=null){
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}

