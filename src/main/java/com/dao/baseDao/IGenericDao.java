package com.dao.baseDao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.util.Page;

/**
 * @author zf
 * @param <T>
 * @param <PK>
 */
public interface IGenericDao<T, PK extends Serializable> {

	public PK save(T object);

	public void saveOrUpdate(T object);

	public void saveOrUpdate(List<T> list);

	public void delete(T obj);

	public void deleteObjects(List<T> objs);

	public T load(Class<T> entityClass, PK id);

	public List<T> loadAll(Class<T> objectClass);

	public List query(String s);

	public List query(String s, Object... parameters);

	public List query(String s, String as[], Object aobj[]);

	public List queryByCriteria(DetachedCriteria detachedcriteria);

	public List queryListBySQL(String sql);

	public List queryListBySQL(String sql, Object[] values);

	public Page queryResultByPage(String hql, Page pageObject);

	public Page queryResultByPage(String hql, Object[] values, Page pageObject);

	public ResultSet queryResultSetBySQL(String sql, Object... parameters);

	public int executeHQL(String hql);

	public int executeHQL(String hql, Object... parameters);

	public void executeNativeSQL(final String sql, final Object... parameters);

	public void executeNativeSQL(final String sql);

	public HibernateDaoSupport getBaseDao();

	public void detachEntity(Object entity);

	public void detachEntities(List entity);

	public List executeSql(final String sql);

	public List executeSql(final String sql, final Object... parameters);

	public <C> C get_(Serializable id, Class<C> c);

	public void persist_(Object obj);

	public void del_(Object obj);

	public void update_(Object obj);

	public void merge_(Object obj);

	public void persistAll_(Collection<T> objs);

	public Date getDbDate();
}
