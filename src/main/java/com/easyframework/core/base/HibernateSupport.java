package com.easyframework.core.base;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.easyframework.core.exception.DaoException;

/**
 * @Title: HibernateSupport.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:04:43
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:04:43
 */
@Component
public class HibernateSupport<T> {
	protected final Logger logger = Logger.getLogger(HibernateSupport.class);
	
	@Resource
	private SessionFactory sessionFactory;
	
	public HibernateSupport() {}
	
	public Session getSession() {
		try{
			return sessionFactory.getCurrentSession();
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("获取session调用失败", e);
		}
	}
	
	
	
}
