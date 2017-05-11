package com.victorzhang.cfs.service;

import com.victorzhang.cfs.domain.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService extends BaseService<Comment, String> {
    int removeByResourceId(String resourceId) throws Exception;

    List<Map<String,Object>> listByResourceId(String resourceId) throws Exception;
}
