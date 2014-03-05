package com.easyframework.core.base;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Title: BaseTest.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:04:34
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:04:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml", "classpath:spring-druid.xml" })
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-ehcache.xml", "classpath:spring-hibernate.xml", "classpath:spring-druid.xml", "classpath:spring-tasks.xml"  }) 
public class BaseTest {
	protected final Logger logger = Logger.getLogger(BaseTest.class);
}


