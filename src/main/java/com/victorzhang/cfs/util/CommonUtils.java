package com.victorzhang.cfs.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CommonUtils {
	
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static HttpSession getSession(boolean flag) {
		return getRequest().getSession(flag);
	}
	
	public static String sesAttr(HttpServletRequest request, String id){
		if(request.getSession().getAttribute(id)!=null){
			return request.getSession().getAttribute(id).toString();
		}
		return null;
	}

	public static String getIpAddr() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		
		return ip; 
	}

	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	public static String newUuid() {
		return UUID.randomUUID().toString().toUpperCase().replace("-", "");
	}
	
	public static int paraPage(String _page){
		int page = 1;
		if(StringUtils.isNotEmpty(_page)){
			try{
				page = Integer.parseInt(_page);
				if(page < 1)
					page = 1;
			}catch(Exception e){
			}
		}
		return page;
	}
	
	public static int paraPageSize(String _pageSize){
		int pageSize = 10;
		if(StringUtils.isNotEmpty(_pageSize)){
			try{
				pageSize = Integer.parseInt(_pageSize);
				if(pageSize < 1)
					pageSize = 1;
			}catch(Exception e){}
		}
		return pageSize;
	}
	
	public static Map<String,Object> para4Page(Map<String,Object> result,int page, int pageSize, int count){
		int pageSum = 1;
		if(count > 0){
			if(count > pageSize){
				pageSum = (count + pageSize -1)/pageSize;
				if(page > pageSum){
					page = pageSum;
				}
			}else{
				page = 1;
			}
			result.put("pageSize", pageSize);
		}else{
			count = 0;
		}
		result.put("begin", (page-1)*pageSize);
		result.put("page", page);
		result.put("pageSum", pageSum);
		result.put("count", count);
		
		return result;
	}
	
	public static List<Map<String,Object>> dataNull(List<Map<String,Object>> datas){
		for(Map<String,Object> data : datas){
			Set<String> keys = data.keySet();
			for(String key : keys){
				if(data.get(key) == null){
					data.put(key, "");
				}
			}
		}
		return datas;
	}

}
