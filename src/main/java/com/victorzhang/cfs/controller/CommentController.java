package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.domain.Comment;
import com.victorzhang.cfs.service.CommentService;
import com.victorzhang.cfs.util.CommonUtils;
import com.victorzhang.cfs.util.query.GenericQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

import static com.victorzhang.cfs.util.Constants.*;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    @Qualifier("commentService")
    private CommentService commentService;

    @RequestMapping("/forwardCommentResourceUI.do")
    public String forwardCommentResourceUI() {
        return "commentResource";
    }

    @RequestMapping("/listPaging.do")
    @ResponseBody
    public Map<String, Object> listPaging(String _page, String _pageSize, String resourceName, String resourceType, String startDate, String endDate) throws Exception {
        Comment comment = new Comment(CommonUtils.sesAttr(request, USER_ID));
        GenericQueryParam param = new GenericQueryParam();
        param.fill("resourceName", resourceName);
        param.fill("resourceType", resourceType);
        return commentService.listPaging(comment, _page, _pageSize, startDate, endDate, param);
    }

    @RequestMapping(value = "/updateComment.do", produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public String updateComment(String commentId, String commentContent) throws Exception {
        Comment comment = new Comment(commentId, commentContent);
        if(!commentService.update(comment)){
            throw new SQLException(UPDATE_ERROR);
        }
        return UPDATE_SUCCESS;
    }

    @RequestMapping(value = "/deleteComment.do", produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public String deleteComment(String commentId) throws Exception {
        if(!commentService.remove(commentId)){
            throw new SQLException(REMOVE_ERROR);
        }
        return REMOVE_SUCCESS;
    }
}
