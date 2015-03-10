package com.springmvc.base.service.impl;

import java.io.Serializable;
import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.BaseService;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements
		BaseService<T, ID> {

	public abstract BaseDao<T, ID> getDao();

	public List<T> findAll() {
		return getDao().findAll();
	}

	public List<T> findAll(int page, int pageSize) {
		return getDao().findAll(page, pageSize);
	}

	public long save(T entity) {
		return getDao().save(entity);
	}

	public void delete(T entity) {
		getDao().delete(entity);

	}

	public void deleteByProperty(String propertyName, Object value) {
		getDao().deleteByProperty(propertyName, value);

	}

	public List<T> findByProperty(String propertyName, Object value) {
		return getDao().findByProperty(propertyName, value);
	}

	public List<T> findByPropertys(String[] propertyNames, Object[] values) {
		return getDao().findByPropertys(propertyNames, values);
	}

	public List<T> findByPropertys(String[] propertyNames, Object[] values,
			int page, int pageSize) {
		return getDao().findByPropertys(propertyNames, values, page, pageSize);
	}

	public List<T> findByProperty(String propertyName, Object value, int page,
			int pageSize) {
		return getDao().findByProperty(propertyName, value, page, pageSize);
	}

	public int countAll() {
		return getDao().countAll();
	}

	public int countByProperty(String propertyName, Object value) {
		return getDao().countByProperty(propertyName, value);
	}

	public int countByPropertys(String[] propertyNames, Object[] values) {
		return getDao().countByPropertys(propertyNames, values);
	}

	public void saveOrUpdate(T entity) {
		getDao().saveOrUpdate(entity);

	}

	public void update(T entity) {
		getDao().update(entity);
	}

	public List<T> findAndOrderByProperty(int firstResult, int fetchSize,
			String propertyName, boolean isSequence) {
		return getDao().findAndOrderByProperty(firstResult, fetchSize,
				propertyName, isSequence);
	}

	public List<T> findAllAndOrderByProperty(String propertyName,
			boolean isSequence) {
		return getDao().findAllAndOrderByProperty(propertyName, isSequence);
	}

	public void flush() {
		getDao().flush();
	}

	public void clear() {
		getDao().clear();
	}

	public void delete(ID id) {
		getDao().delete(id);
	}

	public T get(ID id) {
		return getDao().get(id);
	}

}
