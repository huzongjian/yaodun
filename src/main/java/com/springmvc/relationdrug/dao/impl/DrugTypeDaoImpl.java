package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.DrugTypeDao;
import com.springmvc.relationdrug.domain.DrugType;
@Repository
public class DrugTypeDaoImpl extends BaseDaoImpl<DrugType, Long> implements DrugTypeDao{
	@SuppressWarnings("unchecked")
	public
	List<DrugType> findBydrugids(List<Long> drugids) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from DrugType where basicDrugData.id in (:values)  order by drugTypeId"); 
				//"or commodityName like ? or upper(commodityNameSymbol) like ?)");
		
		List<DrugType> l = this.getSession().createQuery(hql.toString()).setParameterList("values", drugids).list();
		return l;
		}
}
