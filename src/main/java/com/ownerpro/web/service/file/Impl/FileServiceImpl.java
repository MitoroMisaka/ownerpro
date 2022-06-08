package com.ownerpro.web.service.file.Impl;

import com.ownerpro.web.common.Result;
import com.ownerpro.web.entity.Files;
import com.ownerpro.web.mapper.FileMapper;
import com.ownerpro.web.service.file.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.save-path}")
    private String savePath;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Result upLoadFiles(MultipartFile file) {
        //设置支持最大上传的文件，这里是1024*1024*2=2M
        long MAX_SIZE=2097152L;
        //获取要上传文件的名称
        String fileName=file.getOriginalFilename();
        //如果名称为空，返回一个文件名为空的错误
        if (StringUtils.isEmpty(fileName)){
            return Result.fail("文件名为空");
        }
        //如果文件超过最大值，返回超出可上传最大值的错误
        if (file.getSize()>MAX_SIZE){
            return Result.fail("文件超过最大值");
        }
        //获取到后缀名
        String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
        //文件的保存重新按照时间戳命名
        String newName = System.currentTimeMillis() + suffixName;
        File newFile=new File(savePath,newName);
        if (!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdirs();
        }
        try {
            //文件写入
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将这些文件的信息写入到数据库中
        fileMapper.insertFile(newFile.getPath(),fileName, suffixName);
        String url = "http://localhost:8081/"+fileName;
        return Result.success("上传成功", url);
    }

    //根据id获取文件信息
    @Override
    public Files getFileById(Long id) {
        Files files = fileMapper.selectFileById(id);
        return files;
    }

    //将文件转化为InputStream
    @Override
    public InputStream getFileInputStream(Files files) {
        File file=new File(files.getFilePath());
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Result deleteFile(Long id){
        Files files = fileMapper.selectFileById(id);
        File file=new File(files.getFilePath());
        if (file.exists()){
            file.delete();
        }
        fileMapper.deleteFile(id);
        return Result.success("删除成功");
    }
}

