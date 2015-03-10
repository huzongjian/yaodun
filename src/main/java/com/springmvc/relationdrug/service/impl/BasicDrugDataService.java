package com.springmvc.relationdrug.service.impl;



import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.BasicDrugDataDao;
import com.springmvc.relationdrug.domain.BasicDrugData;
/**
 * 
  * @ClassName: BasicDrugCheckService
  * @Description: 药物基表service
  *
 */
@Transactional
@Service("basicDrugDataService")
public class BasicDrugDataService extends BaseServiceImpl<BasicDrugData,Long>{
	
	@Resource
	@Autowired
	private BasicDrugDataDao basicDrugDataDao;
	
	/**
	 * 全面加载
	 * @param id
	 * @return
	 */
	public BasicDrugData get(Long id){
		BasicDrugData bdc = basicDrugDataDao.get(id);
		return bdc;
	}
	public BasicDrugData findByName(String name){
		return basicDrugDataDao.findByName(name);
	}
	public List<BasicDrugData> findByNameAndSymbol(String name){
		List<BasicDrugData> bdc =basicDrugDataDao.findByNameAndSymbol(name);
		return bdc;
	}
	
	@Override
	public BaseDao<BasicDrugData, Long> getDao() {
		return basicDrugDataDao;
	}
	public BasicDrugData loadDeepBasicDrugCheck(Long id) throws ServiceException{
		try{
			BasicDrugData bdc = basicDrugDataDao.findByid(id);
			if(bdc == null){
				return null;
			}
			//bdc.setLoaded(true);
		//	Hibernate.initialize(bdc.getCompatTaboo());
			
		//	Hibernate.initialize(bdc.getSkinTest());
			if(bdc.getSkinTest() != null)
				bdc.getSkinTest().setLoaded(true);
			return bdc;
			
		}catch(Exception e){
			System.err.println("异常");
			throw new ServiceException(e);
		}
	}
	

}
