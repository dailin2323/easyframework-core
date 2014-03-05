package com.easyframework.core.base;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Title: JdbcSupport.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:04:54
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:04:54
 */
@Component
public class JdbcSupport<T> {
	private static final Logger logger = Logger.getLogger(JdbcSupport.class);
	
	@Resource(name = "bpmJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	

	public JdbcTemplate getTemplate(){
		return this.jdbcTemplate;
	}
	
	public JdbcSupport() {
		
	}
	
	
	
			
}





