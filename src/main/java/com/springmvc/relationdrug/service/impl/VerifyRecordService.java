package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.base.util.StringManager;
import com.springmvc.relationdrug.dao.impl.VerifyRecordDaoImpl;
import com.springmvc.relationdrug.domain.BasicDrugCheck;
import com.springmvc.relationdrug.domain.VerifyRecord;
import com.springmvc.relationdrug.domain.VerifyRecordDetail;
import com.springmvc.relationdrug.pojo.ReportDetail;

/**
 * 
 * Summary : 检测记录service
 *
 *
 */
@Transactional
@Service
public class VerifyRecordService extends BaseServiceImpl<VerifyRecord,Long>{

	
	private Logger log = Logger.getLogger(PrescriptionVerifier.class);

	@Resource
	private VerifyRecordDaoImpl verifyRecordDao;

	

	

	/**
	 * 
	 * 保存检测记录及明细
	 * 
	 * @param reoirtDetailList
	 */
	public void saveVerifyRecord(VerifyRecord vr,
			List<ReportDetail> reoirtDetailList) {
		for (ReportDetail rd : reoirtDetailList) {
			VerifyRecordDetail verifyRecordDetail = new VerifyRecordDetail();
			verifyRecordDetail.setGrade(rd.getGrade());
			verifyRecordDetail.setRemark(rd.getRemark());
			verifyRecordDetail.setRuleType(rd.getRuleType());
			verifyRecordDetail.setVerifyRecord(vr);
			this.verifyRecordDao.insertVerifyRecordDetail(verifyRecordDetail);
		}
	}
	
	
	
	
	
	/*=================================天地分割线=================================================*/
	
	
	
	

	@SuppressWarnings({ "unchecked", "unused" })
	public void encrypt(){
		StringBuilder hql = new StringBuilder();
		hql.append("select count(dd) from BasicDrugCheck dd");
		Long count = (Long)this.verifyRecordDao.getSession().createQuery(hql.toString()).uniqueResult();
		long parts = count/1000;
		parts += (count%1000>0)?1:0;
		hql = new StringBuilder();
		hql.append("from BasicDrugCheck  order by id");
		Long currentId = null;
		for(int i = 0;i<parts;i++){
			try{
			List<BasicDrugCheck> details = this.verifyRecordDao.getSession().createQuery(hql.toString()).setFirstResult(i*1000).setMaxResults(1000).list();
			for(BasicDrugCheck detail : details){
				currentId =	detail.getBasicDrugCheckId();
				detail.setDrugName(detail.getDrugName());
				detail.setDrugZhName(detail.getDrugZhName());
			}
			this.verifyRecordDao.getSession().flush();
			this.verifyRecordDao.getSession().clear();
			}catch(Exception e){
			}
			}
	}
	@SuppressWarnings({ "unchecked", "unused" })
	public void decrypt(){
		StringBuilder hql = new StringBuilder();
		hql.append("select count(dd) from BasicDrugCheck dd");
		Long count = (Long)this.verifyRecordDao.getSession().createQuery(hql.toString()).uniqueResult();
		long parts = count/1000;
		parts += (count%1000>0)?1:0;
		hql = new StringBuilder();
		hql.append("from BasicDrugCheck order by id");
		Long currentId = null;
		for(int i = 0;i<parts;i++){
			try{
			List<BasicDrugCheck> details = this.verifyRecordDao.getSession().createQuery(hql.toString()).setFirstResult(i*1000).setMaxResults(1000).list();
			for(BasicDrugCheck detail : details){
				currentId =	detail.getBasicDrugCheckId();
				detail.setDrugName(detail.getDrugName());
				detail.setDrugZhName(detail.getDrugZhName());
			}
			this.verifyRecordDao.getSession().flush();
			this.verifyRecordDao.getSession().clear();
			}catch(Exception e){
			}
			}
	}
	
	
	@SuppressWarnings("unchecked")
	public void setPinyin(){
		StringBuilder hql = new StringBuilder();
		hql.append("select count(dd) from BasicDrugCheck dd");
		Long count = (Long)this.verifyRecordDao.getSession().createQuery(hql.toString()).uniqueResult();
		long parts = count/1000;
		parts += (count%1000>0)?1:0;
		hql = new StringBuilder();
		hql.append("from BasicDrugCheck order by id");
		Long currentId = null;
		for(int i = 0;i<parts;i++){
			try{
			List<BasicDrugCheck> cells = this.verifyRecordDao.getSession().createQuery(hql.toString()).setFirstResult(i*1000).setMaxResults(1000).list();
			for(BasicDrugCheck cell : cells){
				currentId =	cell.getBasicDrugCheckId();
				cell.setSymbol(StringManager.getFirstPinYin(cell.getDrugName()));
				cell.setDrugName(cell.getDrugName());
				cell.setDrugZhName(cell.getDrugZhName());
			}
			this.verifyRecordDao.getSession().flush();
			this.verifyRecordDao.getSession().clear();
			}catch(Exception e){
				log.info("id :"+currentId+"=====================================",e);
			}
			}
	}

	@SuppressWarnings("unchecked")
	public void setPinyin1(){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from G_drug_manage dd");
		java.math.BigInteger count1 = (java.math.BigInteger)this.verifyRecordDao.getSession().createSQLQuery(sql.toString()).uniqueResult();
		int count = count1.intValue();
		int parts = count/1000;
		parts += (count%1000>0)?1:0;
		sql = new StringBuilder();
		sql.append("select id_,drug_name_ from G_drug_manage order by id_");
		for(int i = 0;i<parts;i++){
			try{
				List<Object[]> cells = this.verifyRecordDao.getSession().createSQLQuery(sql.toString()).setFirstResult(i*1000).setMaxResults(1000).list();
				//可能修改后有问题。
				Query stmt = this.verifyRecordDao.getSession().createQuery("update G_drug_manage set drug_Symbol_=? where id_=?");
				for(Object[] cell : cells){
					stmt.setString(1, StringManager.getFirstPinYin((String)cell[1]));
					stmt.setParameter(2, cell[0]);
					stmt.executeUpdate();
				}
				this.verifyRecordDao.getSession().flush();
				this.verifyRecordDao.getSession().clear();
			}catch(Exception e){
			}
			}
	}

	@Override
	public BaseDao<VerifyRecord, Long> getDao() {
		return null;
	}
	
	
}
