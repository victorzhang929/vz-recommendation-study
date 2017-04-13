package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.service.ResourceService;
import com.victorzhang.cfs.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.Map;

import static com.victorzhang.cfs.util.Constants.*;

@Controller
@RequestMapping("resource")
public class ResourceController {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String CONTENT_LENGTH = "Content-Length";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    @Qualifier("resourceService")
    private ResourceService resourceService;

    @RequestMapping("/forwardUserResourceUI.do")
    public String forwardUserResourceUI() {
        return "userResource";
    }

    @RequestMapping(value = "/doUploadResource.do", method = RequestMethod.POST)
    @ResponseBody
    public void doUploadResource(MultipartFile resourceFile, String resourceName, String resourceDescription, String resourceTag, String resourceType) throws Exception {
        if (StringUtils.isEmpty(resourceName) || StringUtils.isEmpty(resourceTag) || StringUtils.isEmpty(resourceTag)) {
            throw new IllegalArgumentException(ARGS_ERROR);
        }
        resourceService.doUploadResource(resourceFile, resourceName, resourceDescription, resourceTag, resourceType, request);
    }

    @RequestMapping("/listPaging.do")
    @ResponseBody
    public Map<String, Object> listPaging(String _page, String _pageSize, String resourceName, String resourceType, String verifyType, String startDate, String endDate) throws Exception {
        Resource resource = new Resource();
        resource.setUserId(CommonUtils.sesAttr(request, USER_ID));
        resource.setResourceName(resourceName);
        resource.setResourceType(resourceType);
        resource.setVerifyType(verifyType);
        return resourceService.listPaging(resource, _page, _pageSize, startDate, endDate, null);
    }

    @RequestMapping("/getById.do")
    @ResponseBody
    public Resource getById(String id) throws Exception {
        return resourceService.getById(id);
    }

    @RequestMapping(value = "/doDownloadResource.do", method = RequestMethod.GET)
    @ResponseBody
    public void doDownloadResource(HttpServletResponse response, String id) throws Exception {
        Resource resource = resourceService.getById(id);
        try {
            File file = new File(resource.getResourceServerPath());
            String fileName = file.getName();
            String downloadFileName = fileName.substring(0, fileName.lastIndexOf(DOT_STRING)) + CommonUtils.currTimestamp() + fileName.substring(fileName.lastIndexOf(DOT_STRING));

            InputStream fis = new BufferedInputStream(new FileInputStream(resource.getResourceServerPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.setHeader(CONTENT_DISPOSITION, "attachment;filename=" + CommonUtils.formatDownloadFileName(downloadFileName));
            response.setHeader(CONTENT_LENGTH, String.valueOf(file.length()));
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            os.write(buffer);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/removeResource.do")
    @ResponseBody
    public void removeResource(String id) throws Exception {
        Resource resource = resourceService.getById(id);
        File file = new File(resource.getResourceServerPath());
        if (file.isFile() && file.exists()) {
            file.delete();
            if (!resourceService.remove(id)) {
                throw new SQLException(REMOVE_ERROR);
            }
        }
    }
}
