package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Comment;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.CommentMapper;
import com.victorzhang.cfs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, String> implements CommentService {

    @Autowired
    @Qualifier("commentMapper")
    private CommentMapper commentMapper;

    @Override
    protected BaseMapper<Comment, String> getMapper() {
        return commentMapper;
    }
}
