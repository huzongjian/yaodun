package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.BasicDrugRelationshipDao;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.domain.BasicDrugData;
import com.springmvc.relationdrug.domain.BasicDrugRelationship;

/**
 * 用量
 * 
 * @author feifangyuan
 * @since 2013-8-1
 */
@Transactional
@Repository
public class BasicDrugRelationshipDaoImpl extends
		BaseDaoImpl<BasicDrugRelationship, Long> implements
		BasicDrugRelationshipDao {

	@Resource
	public SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<BasicDrugRelationship> findByNameAndSymbol(String name) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BasicDrugRelationship where productName is not null and basicDrugData is not null and (productName like ? or upper(productNameSymbol) like ? )"); 
				//"or commodityName like ? or upper(commodityNameSymbol) like ?)");
		
		List<BasicDrugRelationship> l =
		 this.getSession().createQuery(hql.toString())
				.setString(0, "" + name + "%")
				.setString(1, "" + name.toUpperCase() + "%")
				//.setString(2, "" + name + "%")
				//.setString(3, "" + name.toUpperCase() + "%")
				.setMaxResults(10)
				.list();
		
		StringBuilder hql3 = new StringBuilder();
		hql3.append(" from BasicDrugRelationship where commodityName is not null and basicDrugData is not null and (commodityName like ? or upper(commodityNameSymbol) like ? )"); 
				//"or commodityName like ? or upper(commodityNameSymbol) like ?)");
		
		List<BasicDrugRelationship> l3 =
		 this.getSession().createQuery(hql3.toString())
				.setString(0, name + "%")
				.setString(1, "" + name.toUpperCase() + "%")
				//.setString(2, "" + name + "%")
				//.setString(3, "" + name.toUpperCase() + "%")
				.setMaxResults(10)
				.list();
		
		for(BasicDrugRelationship b3: l3){
			b3.setProductName(b3.getCommodityName());
			l.add(b3);
		}
		
		StringBuilder hql2 = new StringBuilder();
		hql2.append(" from BasicDrugData where drugName is not null and (drugName like ? or upper(symbol) like ?)");
		hql2.append(" ORDER BY  case when symbol LIKE ? THEN 1 else 0 end DESC");
		List<BasicDrugData> l2 = this.getSession().createQuery(hql2.toString())
				.setString(0, "%" + name + "%")
				.setString(1, "%" + name.toUpperCase() + "%")
				.setString(2, name.toUpperCase() + "%")
				.setMaxResults(10).list();
		
		for(BasicDrugData b: l2){
			BasicDrugRelationship d2 =new BasicDrugRelationship();
			d2.setBasicDrugCheck(b);
			d2.setCommodityName(b.getDrugName());
			d2.setProductName(b.getDrugName());
			d2.setProductNameSymbol(b.getSymbol());
			l.add(d2);
		}
		return l;
	}

}
