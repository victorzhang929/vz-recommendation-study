package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment, String> {
}
