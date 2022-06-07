package com.ownerpro.web.controller.file;

import com.ownerpro.web.common.Result;
import com.ownerpro.web.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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

    @GetMapping(value = "/get-file/{id}")
    public ResponseEntity<FileSystemResource> getFile(@PathVariable("id") Long id) throws FileNotFoundException {
        Files files = fileService.getFileById(id);
        String filepath = files.getFilePath();
        //convert "\" to "/"
        filepath = filepath.replace("\\","/");
        return export(filepath);
    }

    public ResponseEntity<FileSystemResource> export(String filepath) throws FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + "8964");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(filepath));
    }

}

