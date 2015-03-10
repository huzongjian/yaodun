package com.springmvc.relationdrug.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.BasicDrugCheckDao;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.enums.DrugCateGoryEnum;

/**
 * 
 * @ClassName: BasicDrugCheckDaoImpl
 * @Description: TODO
 * @date 2013-7-15 5:02:49
 * 
 */
@Transactional
@Repository
public class BasicDrugCheckDaoImpl extends BaseDaoImpl<BasicDrugCheck, Long>
		implements BasicDrugCheckDao {

	@Resource
	public SessionFactory sessionFactory;

	public BasicDrugCheck findByid(Long id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BasicDrugCheck where id = ?");
		BasicDrugCheck basicDrugCheck = (BasicDrugCheck) this.getSession()
				.createQuery(hql.toString()).setLong(0, id).uniqueResult();
		if (basicDrugCheck != null) {
			return basicDrugCheck;
		} else {
			return null;
		}
	}

	public BasicDrugCheck findBydrugCode(String drugCode) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BasicDrugCheck where drugCode = ?");
		BasicDrugCheck basicDrugCheck = (BasicDrugCheck) this.getSession()
				.createQuery(hql.toString()).setString(0, drugCode)
				.uniqueResult();
		if (basicDrugCheck != null) {
			return basicDrugCheck;
		} else {
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	public List<BasicDrugCheck> findByTypeAndName(DrugCateGoryEnum type,
			String drugname) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BasicDrugCheck where drugCategory = ? and drugName is not null and (drugName like ? or upper(symbol) like ?) ");
		return this.getSession().createQuery(hql.toString()).setMaxResults(30)
				.setParameter(0, type).setString(1, "%" + drugname + "%")
				.setString(2, "%" + drugname.toUpperCase() + "%").list();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<BasicDrugCheck> findDrugChecksList(Map argMap) {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT NEW BasicDrugCheck(drugCode,drugForm,drugName,ruleUnit,specification) FROM BasicDrugCheck where drugCategory=1");
		org.hibernate.Query query = this.getSession().createQuery(
				hql.toString());
		query.setFirstResult((Integer) argMap.get("pageNum"));
		query.setMaxResults((Integer) argMap.get("pageSize"));
		List<BasicDrugCheck> basicDrugList = query.list();
		if (basicDrugList.isEmpty()) {
			return null;
		} else {
			return basicDrugList;
		}

	}

	public int BasicDrugCheckAllSize() {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT NEW BasicDrugCheck(drugCode,drugForm,drugName,ruleUnit,specification) FROM BasicDrugCheck where drugCategory=1");
		org.hibernate.Query query = this.getSession().createQuery(
				hql.toString());
		Integer basicDrugList = (Integer) query.list().size();
		return basicDrugList;

	}

	@SuppressWarnings("unchecked")
	public List<BasicDrugCheck> findByNameAndSymbol(String drugname) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BasicDrugCheck where drugName is not null and (drugName like ? or upper(symbol) like ?)");
		return this.getSession().createQuery(hql.toString())
				.setString(0, "%" + drugname + "%")
				.setString(1, "%" + drugname.toUpperCase() + "%")
				.setMaxResults(10).list();
	}

	public BasicDrugCheck findByName(String drugname) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BasicDrugCheck where drugName is not null and drugName = ?");
		return (BasicDrugCheck) this.getSession().createQuery(hql.toString())
				.setMaxResults(30).setString(0, drugname).uniqueResult();
	}

}
