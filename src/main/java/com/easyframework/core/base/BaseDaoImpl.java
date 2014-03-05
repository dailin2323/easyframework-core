package com.easyframework.core.base;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.easyframework.core.config.Config;
import com.easyframework.core.converter.MapToObjectConverter;
import com.easyframework.core.converter.MapToTreeConverter;
import com.easyframework.core.converter.ObjectToObjectConverter;
import com.easyframework.core.converter.ObjectToTreeConverter;
import com.easyframework.core.easyUI.DataGrid;
import com.easyframework.core.easyUI.DataGridParam;
import com.easyframework.core.easyUI.Tree;
import com.easyframework.core.easyUI.TreeGrid;
import com.easyframework.core.easyUI.TreeGridParam;
import com.easyframework.core.easyUI.TreeParam;
import com.easyframework.core.easyUI.TreeParam.TreeDepth;
import com.easyframework.core.exception.DaoException;
import com.easyframework.core.pageModel.PageBean;
import com.easyframework.core.pageModel.PageQueryParam;

/**
 * @Title: BaseDaoImpl.java
 * @Description: Dao层未对参数进行安全检测， Dao层返回的数据保证集合类型不为null
 * @author 邹凯明
 * @date 2014-3-5 上午1:03:47
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:03:47
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
	public static final int DB_MYSQL = 0;
	public static final int DB_ORACLE = 1;
	public static final int DB_SQLSERVER = 2;
	private static int db = DB_MYSQL;
	static {
		String driverClassName = Config.get("driverClassName").toUpperCase();
		if (driverClassName.indexOf("MYSQL") >= 0) {
			db = DB_MYSQL;
		} else if (driverClassName.indexOf("ORACLE") >= 0) {
			db = DB_ORACLE;
		} else if (driverClassName.indexOf("SQLSERVER") >= 0) {
			db = DB_SQLSERVER;
		} else {
			// undefine use default config
		}
	}

	public int getDB() {
		return db;
	}

	protected final Logger logger = Logger.getLogger(BaseDaoImpl.class);

	@Resource
	private HibernateSupport<T> hibernateSupport;

	@Resource
	private JdbcSupport<T> jdbcSupport;

	protected Class<T> clazz;

	public BaseDaoImpl() {
		/** HibernateSupport的泛型参数为空或为Object时 */
		if (!(this.getClass().getGenericSuperclass() instanceof ParameterizedType)) {
			clazz = (Class<T>) Object.class;
		} else {
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			clazz = (Class<T>) pt.getActualTypeArguments()[0];
		}
		logger.debug("---> clazz = " + clazz);
	}

	protected Session getSession() {
		return hibernateSupport.getSession();
	}

	protected JdbcTemplate getTemplate() {
		return jdbcSupport.getTemplate();
	}

	/**
	 * 标准化返回结果，防止重复的null空检测
	 * 
	 * @param result
	 * @return
	 */
	private Object getStdResult(Object result) {
		if (result == null) {
			if (result instanceof List) {
				return new LinkedList();
			} else if (result instanceof Set) {
				return new HashSet();
			} else if (result instanceof Map) {
				return new HashMap();
			} else {
				return result;
			}
		} else {
			return result;
		}
	}

	private void copyMapToBean(Map<String, Object> map, Object bean) {
		if (map == null || bean == null || map.isEmpty()) {
			return;
		}
		Map<String, Object> temp = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			temp.put(((String) entry.getKey()).toUpperCase(), entry.getValue());
		}
		Method[] methods = bean.getClass().getMethods();
		if (methods != null) {
			for (Method method : methods) {
				String methodName = method.getName();
				if (methodName.startsWith("set")) {
					Object value = temp.get(methodName.substring(3)
							.toUpperCase());
					if (value != null) {
						try {
							method.invoke(bean, value);
						} catch (Exception e) {
							e.printStackTrace();
							throw new DaoException("方法反射调用失败", e);
						}
					}
				}
			}
		}
	}

	// -----------------------sql 方法----------------------------
	private Class targetClass;
	private MapToObjectConverter mapToObjectConverter;

	RowMapper<Object> rowmapperForObject = new RowMapper<Object>() {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Map<String, Object> row = new HashMap<String, Object>();
			ResultSetMetaData rsmd = rs.getMetaData();// rs为查询结果集
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				// String colName = rsmd.getColumnName(i);
				// row.put(colName, rs.getObject(colName));
				String colName = rsmd.getColumnLabel(i);
				row.put(colName, rs.getObject(colName));
			}
			Object ret = row;
			if (targetClass != null) {
				Object obj = getClassInstance(targetClass);
				copyMapToBean(row, obj);
				ret = obj;
			} else if (mapToObjectConverter != null) {
				ret = mapToObjectConverter.convert(row);
			}
			return ret;
		}
	};

	@Override
	public List sqlQuery(String sql, Object[] args, Class targetClass,
			MapToObjectConverter mapToObjectConverter) {
		try {
			this.targetClass = targetClass;
			this.mapToObjectConverter = mapToObjectConverter;
			List re = getTemplate().query(sql, args, rowmapperForObject);
			return (List) getStdResult(re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("sqlQueryToObject 查询调用失败", e);
		}
	}

	@Override
	public PageBean sqlPageQuery(String sql, Object[] args, PageQueryParam pageQueryParam, Class targetClass,
			MapToObjectConverter mapToObjectConverter) {
		long total = sqlGetCount(sql, args);
		if (getDB() == BaseDaoImpl.DB_MYSQL) {
			sql = sql + " LIMIT  " + (pageQueryParam.getNum()-1) * pageQueryParam.getRows() + " , " //
					+ pageQueryParam.getRows() + " ";
		} else if (getDB() == BaseDaoImpl.DB_ORACLE) {
			long beginIndex = (pageQueryParam.getNum()-1)*pageQueryParam.getRows() + 1;//索引从1开始
			long endIndex = pageQueryParam.getNum() * pageQueryParam.getRows();			//
			sql = "SELECT * FROM "						//
					+ " ( "                          	//
					+ " SELECT A.*, ROWNUM RN "			//
					+ " FROM (" + sql + ") A  "			//
					+ " WHERE ROWNUM <= "+ endIndex + "  "//
					+ " )                   "			//
					+ " WHERE RN >= " + beginIndex + " ";//
		} else if (getDB() == BaseDaoImpl.DB_SQLSERVER) {
			// SELECT TOP @pagesize * FROM TABLE_NAME WHERE id not in (SELECT TOP @pagesize*(@page-1) id  FROM TABLE_NAME ORDER BY id) ORDER BY id
				String tempSql = sql.toUpperCase();
				int selectIndex = tempSql.indexOf("SELECT");
				int lastOrderIndex = tempSql.lastIndexOf("ORDER");
				int whereIndex = tempSql.indexOf("WHERE");
				//含有where条件
				String connectStr = " WHERE ";
				if(whereIndex != -1){
					connectStr = " AND ";
				}
				if(lastOrderIndex == -1){
					String sqlMain = sql.substring(selectIndex + 6);
					sql = " SELECT TOP " + pageQueryParam.getRows() + " "+ sqlMain + 
							connectStr + " id NOT IN ( SELECT TOP " + pageQueryParam.getRows()*(pageQueryParam.getNum()-1) + " " + sqlMain +
							" ) ";   
						
				}else{
					String sqlMain = " " + sql.substring(selectIndex + 6, lastOrderIndex) + " ";
					String sqlOrder = " "+sql.substring(lastOrderIndex) + " ";
					sql = " SELECT TOP " + pageQueryParam.getRows() + " "+ sqlMain + 
							connectStr + " id NOT IN ( SELECT TOP " + pageQueryParam.getRows()*(pageQueryParam.getNum()-1) + " " + sqlMain +
							sqlOrder + " ) " + sqlOrder;   
				}
		} else {
			throw new DaoException("Unknown DataBase!");
		}

		PageBean pageBean = new PageBean();
		List rows = sqlQuery(sql, args, targetClass, mapToObjectConverter);
		pageBean.setRows(rows);
		pageBean.setQueryRows(pageQueryParam.getRows());// 同步分页方式下可能使用到该参数
		pageBean.setNum(pageQueryParam.getNum()); // 同步分页方式下可能使用到该参数
		pageBean.setPageBarLength(pageQueryParam.getPageBarLength());// 同步分页方式下可能使用到该参数
		pageBean.setTotal(total);
	
		return pageBean;
	}

	/*
	public DataGrid sqlGetDataGrid(String sql, Object[] args, DataGridParam dataGridParam, Class targetClass,
			MapToObjectConverter mapToObjectConverter){
		long total = sqlGetCount(sql, args);
		if (getDB() == BaseDaoImpl.DB_MYSQL) {
			sql = sql + " LIMIT  " + (dataGridParam.getNum()-1) * dataGridParam.getRows() + " , " //
					+ dataGridParam.getRows() + " ";
		} else if (getDB() == BaseDaoImpl.DB_ORACLE) {
			long beginIndex = (dataGridParam.getNum()-1)*dataGridParam.getRows() + 1;//索引从1开始
			long endIndex = dataGridParam.getNum() * dataGridParam.getRows();			//
			sql = "SELECT * FROM "						//
					+ " ( "                          	//
					+ " SELECT A.*, ROWNUM RN "			//
					+ " FROM (" + sql + ") A  "			//
					+ " WHERE ROWNUM <= "+ endIndex + "  "//
					+ " )                   "			//
					+ " WHERE RN >= " + beginIndex + " ";//
		} else if (getDB() == BaseDaoImpl.DB_SQLSERVER) {
			// SELECT TOP @pagesize * FROM TABLE_NAME WHERE id not in (SELECT TOP @pagesize*(@page-1) id  FROM TABLE_NAME ORDER BY id) ORDER BY id
				String tempSql = sql.toUpperCase();
				int selectIndex = tempSql.indexOf("SELECT");
				int lastOrderIndex = tempSql.lastIndexOf("ORDER");
				int whereIndex = tempSql.indexOf("WHERE");
				//含有where条件
				String connectStr = " WHERE ";
				if(whereIndex != -1){
					connectStr = " AND ";
				}
				if(lastOrderIndex == -1){
					String sqlMain = sql.substring(selectIndex + 6);
					sql = " SELECT TOP " + dataGridParam.getRows() + " "+ sqlMain + 
							connectStr + " id NOT IN ( SELECT TOP " + dataGridParam.getRows()*(dataGridParam.getNum()-1) + " " + sqlMain +
							" ) ";   
						
				}else{
					String sqlMain = " " + sql.substring(selectIndex + 6, lastOrderIndex) + " ";
					String sqlOrder = " "+sql.substring(lastOrderIndex) + " ";
					sql = " SELECT TOP " + dataGridParam.getRows() + " "+ sqlMain + 
							connectStr + " id NOT IN ( SELECT TOP " + dataGridParam.getRows()*(dataGridParam.getNum()-1) + " " + sqlMain +
							sqlOrder + " ) " + sqlOrder;   
				}
		} else {
			throw new DaoException("Unknown DataBase!");
		}

		DataGrid dataGrid = new DataGrid();
		List rows = sqlQuery(sql, args, targetClass, mapToObjectConverter);
		dataGrid.setRows(rows);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	public List<Tree> sqlGetTree(String sql, Object[] args, TreeParam treeParam,
			MapToTreeConverter mapToTreeConverter){
		List ret = null;
		List<Map<String, Object>> list = null;
		if (mapToTreeConverter != null) {
			for (Map<String,Object> item : list) {
				Tree tree = mapToTreeConverter.convert(item);
				ret.add(tree);
			}
		} else {
			for (Object item : list) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(item, tree);
				ret.add(tree);
			}
		}
		
		return null;
	}
	
	public TreeGrid sqlGetTreeGrid(String sql, Object[] args, TreeGridParam treeGridParam, Class targetClass,
			MapToObjectConverter mapToObjectConverter){
		long total = sqlGetCount(sql, args);
		if (getDB() == BaseDaoImpl.DB_MYSQL) {
			sql = sql + " LIMIT  " + (treeGridParam.getNum()-1) * treeGridParam.getRows() + " , " //
					+ treeGridParam.getRows() + " ";
		} else if (getDB() == BaseDaoImpl.DB_ORACLE) {
			long beginIndex = (treeGridParam.getNum()-1)*treeGridParam.getRows() + 1;//索引从1开始
			long endIndex = treeGridParam.getNum() * treeGridParam.getRows();			//
			sql = "SELECT * FROM "						//
					+ " ( "                          	//
					+ " SELECT A.*, ROWNUM RN "			//
					+ " FROM (" + sql + ") A  "			//
					+ " WHERE ROWNUM <= "+ endIndex + "  "//
					+ " )                   "			//
					+ " WHERE RN >= " + beginIndex + " ";//
		} else if (getDB() == BaseDaoImpl.DB_SQLSERVER) {
			// SELECT TOP @pagesize * FROM TABLE_NAME WHERE id not in (SELECT TOP @pagesize*(@page-1) id  FROM TABLE_NAME ORDER BY id) ORDER BY id
				String tempSql = sql.toUpperCase();
				int selectIndex = tempSql.indexOf("SELECT");
				int lastOrderIndex = tempSql.lastIndexOf("ORDER");
				int whereIndex = tempSql.indexOf("WHERE");
				//含有where条件
				String connectStr = " WHERE ";
				if(whereIndex != -1){
					connectStr = " AND ";
				}
				if(lastOrderIndex == -1){
					String sqlMain = sql.substring(selectIndex + 6);
					sql = " SELECT TOP " + treeGridParam.getRows() + " "+ sqlMain + 
							connectStr + " id NOT IN ( SELECT TOP " + treeGridParam.getRows()*(treeGridParam.getNum()-1) + " " + sqlMain +
							" ) ";   
						
				}else{
					String sqlMain = " " + sql.substring(selectIndex + 6, lastOrderIndex) + " ";
					String sqlOrder = " "+sql.substring(lastOrderIndex) + " ";
					sql = " SELECT TOP " + treeGridParam.getRows() + " "+ sqlMain + 
							connectStr + " id NOT IN ( SELECT TOP " + treeGridParam.getRows()*(treeGridParam.getNum()-1) + " " + sqlMain +
							sqlOrder + " ) " + sqlOrder;   
				}
		} else {
			throw new DaoException("Unknown DataBase!");
		}

		TreeGrid treeGrid = new TreeGrid();
		List rows = sqlQuery(sql, args, targetClass, mapToObjectConverter);
		treeGrid.setRows(rows);
		treeGrid.setTotal(total);
		return treeGrid;
	}
	*/
	
	
	@Override
	public Object sqlGetUniqueResult(String sql, Object[] args,
			Class targetClass, MapToObjectConverter mapToObjectConverter) {
		List list = this.sqlQuery(sql, args, targetClass, mapToObjectConverter);
		if (list.size() > 1) {
			throw new DaoException("sqlGetUniqueResult 查询结果不唯一");
		} else if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void sqlExecute(String sql) {
		try {
			getTemplate().execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("sqlExecute 调用失败", e);
		}
	}

	@Override
	public long sqlGetCount(String sql, Object[] args) {
		try {
			List<Long> re = getTemplate().query(sql, args,
					new RowMapper<Long>() {
						@Override
						public Long mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							/** 1是第一个元素 */
							return rs.getLong(1);
						}
					});
			return re.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("sqlGetCount 调用失败", e);
		}
	}

	@Override
	public Object sqlGetDictById(String tabName, Long id, Class targetClass,
			MapToObjectConverter mapToObjectConverter) {
		try {
			String sql = " SELECT * FROM " + tabName + " table1 " + //
					" WHERE  table1.id = " + id; //
			return sqlGetUniqueResult(sql, null, targetClass,
					mapToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("sqlGetDictByIdToObject 调用失败", e);
		}
	}

	@Override
	public Object sqlGetDictByType(String tabName, int type, Class targetClass,
			MapToObjectConverter mapToObjectConverter) {
		try {
			String sql = " SELECT * FROM " + tabName + " table1 " + //
					" WHERE  table1.type = " + type; //
			return sqlGetUniqueResult(sql, null, targetClass,
					mapToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("sqlGetDictByTypeToObject 调用失败", e);
		}
	}

	@Override
	public Object sqlGetDictByCode(String tabName, String code,
			Class targetClass, MapToObjectConverter mapToObjectConverter) {
		try {
			String sql = " SELECT * FROM " + tabName + " table1 " + //
					" WHERE  table1.code = '" + code.trim() + "'"; //
			return sqlGetUniqueResult(sql, null, targetClass,
					mapToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("sqlGetDictByCodeToObject 调用失败", e);
		}
	}

	// /-------------------hibernate -----------------------------
	private Object getClassInstance(Class clazz) {
		if (clazz == null) {
			return null;
		}
		Object obj = null;
		try {
			obj = clazz.newInstance();
			/** E */
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Clazz参数错误", e);
		}
	}

	private Object procPo(Object po, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		po = this.getStdResult(po);
		if (targetClass == null && objectToObjectConverter == null) {
			return po;
		}
		try {
			if (po == null) {
				return null;
			} else {
				Object ret = po;
				if (po instanceof List) {
					List list = new LinkedList();
					for (Object item : (List) po) {
						if (targetClass != null) {
							Object targetObj = this
									.getClassInstance(targetClass);
							BeanUtils.copyProperties(item, targetObj);
							list.add(targetObj);
						} else if (objectToObjectConverter != null) {
							list.add(objectToObjectConverter.convert(item));
						}
					}
					ret = list;
				} else if (po instanceof Set) {
					Set set = new LinkedHashSet();
					for (Object item : (Set) po) {
						if (targetClass != null) {
							Object targetObj = this
									.getClassInstance(targetClass);
							BeanUtils.copyProperties(item, targetObj);
							set.add(targetObj);
						} else if (objectToObjectConverter != null) {
							set.add(objectToObjectConverter.convert(item));
						}
					}
					ret = set;
				} else if (po instanceof Map) {
					// TODO
					Map map = new LinkedHashMap();
					for (Map.Entry<String, Object> item : ((Map<String, Object>) po)
							.entrySet()) {
						if (targetClass != null) {
							Object targetObj = this
									.getClassInstance(targetClass);
							BeanUtils
									.copyProperties(item.getValue(), targetObj);
							map.put(item.getKey(), targetObj);
						} else if (objectToObjectConverter != null) {
							map.put(item.getKey(), objectToObjectConverter
									.convert(item.getValue()));
						}
					}
					ret = map;
				} else {
					if (targetClass != null) {
						Object targetObj = this.getClassInstance(targetClass);
						BeanUtils.copyProperties(po, targetObj);
						ret = targetObj;
					} else if (objectToObjectConverter != null) {
						ret = objectToObjectConverter.convert(po);
					}
				}
				return ret;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("处理持久化数据转换异常", e);
		}
	}

	@Override
	public Serializable save(T entity) {
		return this.getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) {
		this.getSession().update(entity);
	}

	@Override
	public void delete(T entity) {
		this.getSession().delete(entity);
	}

	/**
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 */

	@Override
	public Object getById(Long id, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			T entity = (T) getSession().get(clazz, id);
			return procPo(entity, targetClass, objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("通过id查询实体调用失败", e);
		}
	}

	@Override
	public List getByIds(List<Long> ids, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			List list = getSession().createCriteria(clazz)//
					.add(Restrictions.in("id", ids))//
					.list();
			return (List) procPo(list, targetClass, objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("通过ID列查询实体调用失败", e);
		}
	}

	@Override
	public Object getByName(String name, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			Object ret = getSession().createCriteria(clazz)//
					.add(Restrictions.eq("name", name))//
					.uniqueResult();
			return procPo(ret, targetClass, objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("通过name查询实体调用失败", e);
		}
	}

	@Override
	public List getByNames(List<String> names, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			List ret = (List) getSession().createCriteria(clazz) //
					.add(Restrictions.in("name", names)) //
					.list();
			return (List) procPo(ret, targetClass, objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("通过name列查询实体调用失败", e);
		}
	}

	@Override
	public List findAllExcept(List<Long> ids, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			List list = getSession().createCriteria(clazz) //
					.add(Restrictions.not(Restrictions.in("id", ids))).list();
			return (List) procPo(list, targetClass, objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("排除查询调用失败", e);
		}
	}

	@Override
	public List find(PageQueryParam pageQueryParam, List<Criterion> criterions,
			Order[] orders, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			Criteria criteria = getSession().createCriteria(clazz);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					criteria.add(criterion);
				}
			}
			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}
			if (pageQueryParam != null) {
				int num = pageQueryParam.getNum();
				int rows = pageQueryParam.getRows();
				criteria.setFirstResult((num - 1) * rows);
				criteria.setMaxResults(rows);
			}
			return (List) procPo(criteria.list(), targetClass,
					objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("查询方法调用失败", e);
		}
	}

	@Override
	public List findAll(Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			return find(null, null, null, targetClass, objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("查询所有调用失败", e);
		}
	}

	@Override
	public Long getCount(List<Criterion> criterions) {
		try {
			Criteria criteria = getSession().createCriteria(clazz);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					criteria.add(criterion);
				}
			}

			criteria.setProjection(Projections.rowCount());
			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("查询总记录调用失败", e);
		}
	}

	@Override
	public Long getCount(TreeGridParam treeGridParam, List<Criterion> criterions) {
		try {
			Criteria criteria = getSession().createCriteria(clazz);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					criteria.add(criterion);
				}
			}

			// TODO　该方法有待考虑
			/*
			 * if(treeGridParam != null){ criteria =
			 * treeGridParam.parseToCriteria(criteria); }
			 */

			criteria.uniqueResult();
			criteria.setProjection(Projections.rowCount());
			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("树表格的查询总记录调用失败", e);
		}
	}

	@Override
	public Long getCount(DataGridParam dataGridParam, List<Criterion> criterions) {
		try {
			Criteria criteria = getSession().createCriteria(clazz);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					criteria.add(criterion);
				}
			}

			/*
			 * if(dataGridParam != null){ criteria =
			 * dataGridParam.parseToCriteria(criteria); }
			 */

			criteria.setProjection(Projections.rowCount());
			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("数据表的查询总记录调用失败", e);
		}
	}

	@Override
	public PageBean pageQuery(PageQueryParam pageQueryParam,
			List<Criterion> criterions, Order[] orders, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			Criteria criteria = getSession().createCriteria(clazz);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					criteria.add(criterion);
				}
			}
			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}
			if (pageQueryParam != null) {
				int num = pageQueryParam.getNum();
				int rows = pageQueryParam.getRows();
				criteria.setFirstResult((num - 1) * rows);
				criteria.setMaxResults(rows);
			}

			PageBean pageBean = new PageBean();
			List rows = (List) procPo(criteria.list(), targetClass,
					objectToObjectConverter);

			pageBean.setRows(rows);
			pageBean.setQueryRows(pageQueryParam.getRows());// 同步分页方式下可能使用到该参数
			pageBean.setNum(pageQueryParam.getNum()); // 同步分页方式下可能使用到该参数
			pageBean.setPageBarLength(pageQueryParam.getPageBarLength());// 同步分页方式下可能使用到该参数
			pageBean.setTotal(getCount(criterions));
			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("分页查询调用失败", e);
		}
	}

	@Override
	public DataGrid getDataGrid(DataGridParam dataGridParam,
			List<Criterion> criterions, Order[] orders, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			Criteria criteria = getSession().createCriteria(clazz);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					criteria.add(criterion);
				}
			}
			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}
			if (dataGridParam != null) {
				int num = dataGridParam.getNum();
				int rows = dataGridParam.getRows();
				criteria.setFirstResult((num - 1) * rows);
				criteria.setMaxResults(rows);
			}
			DataGrid dataGrid = new DataGrid();
			List ret = (List) procPo(criteria.list(), targetClass,
					objectToObjectConverter);
			dataGrid.setRows(ret);
			dataGrid.setTotal(getCount(dataGridParam, criterions));
			return dataGrid;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("查询数据表格调用失败", e);
		}
	}

	@Override
	public List<Tree> getTree(TreeParam treeParam, TreeDepth treeDepth,
			List<Criterion> criterions, Order[] orders,
			ObjectToTreeConverter objectToTreeConverter) {
		try {
			Criteria criteria = getSession().createCriteria(clazz);
			if (treeParam.getId() == null) {
				criteria.add(Restrictions.isNull("parent"));
			} else {
				// this.getById(treeParam.getId(), null)
				criteria.add(Restrictions.eq("parent",
						this.getById(treeParam.getId(), null, null)));
			}
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					criteria.add(criterion);
				}
			}
			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}

			/**  */
			List ret = new LinkedList();
			List list = (List) this.getStdResult(criteria.list());
			if (objectToTreeConverter != null) {
				for (Object item : list) {
					Tree tree = objectToTreeConverter.convert(item);
					ret.add(tree);
				}
			} else {
				for (Object item : list) {
					Tree tree = new Tree();
					BeanUtils.copyProperties(item, tree);
					ret.add(tree);
				}
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("查询树(tree)调用失败", e);
		}
	}

	@Override
	public TreeGrid getTreeGrid(TreeGridParam treeGridParam,
			List<Criterion> criterions, Order[] orders, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			Criteria criteria = getSession().createCriteria(clazz);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					criteria.add(criterion);
				}
			}
			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}
			if (treeGridParam != null) {
				int num = treeGridParam.getNum();
				int rows = treeGridParam.getRows();
				criteria.setFirstResult((num - 1) * rows);
				criteria.setMaxResults(rows);
			}
			TreeGrid treeGrid = new TreeGrid();
			List rows = (List) procPo(criteria.list(), targetClass,
					objectToObjectConverter);
			treeGrid.setRows(rows);
			treeGrid.setTotal(getCount(treeGridParam, criterions));
			return treeGrid;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("查询树表单(TreeGrid)调用失败", e);
		}
	}

	@Override
	public Object getUniqueResult(List<Criterion> criterions,
			Class targetClass, ObjectToObjectConverter objectToObjectConverter) {
		Criteria criteria = getSession().createCriteria(clazz);
		if (criterions != null) {
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
		}
		try {
			Object result = criteria.uniqueResult();
			/** E */
			return procPo(result, targetClass, objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("getUniqueResult 查询结果不唯一", e);
		}
	}

	@Override
	public Object getDictById(Long id, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			return getById(id, targetClass, objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("getDictById 调用失败", e);
		}
	}

	@Override
	public Object getDictByType(int type, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			List<Criterion> criterions = new LinkedList<Criterion>();
			criterions.add(Restrictions.eq("type", type));
			return getUniqueResult(criterions, targetClass,
					objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("getDictByType 调用失败", e);
		}
	}

	@Override
	public Object getDictByCode(String code, Class targetClass,
			ObjectToObjectConverter objectToObjectConverter) {
		try {
			List<Criterion> criterions = new LinkedList<Criterion>();
			criterions.add(Restrictions.eq("code", code));
			return getUniqueResult(criterions, targetClass,
					objectToObjectConverter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("getDictByCode 调用失败", e);
		}
	}
}
