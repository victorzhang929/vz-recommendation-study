package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface CommentMapper extends BaseMapper<Comment, String> {
    int removeByResourceId(String resourceId) throws Exception;

    List<Map<String, Object>> listByResourceId(String resourceId) throws Exception;
}
