package com.easyframework.core.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.easyframework.core.converter.MapToObjectConverter;
import com.easyframework.core.converter.ObjectToObjectConverter;
import com.easyframework.core.converter.ObjectToTreeConverter;
import com.easyframework.core.easyUI.DataGrid;
import com.easyframework.core.easyUI.DataGridParam;
import com.easyframework.core.easyUI.Tree;
import com.easyframework.core.easyUI.TreeGrid;
import com.easyframework.core.easyUI.TreeGridParam;
import com.easyframework.core.easyUI.TreeParam;
import com.easyframework.core.easyUI.TreeParam.TreeDepth;
import com.easyframework.core.pageModel.PageBean;
import com.easyframework.core.pageModel.PageQueryParam;

/**
 * @Title: BaseDao.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:03:20
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:03:20
 */
public interface BaseDao<T> {
	
	
	//-------------------SQL SUPPORT--------------------
	List sqlQuery(String sql, Object[] args, Class targetClass, MapToObjectConverter mapToObjectConverter);

	PageBean sqlPageQuery(String sql, Object[] args, PageQueryParam pageQueryParam, Class targetClass, MapToObjectConverter mapToObjectConverter);
	
	Object sqlGetUniqueResult(String sql, Object[] args, Class targetClass, MapToObjectConverter mapToObjectConverter);
	
	void sqlExecute(String sql);
	
	long sqlGetCount(String sql, Object[] args);
	
	Object sqlGetDictById(String tabName, Long id, Class targetClass, MapToObjectConverter mapToObjectConverter);
	
	Object sqlGetDictByType(String tabName, int type, Class targetClass, MapToObjectConverter mapToObjectConverter);
	
	Object sqlGetDictByCode(String tabName, String code, Class targetClass, MapToObjectConverter mapToObjectConverter);
	
	//-------------------Persistence SUPPORT--------------------
	/**
	 * @Description:add
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	Serializable save(T entity);

	/**
	 * @Description: add or update
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	void saveOrUpdate(T entity);
	
	/**
	 * @Description: update
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	void update(T entity);

	/**
	 * @Description: delete
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	void delete(T entity);

	/**
	 * @Description: get by id
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	Object getById(Long id, Class targetClass, ObjectToObjectConverter objectToObjectConverter);

	/**
	 * @Description: get by ids
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	List getByIds(List<Long> ids, Class targetClass, ObjectToObjectConverter objectToObjectConverter);

	/**
	 * @Description: get by name
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	Object getByName(String name, Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	/**
	 * @Description: get by names
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	List getByNames(List<String> names, Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	/**
	 * @Description: TODO
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	List findAllExcept(List<Long> ids, Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	/**
	 * @Description: TODO
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	List find(PageQueryParam pageQueryParam, List<Criterion> criterions, Order[] orders, 
			Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	/**
	 * @Description: find all
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	List findAll(Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	Long getCount(List<Criterion> criterions);
	Long getCount(TreeGridParam treeGridParam, List<Criterion> criterions);
	
	Long getCount(DataGridParam dataGridParam, List<Criterion> criterions);
	
	/**
	 * @Description: page query
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	PageBean pageQuery(PageQueryParam pageQueryParam, List<Criterion> criterions, Order[] orders,
			Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	/**
	 * @Description: TODO
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	DataGrid getDataGrid(DataGridParam dataGridParam, List<Criterion> criterions, Order[] orders,
			Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	/**
	 * @Description: TODO
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	List<Tree> getTree(TreeParam treeParam, TreeDepth treeDepth, List<Criterion> criterions,
			Order[] orders, ObjectToTreeConverter objectToTreeConverter);
	
	/**
	 * @Description: TODO
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	TreeGrid getTreeGrid(TreeGridParam treeGridParam, List<Criterion> criterions, Order[] orders,
			Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	
	/**
	 * @Description: 查询唯一记录
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	Object getUniqueResult(List<Criterion> criterions, Class targetClass, ObjectToObjectConverter objectToObjectConverter);

	/**
	 * 通过id查询字典
	 * @param id
	 * @param modelParser
	 * @param supportType
	 * @return
	 */
	Object getDictById(Long id, Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	/**
	 * 通过type查询字典
	 * @param type
	 * @param modelParser
	 * @param supportType
	 * @return
	 */
	Object getDictByType(int type, Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	/**
	 * 通过code查询字典
	 * @param code
	 * @param modelParser
	 * @param supportType
	 * @return
	 */
	Object getDictByCode(String code, Class targetClass, ObjectToObjectConverter objectToObjectConverter);
	
	
	
	
}
