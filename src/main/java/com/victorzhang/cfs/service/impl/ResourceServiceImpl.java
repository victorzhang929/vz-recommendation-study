package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.ResourceMapper;
import com.victorzhang.cfs.service.ResourceService;
import com.victorzhang.cfs.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static com.victorzhang.cfs.util.Constants.*;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource, String> implements ResourceService {

    @Autowired
    @Qualifier("resourceMapper")
    private ResourceMapper resourceMapper;

    @Override
    protected BaseMapper<Resource, String> getMapper() {
        return resourceMapper;
    }

    @Override
    public void doUploadResource(MultipartFile resourceFile, String resourceName, String resourceDescription, String resourceTag, String resourceType, HttpServletRequest request) throws Exception {
        String uploadPath = CommonUtils.getUploadPath(request);
        String fileName = resourceFile.getOriginalFilename();
        String serverFileName = fileName.substring(0, fileName.lastIndexOf(DOT_STRING)) + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(DOT_STRING));
        String fileServerPath = uploadPath + serverFileName;
        Resource resource = new Resource(CommonUtils.newUuid(), resourceName, resourceDescription, resourceTag, resourceType, fileServerPath, CommonUtils.sesAttr(request, USER_ID), CommonUtils.getDateTime());
        if (!super.save(resource)) {
            throw new SQLException(INSERT_ERROR);
        }
        try {
            File file = new File(fileServerPath);
            resourceFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
