package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Comment;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.CommentMapper;
import com.victorzhang.cfs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, String> implements CommentService {

    @Autowired
    @Qualifier("commentMapper")
    private CommentMapper commentMapper;

    @Override
    protected BaseMapper<Comment, String> getMapper() {
        return commentMapper;
    }

    @Override
    public int removeByResourceId(String resourceId) throws Exception {
        return commentMapper.removeByResourceId(resourceId);
    }

    @Override
    public List<Map<String, Object>> listByResourceId(String resourceId) throws Exception {
        return commentMapper.listByResourceId(resourceId);
    }
}
