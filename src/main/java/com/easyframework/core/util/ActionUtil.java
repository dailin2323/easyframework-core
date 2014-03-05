package com.easyframework.core.util;

import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.easyframework.core.util.SpringMVCHelper;

/**
 * @Title: ActionUtil.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:11:42
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:11:42
 */
public class ActionUtil {
	// 获取config.property文件配置信息
	private static final ResourceBundle config = ResourceBundle.getBundle("config");   
	// 当前登录用户存放 "域" 标识
	private static final String CUR_LOGIN_USER = "USER";
	// 当前登录用户所属角色 "域" 标识
	private static final String USER_ROLE = "ROLE";
	// 系统所有权限存放 "域" 标识
	private static final String ALL_PRIVILEGE = "ALL_PRIVILEGE";
	// 当前登录用户权限存放"域" 标识
	private static final String CUR_USER_PRIVILEGE = "CUR_USER_PRIVILEGE";
	// 可以自由访问的资源标识
	private static final String FREE_PRIVILEGE = "FREE_PRIVILEGE";
	// 登录后可访问的资源标识
	private static final String LOGIN_ACCESS_PRIVILEGE = "LOGIN_ACCESS_PRIVILEGE";
	//ajax 请求标识
	private static final String AJAX_REQUEST_TAG = "XMLHttpRequest";
	
	/**
	 * 获取配置信息
	 * @return
	 */
	public static ResourceBundle getConfig(){
		return config;
	}
	
	/** 在这里添加框架自定义Action层扩展控件 */
	public static void setCurLoginUser(Object user){
		getSession().setAttribute(CUR_LOGIN_USER, user);
	}
	
	public static Object getCurLoginUser(){
		return getSession().getAttribute(CUR_LOGIN_USER);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequst(HttpServletRequest request){
		String requestType = request.getHeader("X-Requested-With"); 
		if(requestType == null || !requestType.equals(AJAX_REQUEST_TAG)){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * @Description: TODO
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	public static Set<String> getFreePrivilege() {
		return (Set<String>) getApplication().getAttribute(FREE_PRIVILEGE);
	}

	public static void setFreePrivilege(Set<String> freePrivilege) {
		getApplication().setAttribute(FREE_PRIVILEGE, freePrivilege);
	}

	public static Set<String> getLoginAccessPrivilege() {
		return (Set<String>) getApplication().getAttribute(
				LOGIN_ACCESS_PRIVILEGE);
	}

	//---获取 request、session、appliction 统一调用下面的方法------
	public static HttpServletRequest getRequset() {
		return SpringMVCHelper.getRequest();/*//ServletActionContext.getRequest();	*/
	}

	public static HttpSession getSession() {
		return SpringMVCHelper.getSession();//ServletActionContext.getRequest().getSession();
	}

	public static ServletContext getApplication() {
		return SpringMVCHelper.getApplication();//ServletActionContext.getRequest().getSession().getServletContext();
	}
	
	
}
