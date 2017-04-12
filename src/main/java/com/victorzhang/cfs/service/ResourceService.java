package com.victorzhang.cfs.service;

import com.victorzhang.cfs.domain.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ResourceService extends BaseService<Resource, String> {
    void doUploadResource(MultipartFile resourceFile, String resourceName, String resourceDescription, String resourceTag, String resourceType, HttpServletRequest request) throws Exception;
}
