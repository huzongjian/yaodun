package com.springmvc.relationdrug.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.BasicDrugDataDao;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.domain.BasicDrugData;

/**
 * 
 * @ClassName: BasicDrugCheckDaoImpl
 * @Description: TODO
 * @date 2013-7-15 5:02:49
 * 
 */
@Transactional
@Repository
public class BasicDrugDataDaoImpl extends BaseDaoImpl<BasicDrugData, Long>
		implements BasicDrugDataDao {

	@Resource
	public SessionFactory sessionFactory;

	public BasicDrugData findByid(Long id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BasicDrugData where id = ?");
		BasicDrugData basicDrugData = (BasicDrugData) this.getSession()
				.createQuery(hql.toString()).setLong(0, id).uniqueResult();
		if (basicDrugData != null) {
			return basicDrugData;
		} else {
			return null;
		}
	}


	
	

	public BasicDrugData findByName(String name) {
		
			StringBuilder hql = new StringBuilder();
			hql.append(" from BasicDrugData where drugName is not null and drugName = ?");
			return (BasicDrugData) this.getSession().createQuery(hql.toString())
					.setMaxResults(30).setString(0, name).uniqueResult();
		
	}


	@SuppressWarnings("unchecked")
	public List<BasicDrugData> findByNameAndSymbol(String name) {
			StringBuilder hql = new StringBuilder();
			hql.append(" from BasicDrugData where drugName is not null and (drugName like ? or upper(symbol) like ?)");
			return this.getSession().createQuery(hql.toString())
					.setString(0, "%" + name + "%")
					.setString(1, "%" + name.toUpperCase() + "%")
					.setMaxResults(10).list();
		
	}

}
