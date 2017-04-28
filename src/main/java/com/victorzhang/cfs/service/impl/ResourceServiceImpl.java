package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.domain.ResourceDownload;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.ResourceMapper;
import com.victorzhang.cfs.service.ResourceService;
import com.victorzhang.cfs.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.victorzhang.cfs.util.Constants.*;


@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource, String> implements ResourceService {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String CONTENT_LENGTH = "Content-Length";

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

    @Override
    public void doDownloadResource(HttpServletResponse response, HttpServletRequest request, String id) throws Exception {
        InputStream fis = null;
        OutputStream fos = null;
        Resource resource = super.getById(id);
        try {
            File file = new File(resource.getResourceServerPath());
            String fileName = file.getName();
            String downloadFileName = fileName.substring(0, fileName.lastIndexOf(DOT_STRING)) + CommonUtils.currTimestamp() + fileName.substring(fileName.lastIndexOf(DOT_STRING));
            fis = new BufferedInputStream(new FileInputStream(resource.getResourceServerPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            response.reset();
            response.setHeader(CONTENT_DISPOSITION, "attachment;filename=" + CommonUtils.formatDownloadFileName(downloadFileName));
            response.setHeader(CONTENT_LENGTH, String.valueOf(file.length()));
            fos = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            fos.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.flush();
                fos.close();
            }
        }
        //insert record to table of resource_download
        saveResourceDownload(request, id);
        //update table of resource's resource_download_count column
        updateResourceDownload(resource);
    }

    @Override
    public List<Map<String, Object>> listNewestResource() throws Exception {
        return resourceMapper.listNewestResource();
    }

    @Override
    public List<Map<String, Object>> listHotResource() throws Exception {
        return resourceMapper.listHotResource();
    }

    private void saveResourceDownload(HttpServletRequest request, String resourceId) throws Exception {
        String resourceDownloadId = CommonUtils.newUuid();
        String resourceDownloadResourceId = resourceId;
        String resourceDownloadTime = CommonUtils.getDateTime();
        String resourceDownloadUserId = CommonUtils.sesAttr(request, USER_ID);
        int successCount = resourceMapper.saveResourceDownload(new ResourceDownload(resourceDownloadId, resourceDownloadResourceId, resourceDownloadTime, resourceDownloadUserId));
        if (successCount <= 0) {
            throw new SQLException(INSERT_ERROR);
        }
    }

    private void updateResourceDownload(Resource resource) throws Exception {
        Resource updateResourceDownloadCount = new Resource();
        int resourceDownloadCount = Integer.parseInt(resource.getResourceDownloadCount()) + 1;
        updateResourceDownloadCount.setResourceDownloadCount(String.valueOf(resourceDownloadCount));
        updateResourceDownloadCount.setId(resource.getId());
        update(updateResourceDownloadCount);
    }
}
