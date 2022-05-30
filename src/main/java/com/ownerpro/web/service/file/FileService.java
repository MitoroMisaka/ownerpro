package com.ownerpro.web.service.file;

import com.ownerpro.web.common.Result;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import com.ownerpro.web.entity.Files;

@Repository
public interface FileService {
    /**
     * 文件上传接口
     * @param file
     * @return
     */
    Result upLoadFiles(MultipartFile file);

    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    Files getFileById(Long id);

    /**
     * 根据id获取数据流
     * @param files
     * @return
     */
    InputStream getFileInputStream(Files files);
}
