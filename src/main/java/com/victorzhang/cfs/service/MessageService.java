package com.victorzhang.cfs.service;


import com.victorzhang.cfs.domain.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface MessageService extends BaseService<Message, String> {

    Map<String,Object> listUnreadMsg(HttpServletRequest request) throws Exception;

    void doReadMsg(String id) throws Exception;
}
