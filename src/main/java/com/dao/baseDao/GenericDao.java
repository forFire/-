package com.dao.baseDao;

import java.io.Reader;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.jdbc.Work;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.util.Page;

/**
 * @author zf
 * @param <PK>
 */
public class GenericDao<T, PK extends Serializable> implements IGenericDao<T, PK> {

	@Resource(name = "baseDao")
	private HibernateDaoSupport baseDao;

	@SuppressWarnings("unchecked")
	public PK save(T object) {
		return (PK) getBaseDao().getHibernateTemplate().save(object);
	}

	public void saveOrUpdate(T object) {
		getBaseDao().getHibernateTemplate().saveOrUpdate(object);
	}

	public void saveOrUpdate(List<T> ts) {
		for (T t : ts) {
			try {
				saveOrUpdate(t);
				this.flushSession();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(T entity) {
		getBaseDao().getHibernateTemplate().refresh(entity);
		getBaseDao().getHibernateTemplate().delete(entity);
	}

	public T get(Class<T> entityClass, PK id) {
		return getBaseDao().getHibernateTemplate().get(entityClass, id);
	}

	public T load(Class<T> entityClass, PK id) {
		return getBaseDao().getHibernateTemplate().load(entityClass, id);
	}

	public T take(Class<T> entityClass, PK id) {
		T entity = getBaseDao().getHibernateTemplate().get(entityClass, id);
		getBaseDao().getHibernateTemplate().evict(entity);
		return entity;
	}

	public List<T> loadAll(Class<T> objectClass) {
		return null;
	}

	public List<?> query(String hql) {
		return getBaseDao().getHibernateTemplate().find(hql);
	}

	public List<?> query(String hql, Object... parameters) {
		return getBaseDao().getHibernateTemplate().find(hql, parameters);
	}

	public List<?> find(String hql, final String[] paramNames, final Object[] values) {
		return getBaseDao().getHibernateTemplate().findByNamedParam(hql, paramNames, values);
	}

	@SuppressWarnings("unchecked")
	public List<?> queryLock(final String queryString, final String alias, final Object... values) throws DataAccessException {
		return getBaseDao().getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query queryObject = session.createQuery(queryString).setLockMode(alias, LockMode.PESSIMISTIC_WRITE);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	public List queryList(String hql, Object... parameters) {
		return getBaseDao().getHibernateTemplate().find(hql, parameters);
	}

	public List query(String s, String[] as, Object[] aobj) {
		return null;
	}

	public List queryByCriteria(DetachedCriteria detachedcriteria) {
		return null;
	}

	/**
	 * @author songxl
	 * @param sql:查询的语句
	 * @description 原生sql查询
	 */
	public List queryListBySQL(final String sql) {
		return this.queryListBySQL(sql, null);
	}

	/**
	 * @author songxl
	 * @param sql:查询的语句
	 * @param values:查询参数值数组
	 * @description 原生sql查询，支持占位符传入参数
	 */
	@SuppressWarnings("unchecked")
	public List queryListBySQL(final String sql, final Object... values) {
		return (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						createSQLQuery.setParameter(i, values[i]);
					}
				}
				return createSQLQuery.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public ResultSet queryResultSetBySQL(final String sql, final Object... parameters) {
		return (ResultSet) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final ResultSet[] resultSet = new ResultSet[] { null };
				session.doWork(new Work() {
					public void execute(Connection connection) throws SQLException {
						PreparedStatement statement = connection.prepareStatement(sql);
						for (int i = 0; i < parameters.length; i++) {
							Object parameter = parameters[i];
							int index = i + 1;
							if (parameter instanceof String)
								statement.setString(index, (String) parameter);
							else if (parameter instanceof Date)
								statement.setDate(index, new java.sql.Date(((Date) parameter).getTime()));
							else if (parameter instanceof Integer)
								statement.setInt(index, (Integer) parameter);
							else if (parameter instanceof Long)
								statement.setLong(index, (Long) parameter);
							else
								statement.setObject(index, parameter);
						}
						resultSet[0] = statement.executeQuery();
						statement.close();
					}
				});
				return resultSet[0];
			}
		});
	}

	/**
	 * 执行原生的sql语句，用于更新和删除
	 * 
	 * @param sql
	 * @param parameters
	 */
	@SuppressWarnings("unchecked")
	public void executeNativeSQL(final String sql, final Object... parameters) {
		getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.doWork(new Work() {
					public void execute(Connection connection) throws SQLException {
						PreparedStatement statement = connection.prepareStatement(sql);
						for (int i = 0; i < parameters.length; i++) {
							Object parameter = parameters[i];
							int index = i + 1;
							if (parameter instanceof String)
								statement.setString(index, (String) parameter);
							else if (parameter instanceof Date)
								statement.setTimestamp(index, new Timestamp(((Date) parameter).getTime()));
							else if (parameter instanceof Integer)
								statement.setInt(index, (Integer) parameter);
							else if (parameter instanceof Long)
								statement.setLong(index, (Long) parameter);
							else
								statement.setObject(index, parameter);
						}
						statement.executeUpdate();
						statement.close();
					}
				});
				// session.close();
				return null;
			}
		});
	}

	/**
	 * 执行原生的sql语句，用于更新和删除
	 */
	@SuppressWarnings("unchecked")
	public void executeNativeSQL(final String sql) {
		getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final ResultSet[] resultSet = new ResultSet[] { null };
				session.doWork(new Work() {
					public void execute(Connection connection) throws SQLException {
						Statement statement = connection.createStatement();
						statement.execute(sql);
						statement.close();
					}
				});
				return null;
			}
		});
	}

	/**
	 * 执行hql的删除
	 */
	public int executeHQL(final String hql) {
		// return getBaseDao().getHibernateTemplate().bulkUpdate(hql);
		return getBaseDao().getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 执行hql的删除，可以传参数
	 */
	public int executeHQL(final String hql, final Object... parameters) {
		// return getBaseDao().getHibernateTemplate().bulkUpdate(hql,
		// parameters);
		return getBaseDao().getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						query.setParameter(i, parameters[i]);
					}
				}
				return query.executeUpdate();
			}
		});
	}

	public void detachEntity(Object entity) {
		getBaseDao().getHibernateTemplate().evict(entity);
	}

	public void detachEntities(List entities) {
		for (Object entity : entities)
			getBaseDao().getHibernateTemplate().evict(entity);
	}

	public List<T> convert(List entityList) {
		if (entityList == null || entityList.size() == 0)
			return null;
		List<T> list = new ArrayList<T>();
		for (Object o : entityList)
			list.add((T) o);
		return list;
	}

	/**
	 * 分页查询方法 songxl 2011.05.03
	 * 
	 * @param hql:查询的语句
	 * @param pageObject:分页查询对象
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public Page queryResultByPage(final String hql, Page pageObject) throws HibernateException {
		// 截取hql中从from开始的字符串，以便得到查询后记录的总条数
		int formIndex = hql.toLowerCase().indexOf("from");
		String countHql = hql.substring(formIndex);
		String countNumHql = "select count(*) " + countHql; // 得到查询总条数的语句
		int rowCount = getQueryCount(countNumHql); // 得到总行数
//		pageObject.init(rowCount);// 初始化分页类
		pageObject.setTotal(rowCount);
		final int firstResult = pageObject.getFirst();
		final int maxResults = pageObject.getRows();

		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				List list = query.list();
				return list;
			}
		});

		pageObject.setResult(queryList);// 设置查询结果集
		return pageObject;
	}

	/**
	 * 分页查询方法 songxl 2011.05.03
	 * 
	 * @param hql:查询的语句
	 * @param pageObject:分页查询对象
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryResultByPage(final String hql, final Integer start, final Integer size) throws HibernateException {
		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(start);
				query.setMaxResults(size);
				List list = query.list();
				return list;
			}
		});
		return queryList;
	}

	public Page queryResultByPage(final String hql, Page pageObject, final Object... values) {
		return queryResultByPage(hql, values, pageObject);
	}

	public Page queryResultByPage(final String hql, final Map<String, Object> params, Page pageObject) throws HibernateException {
		// 截取hql中从from开始的字符串，以便得到查询后记录的总条数
		int formIndex = hql.toLowerCase().indexOf("from");
		String countHql = hql.substring(formIndex);
		String countNumHql = "select count(*) " + countHql; // 得到查询总条数的语句
		int rowCount = getQueryCount(countNumHql, params); // 得到总行数
//		pageObject.init(rowCount);// 初始化分页类
		pageObject.setTotal(rowCount);
		final int firstResult = pageObject.getFirst();
		final int maxResults = pageObject.getPage();

		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (params != null) {
					Set<String> keySet = params.keySet();
					for (String key : keySet) {
						Object object = params.get(key);
						if (object instanceof Collection) {
							query.setParameterList(key, (Collection) object);
						} else if (object instanceof Object[]) {
							query.setParameterList(key, (Object[]) object);
						} else {
							query.setParameter(key, object);
						}
					}
				}
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				List list = query.list();
				return list;
			}
		});

		pageObject.setResult(queryList);// 设置查询结果集
		return pageObject;
	}

	/**
	 * 分页查询方法 songxl 2011.05.03
	 * 
	 * @param hql:查询的语句
	 * @param values:查询参数值数组
	 * @param pageObject:分页查询对象
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public Page queryResultByPage(final String hql, final Object[] values, Page pageObject) throws HibernateException {
		// 截取hql中从from开始的字符串，以便得到查询后记录的总条数
		int formIndex = hql.toLowerCase().indexOf("from");
		String countHql = hql.substring(formIndex);
		String countNumHql = "select count(*) " + countHql; // 得到查询总条数的语句
		int rowCount = getQueryCount(countNumHql, values); // 得到总行数
//		pageObject.init(rowCount);// 初始化分页类
		pageObject.setTotal(rowCount);
		final int firstResult = pageObject.getFirst();
		final int maxResults = pageObject.getPage();

		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				List list = query.list();
				return list;
			}
		});

		pageObject.setResult(queryList);// 设置查询结果集
		return pageObject;
	}

	private int getQueryCount(final String countNumHql, final Map<String, Object> params) {
		Integer result = getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(countNumHql);
				if (params != null) {
					Set<String> keySet = params.keySet();
					for (String key : keySet) {
						Object object = params.get(key);
						if (object instanceof Collection) {
							query.setParameterList(key, (Collection) object);
						} else if (object instanceof Object[]) {
							query.setParameterList(key, (Object[]) object);
						} else {
							query.setParameter(key, object);
						}
					}
				}
				List list = query.list();
				if (list != null && list.size() > 0) {
					return ((Long) list.get(0)).intValue();
				}
				return 0;
			}
		});
		return result;
	}

	// 计算查询结果的总共的记录数(带参数)
	private int getQueryCount(String queryString, Object[] values) throws HibernateException {
		List list = getBaseDao().getHibernateTemplate().find(queryString, values);
		if (list != null && list.size() > 0) {
			return ((Long) list.get(0)).intValue();
		}
		return 0;
	}

	private int getQueryCount(String queryString) throws HibernateException {
		List list = getBaseDao().getHibernateTemplate().find(queryString);
		if (list != null && list.size() > 0) {
			return ((Long) list.get(0)).intValue();
		}
		return 0;
	}

	/**
	 * 扩展
	 * 
	 * @param <C>
	 * @param id
	 * @param c
	 * @return
	 */
	public <C> C get_(Serializable id, Class<C> c) {
		return getBaseDao().getHibernateTemplate().get(c, id);
	}
	
	

	public void persist_(Object obj) {
		getBaseDao().getHibernateTemplate().persist(obj);
	}

	public void persistAll_(Collection<T> objs) {
		int i = 1;
		for (Object object : objs) {
			persist_(object);
			i++;
			if (i % 10 == 0) {
				getBaseDao().getHibernateTemplate().execute(new HibernateCallback<T>() {
					public T doInHibernate(Session session) throws HibernateException, SQLException {
						session.flush();
						return null;
					}
				});
			}
		}
	}

	public void del_(Object obj) {
		getBaseDao().getHibernateTemplate().delete(obj);
	}

	public void update_(Object obj) {
		getBaseDao().getHibernateTemplate().update(obj);
	}

	public void merge_(Object obj) {
		getBaseDao().getHibernateTemplate().merge(obj);
	}

	public void mergeAll_(Collection<?> objs) {
		for (Object object : objs) {
			merge_(object);
		}
	}

	public void mergeObjects_(List<T> objs) {
		for (Object object : objs) {
			merge_(object);
		}
	}

	public void deleteObjects(List<T> objs) {
		for (T t : objs) {
			this.delete(t);
		}
	}

	@SuppressWarnings("unchecked")
	public List executeSql(final String sql) {
		List execute = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				return createSQLQuery.list();
			}
		});
		return execute;
	}

	@SuppressWarnings("unchecked")
	public List executeSql(final String sql, final Object... parameters) {
		List execute = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				if (parameters != null && parameters.length > 0) {
					for (int i = 0; i < parameters.length; i++) {
						Object parameter = parameters[i];
						int index = i;
						if (parameter instanceof String)
							createSQLQuery.setString(index, (String) parameter);
						else if (parameter instanceof Date)
							createSQLQuery.setDate(index, new java.sql.Date(((Date) parameter).getTime()));
						else if (parameter instanceof Integer)
							createSQLQuery.setInteger(index, (Integer) parameter);
						else if (parameter instanceof Long)
							createSQLQuery.setLong(index, (Long) parameter);
						else
							createSQLQuery.setParameter(index, parameter);
					}
				}
				return createSQLQuery.list();
			}
		});
		return execute;
	}

	public int deleteObjectsByIdAndIdName(Class c, String idName, String ids) {
		return this.executeHQL(" delete from " + c.getSimpleName() + " where " + idName + " in (" + ids + ")");
	}

	public void flushSession() {
		getBaseDao().getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException, SQLException {
				session.flush();
				return null;
			}
		});
	}

	/**
	 * songxl 普通sql查询分页，带查询条件
	 * 
	 * @param sql
	 * @param pageObject
	 * @param parameters
	 *            查询条件
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public Page queryResultByPageSql(final String sql, Page pageObject, final Object... parameters) throws HibernateException {
		// 截取hql中从from开始的字符串，以便得到查询后记录的总条数
		int formIndex = sql.toLowerCase().indexOf("from");
		String countSql = sql.substring(formIndex);

		String countNumSql = "select count(1) " + countSql; // 得到查询总条数的语句

		int rowCount = this.getQueryCountSql(countNumSql, parameters); // 得到总行数
//		pageObject.init(rowCount);// 初始化分页类
		pageObject.setTotal(rowCount);
		final int firstResult = pageObject.getFirst();
		final int maxResults = pageObject.getPage();

		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				if (parameters != null && parameters.length > 0) {
					for (int i = 0; i < parameters.length; i++) {
						Object parameter = parameters[i];
						int index = i;
						if (parameter instanceof String)
							createSQLQuery.setString(index, (String) parameter);
						else if (parameter instanceof Date)
							createSQLQuery.setDate(index, new java.sql.Date(((Date) parameter).getTime()));
						else if (parameter instanceof Integer)
							createSQLQuery.setInteger(index, (Integer) parameter);
						else if (parameter instanceof Long)
							createSQLQuery.setLong(index, (Long) parameter);
						else
							createSQLQuery.setParameter(index, parameter);
					}
				}
				createSQLQuery.setFirstResult(firstResult);
				createSQLQuery.setMaxResults(maxResults);
				List list = createSQLQuery.list();
				return list;
			}
		});
		pageObject.setResult(queryList);// 设置查询结果集
		return pageObject;
	}

	/**
	 * songxl 普通sql查询分页，带查询条件
	 * 
	 * @param sql
	 * @param pageObject
	 * @param parameters
	 *            查询条件
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public Page queryResultByPageSql(final int rowCount, final String sql, Page pageObject, final Object... parameters) throws HibernateException {
		// 截取hql中从from开始的字符串，以便得到查询后记录的总条数
		int formIndex = sql.toLowerCase().indexOf("from");
		String countSql = sql.substring(formIndex);

		String countNumSql = "select count(1) " + countSql; // 得到查询总条数的语句

//		 int rowCount = this.getQueryCountSql(countNumSql,parameters); //得到总行数
//		pageObject.init(rowCount);// 初始化分页类
		pageObject.setTotal(rowCount);
		
		final int firstResult = pageObject.getFirst();
		final int maxResults = pageObject.getPage();

		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				if (parameters != null && parameters.length > 0) {
					for (int i = 0; i < parameters.length; i++) {
						Object parameter = parameters[i];
						int index = i;
						if (parameter instanceof String)
							createSQLQuery.setString(index, (String) parameter);
						else if (parameter instanceof Date)
							createSQLQuery.setDate(index, new java.sql.Date(((Date) parameter).getTime()));
						else if (parameter instanceof Integer)
							createSQLQuery.setInteger(index, (Integer) parameter);
						else if (parameter instanceof Long)
							createSQLQuery.setLong(index, (Long) parameter);
						else
							createSQLQuery.setParameter(index, parameter);
					}
				}
				createSQLQuery.setFirstResult(firstResult);
				createSQLQuery.setMaxResults(maxResults);
				List list = createSQLQuery.list();
				return list;
			}
		});
		pageObject.setResult(queryList);// 设置查询结果集
		return pageObject;
	}

	/**
	 * 普通sql查询分页
	 * 
	 * @param sql
	 * @param pageObject
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public Page queryResultByPageSql(final int rowCount, final String sql, Page pageObject) throws HibernateException {
		// 截取hql中从from开始的字符串，以便得到查询后记录的总条数
		int formIndex = sql.toLowerCase().indexOf("from");
		String countSql = sql.substring(formIndex);

		String countNumSql = "select count(1) " + countSql; // 得到查询总条数的语句
		// System.out.println("countNumSql ="+countNumSql);

		// int rowCount = this.getQueryCountSql(countNumSql); //得到总行数
//		pageObject.init(rowCount);// 初始化分页类
		pageObject.setTotal(rowCount);
		final int firstResult = pageObject.getFirst();
		final int maxResults = pageObject.getPage();

		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setFirstResult(firstResult);
				createSQLQuery.setMaxResults(maxResults);
				List list = createSQLQuery.list();
				return list;
			}
		});
		pageObject.setResult(queryList);// 设置查询结果集
		return pageObject;
	}

	/**
	 * 普通sql查询分页
	 * 
	 * @param sql
	 * @param pageObject
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public Page queryResultByPageSql(final String sql, Page pageObject) throws HibernateException {
		// 截取hql中从from开始的字符串，以便得到查询后记录的总条数
		int formIndex = sql.toLowerCase().indexOf("from");
		String countSql = sql.substring(formIndex);

		String countNumSql = "select count(1) " + countSql; // 得到查询总条数的语句
		// System.out.println("countNumSql ="+countNumSql);

		int rowCount = this.getQueryCountSql(countNumSql); // 得到总行数
//		pageObject.init(rowCount);// 初始化分页类
		pageObject.setTotal(Long.valueOf(countNumSql));
		final int firstResult = pageObject.getFirst();
		final int maxResults = pageObject.getPage();

		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setFirstResult(firstResult);
				createSQLQuery.setMaxResults(maxResults);
				List list = createSQLQuery.list();
				return list;
			}
		});
		pageObject.setResult(queryList);// 设置查询结果集
		return pageObject;
	}

	/**
	 * sql查询分页 (传入用于查询总数的sql)
	 * 
	 * @param sql
	 * @param countSql
	 * @param pageObject
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public Page queryResultByPageSql(final String sql, final String countSql, Page pageObject) throws HibernateException {
		// 查询总条数的语句
		String countNumSql = "select count(1) " + countSql;
		// 得到总行数
		int rowCount = this.getQueryCountSql(countNumSql);
//		pageObject.init(rowCount);// 初始化分页类
		pageObject.setTotal(Long.valueOf(countNumSql));
		final int firstResult = pageObject.getFirst();
		final int maxResults = pageObject.getPage();

		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setFirstResult(firstResult);
				createSQLQuery.setMaxResults(maxResults);
				List list = createSQLQuery.list();
				return list;
			}
		});
		pageObject.setResult(queryList);// 设置查询结果集
		return pageObject;
	}

	public List queryResultByPageSql(final String sql, final int firstResult, final int maxResults) throws HibernateException {
		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setFirstResult(firstResult);
				createSQLQuery.setMaxResults(maxResults);
				List list = createSQLQuery.list();
				return list;
			}
		});
		return queryList;
	}

	// 适用于有group by 的sql
	@SuppressWarnings("unchecked")
	public PageObject queryResultByPageSqlContainsGroupBy(final String sql, PageObject pageObject) throws HibernateException {
		// 截取hql中从from开始的字符串，以便得到查询后记录的总条数
		/*
		 * int formIndex = sql.toLowerCase().indexOf("from"); String countSql =
		 * sql.substring(formIndex);
		 */

		String countNumSql = "select count(1) from (" + sql + ")"; // 得到查询总条数的语句
		// System.out.println("countNumSql ="+countNumSql);

		int rowCount = this.getQueryCountSql(countNumSql); // 得到总行数
		pageObject.init(rowCount);// 初始化分页类

		final int firstResult = pageObject.getFirstResult();
		final int maxResults = pageObject.getPageSize();

		List queryList = (List) getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setFirstResult(firstResult);
				createSQLQuery.setMaxResults(maxResults);
				List list = createSQLQuery.list();
				/*
				 * Query query = session.createQuery(sql);
				 * query.setFirstResult(firstResult);
				 * query.setMaxResults(maxResults);
				 */
				// List list = query.list();
				return list;
			}
		});
		pageObject.setResultList(queryList);// 设置查询结果集
		return pageObject;
	}

	private int getQueryCountSql(String queryString, Object... parameters) throws HibernateException {
		List list = this.queryListBySQL(queryString, parameters);
		if (list != null && list.size() > 0) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

	private int getQueryCountSql(String queryString) throws HibernateException {
		List list = this.queryListBySQL(queryString);
		if (list != null && list.size() > 0) {
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

	public void upMenu(Class cc, String menucodeid, String postidnext) {
		String sql = "update " + cc.getSimpleName() + " t set t.position = '" + postidnext + "' where t.menuid ='" + menucodeid + "'";
		// System.out.println("sql ="+sql);
		this.executeHQL(sql);
	}

	public String clobToString(Clob c) {
		StringBuffer sb = new StringBuffer(1024);
		Reader instream = null;
		try {
			instream = c.getCharacterStream();
			char[] buffer = new char[(int) c.length()];
			@SuppressWarnings("unused")
			int length = 0;
			while ((length = instream.read(buffer)) != -1) {
				sb.append(buffer);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (instream != null)
					instream.close();
			} catch (Exception dx) {
				instream = null;
			}
			return sb.toString();
		}
	}

	/**
	 * 执行无返回值的存储过程
	 */
	@SuppressWarnings("unchecked")
	public void executeProcedure(final String procedureName, final Object... parameters) {
		getBaseDao().getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				final ResultSet[] resultSet = new ResultSet[] { null };
				session.doWork(new Work() {
					public void execute(Connection connection) throws SQLException {
						String call = "{ call " + procedureName + "(";
						if (parameters == null || parameters.length == 0)
							call = call + ")}";
						else
							call = call + StringUtils.repeat(",?", parameters.length).substring(1) + ")}";
						CallableStatement statement = connection.prepareCall(call);
						if (parameters != null && parameters.length > 0) {
							for (int i = 0; i < parameters.length; i++) {
								Object parameter = parameters[i];
								int index = i + 1;
								if (parameter instanceof String)
									statement.setString(index, (String) parameter);
								else if (parameter instanceof Date) {
									if (parameter instanceof java.sql.Timestamp) {
										statement.setTimestamp(index, new java.sql.Timestamp(((Date) parameter).getTime()));
									} else {
										statement.setDate(index, new java.sql.Date(((Date) parameter).getTime()));
									}
								} else if (parameter instanceof Integer)
									statement.setInt(index, (Integer) parameter);
								else if (parameter instanceof Long)
									statement.setLong(index, (Long) parameter);
								else
									statement.setObject(index, parameter);
							}
						}
						statement.execute();
						statement.close();
					}
				});
				return null;
			}
		});
	}

	public Date getDbDate() {
		@SuppressWarnings("unchecked")
		List<Object> list = queryListBySQL("SELECT NOW()");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse((String) list.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}
		return date;
	}

	@Override
	public HibernateDaoSupport getBaseDao() {
		return baseDao;
	}



}
